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
 *
 *
 */

package th.or.nectec.android.widget.thai.sample;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import th.or.nectec.domain.thai.HouseId;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static th.or.nectec.android.widget.thai.sample.EditTextMatcher.withError;
import static th.or.nectec.android.widget.thai.sample.IdEditTextMatcher.withIdObject;

public class HouseIdSampleActivityTest {

    @Rule
    public ActivityTestRule<HouseIdSampleActivity> activityRule = new ActivityTestRule<>(HouseIdSampleActivity.class);

    @Test
    public void checkIdEditTextDisplay() {
        onView(withId(R.id.house_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void typeInvalidId() {
        onView(withId(R.id.house_id))
                .perform(ViewActions.typeText("12345678901"))
                .check(matches(withText("1234-567890-1")))
                .check(matches(withError("invalid house id")));
    }

    @Test
    public void getIdObject() {
        onView(withId(R.id.house_id))
                .perform(ViewActions.typeText("74020749965"))
                .check(matches(withIdObject(new HouseId("74020749965"))));
    }


}
