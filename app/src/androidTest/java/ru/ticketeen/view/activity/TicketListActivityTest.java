package ru.ticketeen.view.activity;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.ticketeen.BaseSleepTest;
import ru.ticketeen.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TicketListActivityTest extends BaseSleepTest {

    @Rule
    public ActivityTestRule<TicketListActivity> rule = new ActivityTestRule<>(TicketListActivity.class);

    @Test
    public void ticketListActivityTest() throws InterruptedException {
        onView(withId(R.id.list))
                .check(matches(isDisplayed()));

        int numItems = ((RecyclerView) rule.getActivity()
                .findViewById(R.id.list)).getAdapter().getItemCount();
        assertTrue("List is empty", numItems > 0);
    }

}
