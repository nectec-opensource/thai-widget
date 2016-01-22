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

package th.or.nectec.thai.widget.address;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import th.or.nectec.thai.address.*;
import th.or.nectec.thai.widget.address.repository.AddressRepositoryImpl;
import th.or.nectec.thai.widget.address.repository.DistrictRepository;
import th.or.nectec.thai.widget.address.repository.ProvinceRepository;
import th.or.nectec.thai.widget.address.repository.SubDistrictRepository;
import th.or.nectec.thai.widget.thai.R;

import java.util.List;
import java.util.Stack;

public class AddressPickerDialog extends Dialog implements AddressPopup, AdapterView.OnItemClickListener {

    Stack<AddressEntity> addressStack = new Stack<>();
    private TextView statusInfoView;
    private TextView titleView;
    private ListView listView;
    private AddressRepositoryImpl addressRepository;
    private ProvinceRepository provinceRepository;
    private DistrictRepository districtRepository;
    private SubDistrictRepository subDistrictRepository;
    private AddressView.OnAddressChangedListener onAddressChangedListener;
    private AddressListAdapter addressListAdapter;


    public AddressPickerDialog(Context context) {
        this(context, null);
    }

    public AddressPickerDialog(Context context, AddressView.OnAddressChangedListener onAddressChangedListener) {
        this(context, 0, onAddressChangedListener);
    }

    public AddressPickerDialog(Context context, int themeResId, AddressView.OnAddressChangedListener onAddressChangedListener) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setOnAddressChangedListener(onAddressChangedListener);

        addressRepository = AddressRepositoryImpl.getInstance(context);
        provinceRepository = ProvinceRepository.getInstance(context);
        districtRepository = DistrictRepository.getInstance(context);
        subDistrictRepository = SubDistrictRepository.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_address_list_picker);
        titleView = (TextView) findViewById(R.id.title_text);
        statusInfoView = (TextView) findViewById(R.id.status_info);
        listView = (ListView) findViewById(R.id.picker_list);
        listView.setOnItemClickListener(this);
        switchPage();
    }

    @Override
    public void onBackPressed() {
        if (addressStack.empty()) {
            onAddressChangedListener.onAddressCanceled();
            dismiss();
            return;
        }
        addressStack.pop();
        switchPage();
    }


    private void switchPage() {
        if (addressStack.empty()) {
            showProvinceList();
            return;
        }
        AddressEntity choosedEntity = addressStack.peek();
        if (choosedEntity instanceof Province) {
            showDistrictList(choosedEntity.getCode());
        } else if (choosedEntity instanceof District) {
            showSubDistrictList(choosedEntity.getCode());
        } else if (choosedEntity instanceof SubDistrict) {
            notifyAddressChange(choosedEntity.getCode());
            dismiss();
        }
    }

    private void showSubDistrictList(String code) {
        titleView.setVisibility(View.VISIBLE);
        titleView.setText(makeTitle());
        statusInfoView.setText(R.string.choose_subdistrict);

        List<SubDistrict> subDistricts = subDistrictRepository.findByParentCode(code);
        setListAdapter(new AddressListAdapter<>(getContext(), subDistricts));
    }

    private String makeTitle() {
        return addressStack.peek().getName();
    }

    private void notifyAddressChange(String code) {
        Address address = addressRepository.findByCode(code);
        onAddressChangedListener.onAddressChanged(address);
    }

    private void showProvinceList() {
        titleView.setVisibility(View.GONE);
        statusInfoView.setText(R.string.choose_province);

        List<Province> provinces = provinceRepository.find();
        setListAdapter(new AddressListAdapter<>(getContext(), provinces));
    }

    private void setListAdapter(AddressListAdapter addressListAdapter) {
        this.addressListAdapter = addressListAdapter;
        listView.setAdapter(addressListAdapter);
    }

    private void showDistrictList(String code) {
        titleView.setText(addressStack.peek().getName());
        titleView.setVisibility(View.VISIBLE);
        statusInfoView.setText(R.string.choose_district);

        List<District> districts = districtRepository.findByParentCode(code);
        setListAdapter(new AddressListAdapter<>(getContext(), districts));
    }

    public AddressPickerDialog setRepository(ProvinceRepository province) {
        this.provinceRepository = province;
        return this;
    }

    @Override
    public void show(Address area) {
        addressStack = new Stack<>();
        if (area != null) {
            addressStack.push(area.getProvince());
            addressStack.push(area.getDistrict());
        }
        show();
    }

    public AddressPickerDialog setRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
        return this;
    }

    @Override
    public void show(String addressCode) {
        show(addressRepository.findByCode(addressCode));
    }

    public AddressPickerDialog setRepository(SubDistrictRepository subDistrictRepository) {
        this.subDistrictRepository = subDistrictRepository;
        return this;
    }

    @Override
    public void setOnAddressChangedListener(AddressView.OnAddressChangedListener onAddressChangedListener) {
        this.onAddressChangedListener = onAddressChangedListener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        AddressEntity choosedEntity = addressListAdapter.getItem(position);
        addressStack.push(choosedEntity);
        switchPage();
    }


}
