/*
 * Copyright (c) 2016 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package th.or.nectec.thai.widget.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import nectec.thai.identity.CitizenId;
import nectec.thai.widget.identity.CitizenIdHandler;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static th.or.nectec.thai.widget.sample.matcher.EditTextMatcher.withError;
import static th.or.nectec.thai.widget.sample.matcher.EditTextMatcher.withoutError;
import static th.or.nectec.thai.widget.sample.matcher.IdentityViewMatcher.withIdentity;

@RunWith(AndroidJUnit4.class)
public class CitizenIdSampleActivityTest {

    public static final String VALID_ID = "1610255811112";
    public static final String INVALID_ID = "1234567890123";
    @Rule
    public ActivityTestRule<CitizenIdSampleActivity> activityRule = new ActivityTestRule<>(CitizenIdSampleActivity.class);

    @Test
    public void checkIdEditTextDisplay() {
        onView(withId(R.id.citizen_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void prettyPrint() {
        CitizenId cid = new CitizenId(VALID_ID);
        onView(withId(R.id.citizen_id))
                .perform(typeText(cid.getId()))
                .check(matches(withText(cid.prettyPrint())));
    }

    @Test
    public void typeValidId() {
        onView(withId(R.id.citizen_id))
                .perform(typeText(VALID_ID))
                .check(matches(withoutError()));
    }

    @Test
    public void typeInvalidId() {
        onView(withId(R.id.citizen_id))
                .perform(typeText(INVALID_ID))
                .check(matches(withError(CitizenIdHandler.DEFAULT_ERROR_MESSAGE)));
    }

    @Test
    public void mustCannottypeIdLengthMoreThan13() {
        onView(withId(R.id.citizen_id))
                .perform(typeText("1610255811112123"))
                .check(matches(withText("1-6102-55811-11-2")));
    }

    @Test
    public void mustCannottypeCharacter() {
        onView(withId(R.id.citizen_id))
                .perform(typeText("1z6102ab55811-11-2"))
                .check(matches(withText("1-6102-55811-11-2")));
    }

    @Test
    public void getIdentity() {
        onView(withId(R.id.citizen_id))
                .perform(typeText(VALID_ID))
                .check(matches(withIdentity(new CitizenId(VALID_ID))));

    }


}
