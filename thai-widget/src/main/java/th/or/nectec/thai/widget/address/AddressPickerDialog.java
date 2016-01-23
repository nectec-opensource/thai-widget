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

import java.util.Iterator;
import java.util.List;

public class AddressPickerDialog extends Dialog implements AddressPopup, AdapterView.OnItemClickListener {

    private ArrayStack<AddressEntity> addressStack = new ArrayStack<>();
    private TextView header;
    private TextView breadcrumb;
    private ListView list;
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

    public AddressPickerDialog setRepository(ProvinceRepository province) {
        this.provinceRepository = province;
        return this;
    }

    public AddressPickerDialog setRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
        return this;
    }

    public AddressPickerDialog setRepository(SubDistrictRepository subDistrictRepository) {
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

    private void showProvinceList() {
        header.setText(R.string.choose_province);
        List<Province> provinces = provinceRepository.find();
        setListAdapter(new AddressListAdapter<>(getContext(), provinces));
    }

    private void setListAdapter(AddressListAdapter addressListAdapter) {
        this.addressListAdapter = addressListAdapter;
        list.setAdapter(addressListAdapter);
    }

    private void showSubDistrictList(String code) {
        header.setText(R.string.choose_subdistrict);
        List<SubDistrict> subDistricts = subDistrictRepository.findByParentCode(code);
        setListAdapter(new AddressListAdapter<>(getContext(), subDistricts));
    }

    private void showDistrictList(String code) {
        List<District> districts = districtRepository.findByParentCode(code);
        setListAdapter(new AddressListAdapter<>(getContext(), districts));
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
    public void setOnAddressChangedListener(AddressView.OnAddressChangedListener onAddressChangedListener) {
        this.onAddressChangedListener = onAddressChangedListener;
    }


}
