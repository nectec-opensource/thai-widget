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

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import th.or.nectec.android.widget.thai.PrettyEditText;

public class PrettyTextMatcher {

    public static Matcher<View> withNonPrettyText(final String stringMatcher) {
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with pretty text: " + stringMatcher);
                //stringMatcher.describeTo(description);

            }

            @Override
            protected boolean matchesSafely(EditText editText) {
                if (editText instanceof PrettyEditText) {
                    return stringMatcher.equals(((PrettyEditText) editText).getNonPrettyText().toString());
                }
                return false;
            }
        };
    }
}
