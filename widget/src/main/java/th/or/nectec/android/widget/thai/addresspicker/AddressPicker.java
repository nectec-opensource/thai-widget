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

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.List;

import th.or.nectec.android.widget.thai.AddressView;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.repository.JsonSubdistrictRepository;
import th.or.nectec.domain.thai.ThaiAddressPrinter;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictChooser;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictPresenter;
import th.or.nectec.entity.thai.Address;

public class AddressPicker extends Button implements AddressView, OnAddressChangedListener {

    Address address;
    Context context;
    Activity activity;
    AddressPickerDialogFragment addressPickerDialogFragment;

    SubdistrictChooser subdistrictChooser;
    SubdistrictPresenter subdistrictPresenter = new SubdistrictPresenter() {
        @Override
        public void showSubdistrictList(List<Address> subdistrict) {

        }

        @Override
        public void showSubdistrictInfo(Address subdistrict) {
            address = subdistrict;
            setText(ThaiAddressPrinter.buildShortAddress(subdistrict.getSubdistrict(), subdistrict.getDistrict(), subdistrict.getProvince()));
        }

        @Override
        public void showNotFoundSubdistrict() {

        }
    };

    public AddressPicker(Context context) {
        super(context);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.spinnerStyle);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context context) {
        this.context = context;

        if (context instanceof Activity) {
            activity = (Activity) context;
        }

        if (activity == null) {
            return;
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        AddressPickerDialogFragment addressPickerDialogFragment = (AddressPickerDialogFragment) fragmentManager.findFragmentByTag(AppCompatAddressPickerDialogFragment.FRAGMENT_TAG);

        if (addressPickerDialogFragment != null) {
            this.addressPickerDialogFragment = addressPickerDialogFragment;
        } else {
            this.addressPickerDialogFragment = new AddressPickerDialogFragment();
        }

        this.addressPickerDialogFragment.setOnAddressChangedListener(this);

        subdistrictChooser = new SubdistrictChooser(new JsonSubdistrictRepository(getContext()), subdistrictPresenter);

        setText("กรุณาระบุ ตำบล อำเภอ จังหวัด");

    }

    @Override
    public boolean performClick() {
        boolean handle = false;
        if (this.addressPickerDialogFragment != null) {
            FragmentManager fm = activity.getFragmentManager();

            if (!this.addressPickerDialogFragment.isAdded()) {
                this.addressPickerDialogFragment.show(fm, AddressPickerDialogFragment.FRAGMENT_TAG);

                if (address != null) {
                    this.addressPickerDialogFragment.restoreAddressField(address);
                }

                handle = true;
            }
        }
        return handle;
    }

    @Override
    public void setAddressCode(String addressCode) {
        subdistrictChooser.showSubDistrictInfoByAddressCode(addressCode);
    }

    @Override
    public void setAddress(String subdistrict, String district, String province) {
        subdistrictChooser.showSubDistrictInfoByAddressData(subdistrict, district, province);
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
        setText(ThaiAddressPrinter.buildShortAddress(address.getSubdistrict(), address.getDistrict(), address.getProvince()));
    }

    @Override
    public void onAddressCanceled() {

    }
}
