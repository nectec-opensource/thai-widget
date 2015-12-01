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

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.fragment.DistrictListFragment;
import th.or.nectec.android.widget.thai.addresspicker.fragment.ProvinceListFragment;
import th.or.nectec.android.widget.thai.addresspicker.fragment.RegionListFragment;
import th.or.nectec.android.widget.thai.addresspicker.fragment.SubdistrictListFragment;
import th.or.nectec.android.widget.thai.addresspicker.handler.AddressPickerInterface;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonDistrictRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonProvinceRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonSubdistrictRepository;
import th.or.nectec.domain.thai.address.AddressController;
import th.or.nectec.domain.thai.address.AddressPresenter;
import th.or.nectec.entity.thai.Address;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.Province;
import th.or.nectec.entity.thai.Subdistrict;


public class AppCompatAddressPickerDialogFragment extends DialogFragment implements AddressPickerInterface, View.OnClickListener, AddressPresenter {

    public static final String FRAGMENT_TAG = "address_dialog";
    private static final int SELECT_REGION = 0;
    private static final int SELECT_PROVINCE = 1;
    private static final int SELECT_DISTRICT = 2;
    private static final int SELECT_SUBDISTRICT = 3;
    FragmentManager fragmentManager;
    Button backButton, nextButton;

    RegionListFragment regionListFragment;
    ProvinceListFragment provinceListFragment;
    DistrictListFragment districtListFragment;
    SubdistrictListFragment subdistrictListFragment;
    OnAddressChangedListener addressChangedListener;

    private int currentState = SELECT_REGION;
    private Province province;
    private District district;
    private Subdistrict subdistrict;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_picker_dialog, container, false);

        initInstances(view);

        return view;
    }

    private void initInstances(View view) {
        backButton = (Button) view.findViewById(R.id.back);
        nextButton = (Button) view.findViewById(R.id.next);
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bringToRegionList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {
            backStep();
        } else if (id == R.id.next) {
            nextStep();
        }
    }

    public void nextStep() {
        if (currentState == SELECT_REGION) {
            String region = regionListFragment.getRegion();
            if (TextUtils.isEmpty(region)) {
                Toast.makeText(getActivity(), "ไปเลือกภูมิภาคก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                bringToProvinceList(region);
            }
        } else if (currentState == SELECT_PROVINCE) {
            province = provinceListFragment.getData();
            if (province == null) {
                Toast.makeText(getActivity(), "ไปเลือกจังหวัดก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                bringToDistrictList(province.getCode());
            }

        } else if (currentState == SELECT_DISTRICT) {
            district = districtListFragment.getData();
            if (district == null) {
                Toast.makeText(getActivity(), "ไปเลือกอำเภอก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                bringToSubdistrictList(district.getCode());
            }

        } else if (currentState == SELECT_SUBDISTRICT) {
            subdistrict = subdistrictListFragment.getData();
            if (subdistrict == null) {
                Toast.makeText(getActivity(), "ไปเลือกตำบลก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                AddressController addressController = new AddressController(
                        InMemoryJsonSubdistrictRepository.getInstance(getActivity()),
                        InMemoryJsonDistrictRepository.getInstance(getActivity()),
                        InMemoryJsonProvinceRepository.getInstance(getActivity()), this);
                addressController.showByAddressCode(subdistrict.getCode());
                dismiss();
            }
        }
    }

    public void backStep() {
        if (currentState == SELECT_REGION) {
            dismiss();
        } else if (currentState == SELECT_PROVINCE) {
            bringToRegionList();
        } else if (currentState == SELECT_DISTRICT) {
            bringToProvinceList(province.getRegion().toString());
        } else if (currentState == SELECT_SUBDISTRICT) {
            bringToDistrictList(district.getProvinceCode());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                backStep();
            }
        };
    }

    @Override
    public void bringToRegionList() {
        getDialog().setTitle(R.string.choose_region);
        regionListFragment = new RegionListFragment();
        fragmentManager.beginTransaction().replace(R.id.container, regionListFragment, RegionListFragment.FRAGMENT_TAG).commit();
        backButton.setEnabled(false);
        currentState = SELECT_REGION;
    }

    @Override
    public void bringToProvinceList(String region) {
        getDialog().setTitle(R.string.choose_province);
        provinceListFragment = ProvinceListFragment.newInstance(region);
        fragmentManager.beginTransaction()
                .replace(R.id.container, provinceListFragment, ProvinceListFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
        currentState = SELECT_PROVINCE;
        getDialog().setTitle(R.string.choose_province);
        backButton.setEnabled(true);
    }

    @Override
    public void bringToDistrictList(String provinceCode) {
        getDialog().setTitle(R.string.choose_district);
        districtListFragment = DistrictListFragment.newInstance(provinceCode);
        fragmentManager.beginTransaction()
                .replace(R.id.container, districtListFragment, DistrictListFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
        getDialog().setTitle(R.string.choose_district);
        nextButton.setText(R.string.next);
        currentState = SELECT_DISTRICT;
    }

    @Override
    public void bringToSubdistrictList(String districtCode) {
        getDialog().setTitle(R.string.choose_subdistrict);
        subdistrictListFragment = SubdistrictListFragment.newInstance(districtCode);
        fragmentManager.beginTransaction()
                .replace(R.id.container, subdistrictListFragment, SubdistrictListFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
        getDialog().setTitle(R.string.choose_subdistrict);
        currentState = SELECT_SUBDISTRICT;
        nextButton.setText(R.string.finish);
    }

    @Override
    public void bringAddressValueToAddressView(Address addressData) {
        if (addressChangedListener != null) {
            addressChangedListener.onAddressChanged(addressData);
        }
        dismiss();
    }

    @Override
    public void displayAddressInfo(Address address) {
        bringAddressValueToAddressView(address);
    }

    @Override
    public void alertAddressNotFound() {
        Toast.makeText(getActivity(), "ไม่พบข้อมูลที่อยู่", Toast.LENGTH_LONG).show();
    }

    public void setOnAddressChangedListener(OnAddressChangedListener addressChangedListener) {
        this.addressChangedListener = addressChangedListener;
    }

    @Override
    public void restoreAddressField(final Address address) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                subdistrict = new Subdistrict(address.getAddressCode(), address.getSubdistrict());
                district = new District(address.getDistrictCode(), address.getDistrict());
                province = new Province(address.getProvinceCode(), address.getProvince(), address.getRegion());
                bringToProvinceList(province.getRegion().toString());
                bringToDistrictList(district.getProvinceCode());
                bringToSubdistrictList(subdistrict.getDistrictCode());
            }
        });
    }
}