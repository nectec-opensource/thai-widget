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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintContextWrapper;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import th.or.nectec.android.widget.thai.AddressView;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.R;
import th.or.nectec.domain.thai.ThaiAddressPrinter;
import th.or.nectec.entity.thai.Address;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class AddressPicker extends AppCompatButton implements AddressView, OnAddressChangedListener {

    public static final int[] TINT_ATTRS = {android.R.attr.background};
    public static final String FRAGMENT_TAG = "address_dialog";
    Address address;
    Context context;
    AppCompatActivity activity;
    AddressPickerDialogFragment addressPickerDialogFragment;

    public AddressPicker(Context context) {
        super(context);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.spinnerStyle);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(TintContextWrapper.wrap(context), attrs, defStyleAttr);
        initTintManager(attrs, defStyleAttr);
        init(context);
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

    public void init(Context context) {
        this.context = context;

        if (context instanceof AppCompatActivity) {
            activity = (AppCompatActivity) context;
        }

        if (activity == null) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        AddressPickerDialogFragment addressPickerDialogFragment = (AddressPickerDialogFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);

        if (addressPickerDialogFragment != null) {
            this.addressPickerDialogFragment = addressPickerDialogFragment;
        } else {
            this.addressPickerDialogFragment = new AddressPickerDialogFragment();
        }

        this.addressPickerDialogFragment.setOnAddressChangedListener(this);

        fragmentManager.beginTransaction().add(new AddressPickerDialogFragment(), "dialog").commit();

        setText("กรุณาระบุ ตำบล อำเภอ จังหวัด");

    }

    @Override
    public boolean performClick() {
        boolean handle = false;
        if (this.addressPickerDialogFragment != null) {
            FragmentManager fm = activity.getSupportFragmentManager();

            if (!this.addressPickerDialogFragment.isAdded()) {
                this.addressPickerDialogFragment.show(fm, "dialog");
                handle = true;

                if (address != null) {

                }
            }
        }
        return handle;
    }

    @Override
    public void setAddressCode(String addressCode) {

    }

    @Override
    public void setAddress(String subdistrict, String district, String province) {
        setText(ThaiAddressPrinter.buildShortAddress(subdistrict, district, province));
    }

    @Override
    public void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener) {

    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void onAddressChanged(Address address) {
        this.address = address;
        setAddress(address.getSubdistrict(), address.getDistrict(), address.getProvince());
    }

    @Override
    public void onAddressCanceled() {

    }
}
