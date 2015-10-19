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

package th.or.nectec.android.widget.thai;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

import th.or.nectec.domain.thai.CitizenId;

public class CitizenIdEditText extends EditText implements PrettyEditText {

    private static final int MAX_LENGTH = 17;

    public CitizenIdEditText(Context context) {
        super(context);
        init();
    }

    public CitizenIdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CitizenIdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setFilters(new InputFilter[] { new InputFilter.LengthFilter(MAX_LENGTH), });
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setKeyListener(DigitsKeyListener.getInstance(false, true));
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                String id = text.replaceAll("-", "");

                CitizenId cid = new CitizenId(id);
                String pretty = cid.prettyPrint();

                if(!pretty.equals(text)) {
                    setText(pretty);
                    Selection.setSelection(getEditableText(), length());
                }
            }
        });
    }

    @Override
    public Editable getNonPrettyText() {
        String text = getText().toString();
        String nonPrettyText = text.replaceAll("-", "");
        return new SpannableStringBuilder(nonPrettyText);
    }
}
