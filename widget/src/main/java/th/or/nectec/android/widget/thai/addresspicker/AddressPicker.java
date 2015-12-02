/*
 * Copyright 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
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

package th.or.nectec.android.widget.thai.addresspicker;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Button;

import th.or.nectec.android.widget.thai.AddressView;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.addresspicker.handler.AddressPickerHandler;
import th.or.nectec.entity.thai.Address;

public class AddressPicker extends Button implements AddressView {
    AddressPickerHandler addressPickerHandler;

    public AddressPicker(Context context) {
        super(context);
        initHandler(context);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
        initHandler(context);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHandler(context);
    }

    private void initHandler(Context context) {
        addressPickerHandler = new AddressPickerHandler(this, context);
    }

    @Override
    public boolean performClick() {
        return addressPickerHandler.performClick();
    }

    @Override
    public void setAddressCode(String addressCode) {
        addressPickerHandler.setAddressCode(addressCode);
    }

    @Override
    public void setAddress(String subdistrict, String district, String province) {
        addressPickerHandler.setAddress(subdistrict, district, province);
    }

    @Override
    public void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener) {
        addressPickerHandler.setOnAddressChangedListener(onAddressChangedListener);
    }

    @Override
    public Address getAddress() {
        return addressPickerHandler.getAddress();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        AddressSavedState savedState = new AddressSavedState(parcelable);
        savedState.addressCode = addressPickerHandler.getAddress().getSubdistrictCode();
        return savedState;
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
}
