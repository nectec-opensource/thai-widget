package th.or.nectec.android.widget.thai.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by blaze on 10/17/2015 AD.
 */
@RunWith(AndroidJUnit4.class)
public class CitizenIdSampleActivityTest {

    @Rule
    public ActivityTestRule<CitizenIdSampleActivity> activityRule = new ActivityTestRule(CitizenIdSampleActivity.class);

    @Test
    public void checkIdEditTextdisplay(){
        onView(withId(R.id.citizen_id)).check(matches(isDisplayed()));
    }

}