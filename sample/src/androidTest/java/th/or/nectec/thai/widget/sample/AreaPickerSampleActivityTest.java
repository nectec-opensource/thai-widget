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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import th.or.nectec.thai.unit.Area;
import th.or.nectec.thai.widget.sample.action.NumberPickerAction;
import th.or.nectec.thai.widget.unit.AreaPickerDialog;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.startsWith;
import static th.or.nectec.thai.widget.sample.matcher.AreaViewMatcher.withArea;

@RunWith(AndroidJUnit4.class)
public class AreaPickerSampleActivityTest {

    @Rule
    public ActivityTestRule<AreaPickerSampleActivity> activityTestRule = new ActivityTestRule<>(AreaPickerSampleActivity.class);

    @Test
    public void testView() throws Exception {
        onView(withId(R.id.area_picker))
                .perform(click());

        onView(withText(startsWith(AreaPickerDialog.TITLE)))
                .check(matches(isDisplayed()));

        onView(withId(R.id.rai))
                .perform(NumberPickerAction.setValue(10));
        onView(withId(R.id.ngan))
                .perform(NumberPickerAction.setValue(2));
        onView(withId(R.id.squareWa))
                .perform(NumberPickerAction.setValue(40));

        onView(withId(android.R.id.button1))
                .perform(click());

        onView(withId(R.id.area_picker))
                .check(matches(withArea(Area.fromRaiNganSqaureWa(10, 2, 40))));
    }
}
