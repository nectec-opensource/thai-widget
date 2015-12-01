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

package th.or.nectec.android.widget.thai.addresspicker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.adapter.SubdistrictAdapter;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonDistrictRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonSubdistrictRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.JsonProvinceRepository;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictChooser;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictListPresenter;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.Subdistrict;


public class SubdistrictListFragment extends Fragment {

    public static final String FRAGMENT_TAG = "subdistrict_list";

    private static final String DISTRICT_CODE = "district_code";
    private ListView listView;
    private TextView addressInfo;
    private SubdistrictAdapter subdistrictAdapter;

    private SubdistrictChooser subdistrictChooser;
    private SubdistrictListPresenter subdistrictListPresenter = new SubdistrictListPresenter() {
        @Override
        public void showSubdistrictList(List<Subdistrict> districts) {
            subdistrictAdapter = new SubdistrictAdapter(getActivity(), districts);
        }

        @Override
        public void showNotFoundSubdistrict() {
            Toast.makeText(getActivity(), "ไม่พบตำบล", Toast.LENGTH_LONG).show();
        }
    };

    public static SubdistrictListFragment newInstance(String provinceCode) {
        SubdistrictListFragment fragment = new SubdistrictListFragment();
        Bundle args = new Bundle();
        args.putString(DISTRICT_CODE, provinceCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list_picker, container, false);
        initInstances(view);
        setupStageHeader();
        return view;
    }

    private void initInstances(View view) {
        addressInfo = (TextView) view.findViewById(R.id.address_info);
        listView = (ListView) view.findViewById(R.id.picker_list);
        subdistrictChooser = new SubdistrictChooser(InMemoryJsonSubdistrictRepository.getInstance(getActivity()), subdistrictListPresenter);
        subdistrictChooser.showSubdistrictListByDistrictCode(getDistrictCode());
        listView.setAdapter(subdistrictAdapter);
    }

    private String getDistrictCode() {
        return getArguments().getString(DISTRICT_CODE);
    }

    private void setupStageHeader() {
        District district = new InMemoryJsonDistrictRepository(getActivity()).findByDistrictCode(getDistrictCode());
        String province = new JsonProvinceRepository(getActivity()).findByProvinceCode(district.getProvinceCode()).getName();
        addressInfo.setText(String.format(getResources().getString(R.string.breadcrumb_text), province, district.getName()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Subdistrict getData() {
        int checkedItemPosition = listView.getCheckedItemPosition();
        if (listView.getCheckedItemPosition() == -1) {
            return null;
        } else {
            return subdistrictAdapter.getItem(checkedItemPosition);
        }
    }
}
