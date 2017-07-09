package ru.ticketeen.view.activity;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.ticketeen.BaseSleepTest;
import ru.ticketeen.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest extends BaseSleepTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        onView(withId(R.id.login_form))
                .check(matches(isDisplayed()));

        onView(withId(R.id.phone))
                .check(matches(allOf(withText(""), isDisplayed())));

        onView(withId(R.id.password))
                .check(matches(allOf(withText(""), isDisplayed())));

        onView(withId(R.id.phone_sign_in_button))
                .check(matches(allOf(withText("Войти"), isDisplayed())));

        onView(withId(R.id.to_sign_up_button))
                .check(matches(allOf(withText("Регистрация"), isDisplayed())));

        onView(withId(R.id.phone))
                .perform(scrollTo(), replaceText("+79139066994"), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(scrollTo(), replaceText("705697"), closeSoftKeyboard());

        // todo: add then done next activity
        // button.perform(scrollTo(), click());
    }

}
