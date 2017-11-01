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

package nectec.thai.widget.identity;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import nectec.thai.identity.Identity;

public abstract class IdentityHandler<T extends Identity> {
    private EditText editText;
    private boolean watching = true;
    private T id;

    private IdentityWatcher<T> idWatcher;

    public IdentityHandler(EditText editText) {
        this.editText = editText;
        this.id = onCreateNewId(editText.getText().toString());

        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(getMaxLength())});
        editText.setKeyListener(DigitsKeyListener.getInstance(false, true));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {//NOPMD
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {//NOPMD
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (watching) onIdChanged(editable);
            }
        });
    }

    protected abstract int getMaxLength();

    protected abstract T onCreateNewId(String id);

    private void onIdChanged(Editable editable) {
        T id = onCreateNewId(editable.toString());
        updateText(id);
    }

    private void updateText(T id) {
        String newText = id.prettyPrint();
        Editable currentText = editText.getText();
        if (newText.length() > currentText.length() && newText.length() <= getMaxLength()) {
            watching = false;
            this.id = id;
            editText.setText(newText);
            editText.setSelection(editText.getText().length());
            watching = true;
        }

        if (newText.length() == getMaxLength()) {
            validate(id);
        }
    }

    private void validate(T id) {
        if (id.validate()) {
            if (idWatcher != null) idWatcher.onValid(id);
        } else {
            if (idWatcher != null) idWatcher.onInvalid(id);
        }
    }

    public T getId() {
        return id;
    }

    public void setIdWatcher(IdentityWatcher idWatcher) {
        this.idWatcher = idWatcher;
    }
}
