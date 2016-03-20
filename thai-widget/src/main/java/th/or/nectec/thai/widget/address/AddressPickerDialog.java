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
import th.or.nectec.thai.widget.R;
import th.or.nectec.thai.widget.address.AddressView.OnAddressChangedListener;
import th.or.nectec.thai.widget.address.repository.AddressRepositoryImpl;
import th.or.nectec.thai.widget.address.repository.DistrictRepository;
import th.or.nectec.thai.widget.address.repository.ProvinceRepository;
import th.or.nectec.thai.widget.address.repository.SubDistrictRepository;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AddressPickerDialog extends Dialog implements AddressPopup, AdapterView.OnItemClickListener {

    private ArrayStack<AddressEntity> addressStack = new ArrayStack<>();
    private TextView header;
    private TextView breadcrumb;
    private ListView list;
    private AddressRepositoryImpl addressRepository;
    private AddressRepository<Province> provinceRepository;
    private AddressRepository<District> districtRepository;
    private AddressRepository<SubDistrict> subDistrictRepository;
    private OnAddressChangedListener onAddressChangedListener;
    private AddressListAdapter addressListAdapter;


    public AddressPickerDialog(Context context) {
        this(context, null);
    }

    public AddressPickerDialog(Context context, OnAddressChangedListener onAddressChangedListener) {
        this(context, 0, onAddressChangedListener);
    }

    public AddressPickerDialog(Context context, int themeResId, OnAddressChangedListener onAddressChangedListener) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        addressRepository = AddressRepositoryImpl.getInstance(context);
        provinceRepository = ProvinceRepository.getInstance(context);
        districtRepository = DistrictRepository.getInstance(context);
        subDistrictRepository = SubDistrictRepository.getInstance(context);

        this.onAddressChangedListener = onAddressChangedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_address_picker);
        breadcrumb = (TextView) findViewById(R.id.breadcrumb);
        header = (TextView) findViewById(R.id.header);
        list = (ListView) findViewById(R.id.picker_list);
        list.setOnItemClickListener(this);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        switchPage();
    }

    @Override
    public void onBackPressed() {
        if (addressStack.isEmpty()) {
            onAddressChangedListener.onAddressCanceled();
            dismiss();
            return;
        }
        addressStack.pop();
        switchPage();
    }

    public AddressPickerDialog setProvinceRepository(AddressRepository<Province> province) {
        this.provinceRepository = province;
        return this;
    }

    public AddressPickerDialog setDistrictRepository(AddressRepository<District> districtRepository) {
        this.districtRepository = districtRepository;
        return this;
    }

    public AddressPickerDialog setSubDistrictRepository(AddressRepository<SubDistrict> subDistrictRepository) {
        this.subDistrictRepository = subDistrictRepository;
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        addressStack.push(addressListAdapter.getItem(position));
        switchPage();
    }

    private void switchPage() {
        updateBreadCrumb();
        if (addressStack.isEmpty()) {
            List<Province> provinceList = provinceRepository.find();
            Collections.sort(provinceList);
            updateDialog(R.string.choose_province, provinceList);
            return;
        }
        AddressEntity choosedEntity = addressStack.peek();
        if (choosedEntity instanceof Province) {
            List<District> districtList = districtRepository.findByParentCode(choosedEntity.getCode());
            Collections.sort(districtList);
            updateDialog(R.string.choose_district, districtList);
        } else if (choosedEntity instanceof District) {
            List<SubDistrict> subDistrictList = subDistrictRepository.findByParentCode(choosedEntity.getCode());
            Collections.sort(subDistrictList);
            updateDialog(R.string.choose_subdistrict, subDistrictList);
        } else if (choosedEntity instanceof SubDistrict) {
            notifyAddressChange(choosedEntity.getCode());
            dismiss();
        }
    }

    private void updateBreadCrumb() {
        if (addressStack.isEmpty()) {
            breadcrumb.setText("");
            breadcrumb.setVisibility(View.GONE);
            return;
        }
        StringBuilder builder = new StringBuilder();
        Iterator<AddressEntity> iterator = addressStack.getIterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().getName());
            if (iterator.hasNext()) {
                builder.append("  >  ");
            }
        }
        breadcrumb.setText(builder.toString());
        breadcrumb.setVisibility(View.VISIBLE);
    }

    public void updateDialog(int headerStringResId, List<? extends AddressEntity> addressEntityList) {
        header.setText(headerStringResId);
        setListAdapter(new AddressListAdapter<>(getContext(), addressEntityList));
    }

    private void setListAdapter(AddressListAdapter addressListAdapter) {
        this.addressListAdapter = addressListAdapter;
        list.setAdapter(addressListAdapter);
    }

    private void notifyAddressChange(String code) {
        Address address = addressRepository.findByCode(code);
        onAddressChangedListener.onAddressChanged(address);
    }

    @Override
    public void show(Address area) {
        addressStack = new ArrayStack<>();
        if (area != null) {
            addressStack.push(area.getProvince());
            addressStack.push(area.getDistrict());
        }
        show();
    }

    @Override
    public void show(String addressCode) {
        show(addressRepository.findByCode(addressCode));
    }

    @Override
    public void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener) {
        this.onAddressChangedListener = onAddressChangedListener;
    }


}
