/*
 * Copyright © 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.or.nectec.android.widget.thai.sample;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import th.or.nectec.domain.thai.CitizenId;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static th.or.nectec.android.widget.thai.sample.IdEditTextMatcher.withIdObject;

@RunWith(AndroidJUnit4.class)
public class CitizenIdSampleActivityTest {

    @Rule
    public ActivityTestRule<CitizenIdSampleActivity> activityRule = new ActivityTestRule<>(CitizenIdSampleActivity.class);

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

    @Test
    public void getNonPrettyText() {
        onView(withId(R.id.citizen_id))
                .perform(ViewActions.typeText("1610255811112"))
                .check(matches(withIdObject(new CitizenId("1610255811112"))));

    }


}