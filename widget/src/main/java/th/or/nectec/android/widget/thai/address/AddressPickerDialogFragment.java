/*
 * Copyright © 2015 NECTEC
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

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.R;
import th.or.nectec.domain.thai.address.*;
import th.or.nectec.entity.thai.*;

import java.util.ArrayList;
import java.util.List;


public class AddressPickerDialogFragment extends DialogFragment
        implements AdapterView.OnItemClickListener,
        RegionPresenter, ProvincePresenter, DistrictPresenter, SubdistrictListPresenter, AddressPresenter {

    public static final String FRAGMENT_TAG = "address_dialog";

    private static final int SELECT_REGION = 0;
    private static final int SELECT_PROVINCE = 1;
    private static final int SELECT_DISTRICT = 2;
    private static final int SELECT_SUBDISTRICT = 3;

    private OnAddressChangedListener addressChangedListener;
    private ListView listView;

    private ArrayAdapter<String> regionAdapter;
    private RegionChooser regionChooser = new RegionChooser(new EnumRegionRepository(), this);
    private AddressAdapter<District> districtAdapter;
    private DistrictChooser districtChooser;
    private AddressAdapter<Province> provinceAdapter;
    private ProvinceChooser provinceChooser;
    private AddressAdapter<Subdistrict> subdistrictAdapter;
    private SubdistrictChooser subdistrictChooser;

    private InMemoryJsonProvinceRepository inMemoryJsonProvinceRepository;
    private InMemoryJsonDistrictRepository inMemoryJsonDistrictRepository;
    private InMemoryJsonSubdistrictRepository inMemoryJsonSubdistrictRepository;
    private Subdistrict subdistrictData;
    private District districtData;
    private Province provinceData;

    private int currentState = SELECT_REGION;

    private TextView titleView;
    private TextView statusInfoView;
    private String regionString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list_picker, container, false);
        initInstances(view);

        return view;
    }

    private void initInstances(View view) {
        titleView = (TextView) view.findViewById(R.id.title_text);
        statusInfoView = (TextView) view.findViewById(R.id.status_info);
        listView = (ListView) view.findViewById(R.id.picker_list);
        listView.setOnItemClickListener(this);

        setupRepository();
        bringToRegionList();
    }

    private void setupRepository() {
        inMemoryJsonProvinceRepository = InMemoryJsonProvinceRepository.getInstance(getActivity());
        inMemoryJsonDistrictRepository = InMemoryJsonDistrictRepository.getInstance(getActivity());
        inMemoryJsonSubdistrictRepository = InMemoryJsonSubdistrictRepository.getInstance(getActivity());
    }


    private void bringToRegionList() {
        titleView.setVisibility(View.GONE);
        statusInfoView.setText(R.string.choose_region);
        regionChooser.showRegionList();
        listView.setAdapter(regionAdapter);
        currentState = SELECT_REGION;
    }

    @Override
    public void showRegionList(List<Region> regions) {
        List<String> regionStringList = mapToListOfString(regions);
        regionAdapter = new ArrayAdapter<>(getActivity(), R.layout.address_picker_list_item, regionStringList);
    }

    @Override
    public void showNotFoundRegion() {
        Toast.makeText(getActivity(), R.string.region_not_found, Toast.LENGTH_LONG).show();
    }

    public List<String> mapToListOfString(List<Region> regions) {
        List<String> stringList = new ArrayList<>();
        for (Region region : regions) {
            stringList.add(region.toString());
        }
        return stringList;
    }

    @Override
    public void showProvinceList(List<Province> provinces) {
        provinceAdapter = new AddressAdapter<Province>(getActivity(), provinces);
    }

    @Override
    public void showNotFoundProvince() {
        Toast.makeText(getActivity(), R.string.province_not_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDistrictList(List<District> districts) {
        districtAdapter = new AddressAdapter<District>(getActivity(), districts);
    }

    @Override
    public void showNotFoundDistrict() {
        Toast.makeText(getActivity(), R.string.district_not_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSubdistrictList(List<Subdistrict> subdistricts) {
        subdistrictAdapter = new AddressAdapter<Subdistrict>(getActivity(), subdistricts);
    }

    @Override
    public void showNotFoundSubdistrict() {
        Toast.makeText(getActivity(), R.string.subdistrict_not_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (currentState == SELECT_REGION) {
            regionString = regionAdapter.getItem(position);
            bringToProvinceList(regionString);
        } else if (currentState == SELECT_PROVINCE) {
            provinceData = provinceAdapter.getItem(position);
            bringToDistrictList(provinceData.getCode());
        } else if (currentState == SELECT_DISTRICT) {
            districtData = districtAdapter.getItem(position);
            bringToSubdistrictList(districtData.getCode());
        } else if (currentState == SELECT_SUBDISTRICT) {
            subdistrictData = subdistrictAdapter.getItem(position);
            AddressController addressController = new AddressController(inMemoryJsonSubdistrictRepository, inMemoryJsonDistrictRepository, inMemoryJsonProvinceRepository, this);
            addressController.showByAddressCode(subdistrictData.getCode());
        }
    }

    private void bringToProvinceList(String region) {
        titleView.setText(regionString);
        statusInfoView.setText(R.string.choose_province);
        titleView.setVisibility(View.VISIBLE);
        provinceChooser = new ProvinceChooser(inMemoryJsonProvinceRepository, this);
        provinceChooser.showProvinceListByRegion(Region.fromName(region));
        listView.setAdapter(provinceAdapter);
        currentState = SELECT_PROVINCE;
    }

    private void bringToDistrictList(String provinceCode) {
        titleView.setText(regionString.concat(" > ").concat(provinceData.getName()));
        statusInfoView.setText(R.string.choose_district);
        titleView.setVisibility(View.VISIBLE);
        districtChooser = new DistrictChooser(inMemoryJsonDistrictRepository, this);
        districtChooser.showDistrictListByProvinceCode(provinceCode);
        listView.setAdapter(districtAdapter);
        currentState = SELECT_DISTRICT;
    }

    private void bringToSubdistrictList(String districtCode) {
        titleView.setText(String.format(getString(R.string.breadcrumb_text), provinceData.getName(), districtData.getName()));
        statusInfoView.setText(R.string.choose_subdistrict);
        titleView.setVisibility(View.VISIBLE);
        subdistrictChooser = new SubdistrictChooser(inMemoryJsonSubdistrictRepository, this);
        subdistrictChooser.showSubdistrictListByDistrictCode(districtCode);
        listView.setAdapter(subdistrictAdapter);
        currentState = SELECT_SUBDISTRICT;
    }

    public void setOnAddressChangedListener(OnAddressChangedListener addressChangedListener) {
        this.addressChangedListener = addressChangedListener;
    }

    public void restoreAddressField(final Address address) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                regionString = address.getRegion().toString();
                provinceData = address.getProvince();
                subdistrictData = address.getSubdistrict();
                districtData = address.getDistrict();
                bringToSubdistrictList(address.getDistrictCode());
            }
        });
    }


    @Override
    public void displayAddressInfo(Address address) {
        bringAddressValueToAddressView(address);
    }

    private void bringAddressValueToAddressView(Address addressData) {
        if (addressChangedListener != null)
            addressChangedListener.onAddressChanged(addressData);
        dismiss();
    }

    @Override
    public void alertAddressNotFound() {
        Toast.makeText(getActivity(), R.string.address_not_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (currentState == SELECT_REGION) {
                    if (addressChangedListener != null)
                        addressChangedListener.onAddressCanceled();
                    dismiss();
                } else if (currentState == SELECT_PROVINCE) {
                    bringToRegionList();
                } else if (currentState == SELECT_DISTRICT) {
                    bringToProvinceList(provinceData.getRegion().toString());
                } else if (currentState == SELECT_SUBDISTRICT) {
                    bringToDistrictList(districtData.getProvinceCode());
                }
            }
        };
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}