package ru.ticketeen.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import ru.ticketeen.App;
import ru.ticketeen.R;
import ru.ticketeen.preference.LoginPasswordPreference;

public class MainActivity extends AppCompatActivity {

    @Inject
    LoginPasswordPreference loginPasswordPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.component().inject(this);

        setContentView(R.layout.activity_main);

        if (isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        finish();
    }

    private boolean isLoggedIn() {
        return loginPasswordPreference.isLoggedIn();
    }
}
