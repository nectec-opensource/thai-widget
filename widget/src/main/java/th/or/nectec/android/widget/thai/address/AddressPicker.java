/*
 * Copyright © 2016 NECTEC
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

package th.or.nectec.android.widget.thai.address;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Button;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.entity.thai.Address;

public class AddressPicker extends Button implements AddressView {
    AddressPickerHandler handler;

    public AddressPicker(Context context) {
        this(context, null);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handler = new AddressPickerHandler(this, context);
    }

    @Override
    public boolean performClick() {
        return handler.onClick();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return handler.buildSaveState(super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof AddressSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        AddressSavedState ss = (AddressSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setAddressCode(ss.addressCode);
    }

    @Override
    public void setAddressCode(String addressCode) {
        handler.setAddressCode(addressCode);
    }

    @Override
    public void setAddress(String subdistrict, String district, String province) {
        handler.setAddress(subdistrict, district, province);
    }

    @Override
    public void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener) {
        handler.setOnAddressChangedListener(onAddressChangedListener);
    }

    @Override
    public Address getAddress() {
        return handler.getAddress();
    }
}
