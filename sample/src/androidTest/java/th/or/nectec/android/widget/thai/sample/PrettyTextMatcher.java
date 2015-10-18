package th.or.nectec.android.widget.thai.sample;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import th.or.nectec.android.widget.thai.PrettyEditText;

/**
 * Created by blaze on 10/18/2015 AD.
 */
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
