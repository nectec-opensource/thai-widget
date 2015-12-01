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
import android.content.res.ColorStateList;
import android.support.v7.internal.widget.TintContextWrapper;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import th.or.nectec.android.widget.thai.AddressView;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.handler.AddressPickerHandler;
import th.or.nectec.entity.thai.Address;

public class AppCompatAddressPicker extends AppCompatButton implements AddressView {

    public static final int[] TINT_ATTRS = {android.R.attr.background};
    private AddressPickerHandler addressPickerHandler;

    public AppCompatAddressPicker(Context context) {
        super(context);
        initHandler(context);
    }

    public AppCompatAddressPicker(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.spinnerStyle);
        initHandler(context);
    }

    public AppCompatAddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(TintContextWrapper.wrap(context), attrs, defStyleAttr);
        initTintManager(attrs, defStyleAttr);
        initHandler(context);
    }

    private void initHandler(Context context) {
        addressPickerHandler = new AddressPickerHandler(this, context);
    }

    private void initTintManager(AttributeSet attrs, int defStyleAttr) {
        if (TintManager.SHOULD_BE_USED) {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    TINT_ATTRS, defStyleAttr, 0);
            if (a.hasValue(0)) {
                ColorStateList tint = a.getTintManager().getTintList(a.getResourceId(0, -1));
                if (tint != null) {
                    setSupportBackgroundTintList(tint);
                }
            }
            a.recycle();
        }
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
}
