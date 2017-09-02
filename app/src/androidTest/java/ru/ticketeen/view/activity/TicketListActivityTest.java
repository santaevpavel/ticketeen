package ru.ticketeen.view.activity;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.ticketeen.BaseSleepTest;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TicketListActivityTest extends BaseSleepTest {

    @Rule
    public ActivityTestRule<TicketListActivity> rule = new ActivityTestRule<>(TicketListActivity.class);

    @Test
    public void ticketListActivityTest() {
        /*onView(withId(R.id.login_form))
                .check(matches(isDisplayed()));*/
    }

}
