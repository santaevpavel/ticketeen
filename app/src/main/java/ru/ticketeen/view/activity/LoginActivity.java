package ru.ticketeen.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import javax.inject.Inject;

import ru.ticketeen.App;
import ru.ticketeen.R;
import ru.ticketeen.databinding.ActivityLoginBinding;
import ru.ticketeen.preference.LoginPasswordPreference;
import ru.ticketeen.viewmodel.LoginViewModel;

import static ru.ticketeen.util.FieldCheckerUtil.isPasswordValid;
import static ru.ticketeen.util.FieldCheckerUtil.isPhoneValid;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends LifecycleActivity {

    @Inject
    LoginPasswordPreference loginPasswordPreference;

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.component().inject(this);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.password.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        binding.phoneSignInButton.setOnClickListener(view -> attemptLogin());

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getProgress().observe(this, this::showProgress);
        viewModel.getLoginStatus().observe(this, status -> {
            if (status != null && status) {
                Toast.makeText(this,
                        status + " and login " + loginPasswordPreference.getLogin() +
                                " and password " + loginPasswordPreference.getPassword(),
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                finish();
            }
        });
    }

    private void attemptLogin() {
        // Reset errors.
        binding.phone.setError(null);
        binding.password.setError(null);

        // Store values at the time of the login attempt.
        String phone = binding.phone.getText().toString();
        String password = binding.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            binding.password.setError(getString(R.string.error_field_required));
            focusView = binding.password;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            binding.password.setError(getString(R.string.error_invalid_password));
            focusView = binding.password;
            cancel = true;
        }

        // Check for a valid phone address.
        if (TextUtils.isEmpty(phone)) {
            binding.phone.setError(getString(R.string.error_field_required));
            focusView = binding.phone;
            cancel = true;
        } else if (!isPhoneValid(phone)) {
            binding.phone.setError(getString(R.string.error_invalid_phone));
            focusView = binding.phone;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            //mAuthTask = new UserLoginTask(phone, password);
            //mAuthTask.execute((Void) null);
            viewModel.login(phone, password);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            binding.phoneLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            binding.phoneLoginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    binding.phoneLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            binding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            binding.loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    binding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            binding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            binding.phoneLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

