package th.or.nectec.android.widget.thai.sample;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by blaze on 10/17/2015 AD.
 */
@RunWith(AndroidJUnit4.class)
public class CitizenIdSampleActivityTest {

    @Rule
    public ActivityTestRule<CitizenIdSampleActivity> activityRule = new ActivityTestRule(CitizenIdSampleActivity.class);

    @Test
    public void checkIdEditTextDisplay(){
        onView(withId(R.id.citizen_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void typeValidId(){
        onView(withId(R.id.citizen_id))
                .perform(ViewActions.typeText("1610255811112"))
                .check(matches(withText("1-6102-55811-11-2")));

    }

    @Test
    public void mustCannottypeIdLengthMoreThan13(){
        onView(withId(R.id.citizen_id))
                .perform(ViewActions.typeText("1610255811112123"))
                .check(matches(withText("1-6102-55811-11-2")));
    }

    @Test
    public void mustCannottypeCharacter(){
        onView(withId(R.id.citizen_id))
                .perform(ViewActions.typeText("1z6102ab55811-11-2"))
                .check(matches(withText("1-6102-55811-11-2")));
    }


}