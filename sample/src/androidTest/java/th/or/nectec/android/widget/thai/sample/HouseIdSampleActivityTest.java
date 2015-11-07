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
import th.or.nectec.android.widget.thai.HouseIdHandler;
import th.or.nectec.domain.thai.HouseId;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static th.or.nectec.android.widget.thai.sample.matcher.EditTextMatcher.withError;
import static th.or.nectec.android.widget.thai.sample.matcher.EditTextMatcher.withoutError;
import static th.or.nectec.android.widget.thai.sample.matcher.IdentityViewMatcher.withIdentity;

public class HouseIdSampleActivityTest {

    public static final String VALID_ID = "12040847961";
    public static final String INVALID_ID = "12345678901";

    @Rule
    public ActivityTestRule<HouseIdSampleActivity> activityRule = new ActivityTestRule<>(HouseIdSampleActivity.class);

    @Test
    public void checkIdEditTextDisplay() {
        onView(withId(R.id.house_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void prettyPrint() {
        HouseId hid = new HouseId(VALID_ID);
        onView(withId(R.id.house_id))
                .perform(ViewActions.typeText(hid.getId()))
                .check(matches(withText(hid.prettyPrint())));
    }

    @Test
    public void typeValidId() {
        onView(withId(R.id.house_id))
                .perform(ViewActions.typeText(VALID_ID))
                .check(matches(withoutError()));
    }


    @Test
    public void typeInvalidId() {
        onView(withId(R.id.house_id))
                .perform(ViewActions.typeText(INVALID_ID))
                .check(matches(withError(HouseIdHandler.DEFAULT_ERROR_MESSAGE)));
    }

    @Test
    public void getIdentity() {
        onView(withId(R.id.house_id))
                .perform(ViewActions.typeText(VALID_ID))
                .check(matches(withIdentity(new HouseId(VALID_ID))));
    }


}
