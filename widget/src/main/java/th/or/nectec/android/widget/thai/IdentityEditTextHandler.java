/*
 * Copyright (c) 2015 NECTEC
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
 */

package th.or.nectec.android.widget.thai;

import android.text.*;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;
import th.or.nectec.domain.thai.Identity;

public abstract class IdentityEditTextHandler implements TextWatcher {
    protected EditText editText;
    private Identity id;

    public IdentityEditTextHandler(EditText editText) {
        this.editText = editText;
        this.id = onCreateNewId(editText.getText().toString());
        initialize();
    }

    protected abstract Identity onCreateNewId(String id);

    protected void initialize() {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(getMaxLength()),});
        editText.setKeyListener(DigitsKeyListener.getInstance(false, true));
        editText.addTextChangedListener(this);
    }

    protected abstract int getMaxLength();

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        onIdChanged(editable);
    }

    public void onIdChanged(Editable editable) {
        Identity id = onCreateNewId(editable.toString());
        updateText(id);
        updateErrorMessage(id);
    }

    private void updateText(Identity id) {
        if (!id.equals(this.id)) {
            this.id = id;
            editText.setText(id.prettyPrint());
            Selection.setSelection(editText.getEditableText(), editText.length());
        }
    }

    private void updateErrorMessage(Identity id) {
        if (id.isValidFormat()) {
            editText.setError(id.validate() ? null : getErrorMessage());
        } else {
            editText.setError(null);
        }
    }

    protected abstract String getErrorMessage();

    public Identity getId() {
        return id;
    }


}
