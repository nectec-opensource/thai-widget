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

import android.content.DialogInterface;
import android.os.Bundle;
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


public class AppCompatAddressPickerDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String FRAGMENT_TAG = "address_dialog";

    private static final String ADDRESS_CODE = "address_code";
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


    private String addressCode;
    private int currentState = SELECT_REGION;

    public AppCompatAddressPickerDialogFragment() {
        // Required empty public constructor
    }

    public static AppCompatAddressPickerDialogFragment newInstance(String addressCode) {
        AppCompatAddressPickerDialogFragment fragment = new AppCompatAddressPickerDialogFragment();
        Bundle args = new Bundle();
        args.putString(ADDRESS_CODE, addressCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getChildFragmentManager();
        if (getArguments() != null) {
            addressCode = getArguments().getString(ADDRESS_CODE);
        }
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
        getDialog().setTitle(R.string.choose_region);
        regionListFragment = new RegionListFragment();
        fragmentManager.beginTransaction().replace(R.id.container, regionListFragment, RegionListFragment.FRAGMENT_TAG).commit();
        backButton.setEnabled(false);
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
            if (TextUtils.isEmpty(regionListFragment.getRegion())) {
                Toast.makeText(getActivity(), "ไปเลือกภูมิภาคก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                provinceListFragment = ProvinceListFragment.newInstance(regionListFragment.getRegion());
                fragmentManager.beginTransaction()
                        .replace(R.id.container, provinceListFragment, ProvinceListFragment.FRAGMENT_TAG)
                        .addToBackStack(null)
                        .commit();
                currentState = SELECT_PROVINCE;
                getDialog().setTitle(R.string.choose_province);
                backButton.setEnabled(true);
            }
        } else if (currentState == SELECT_PROVINCE) {
            if (provinceListFragment.getData() == null) {
                Toast.makeText(getActivity(), "ไปเลือกจังหวัดก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                districtListFragment = DistrictListFragment.newInstance(provinceListFragment.getData().getCode());
                fragmentManager.beginTransaction()
                        .replace(R.id.container, districtListFragment, DistrictListFragment.FRAGMENT_TAG)
                        .addToBackStack(null)
                        .commit();
                getDialog().setTitle(R.string.choose_district);
                currentState = SELECT_DISTRICT;
            }

        } else if (currentState == SELECT_DISTRICT) {
            if (districtListFragment.getData() == null) {
                Toast.makeText(getActivity(), "ไปเลือกอำเภอก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), districtListFragment.getData().getDistrict(), Toast.LENGTH_LONG).show();
                subdistrictListFragment = SubdistrictListFragment.newInstance(districtListFragment.getData().getAddressCode());
                fragmentManager.beginTransaction()
                        .replace(R.id.container, subdistrictListFragment, SubdistrictListFragment.FRAGMENT_TAG)
                        .addToBackStack(null)
                        .commit();
                getDialog().setTitle(R.string.choose_subdistrict);
                currentState = SELECT_SUBDISTRICT;
                nextButton.setText(R.string.finish);
            }

        } else if (currentState == SELECT_SUBDISTRICT) {
            if (subdistrictListFragment.getData() == null) {
                Toast.makeText(getActivity(), "ไปเลือกตำบลก่อนเลย", Toast.LENGTH_LONG).show();
            } else {
                this.addressChangedListener.onAddressChanged(subdistrictListFragment.getData());
                dismiss();
            }
        }
    }

    public void backStep() {
        fragmentManager.popBackStack();
        if (currentState == SELECT_PROVINCE) {
            currentState = SELECT_REGION;
            backButton.setEnabled(false);
        } else if (currentState == SELECT_DISTRICT) {
            currentState = SELECT_PROVINCE;
        } else if (currentState == SELECT_SUBDISTRICT) {
            currentState = SELECT_DISTRICT;
            nextButton.setText(R.string.back);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        currentState = SELECT_REGION;
    }

    public void setOnAddressChangedListener(OnAddressChangedListener addressChangedListener) {
        this.addressChangedListener = addressChangedListener;
    }
}
