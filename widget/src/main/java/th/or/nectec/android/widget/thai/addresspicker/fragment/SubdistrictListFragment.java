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
import android.widget.Toast;

import java.util.List;

import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.adapter.SubdistrictAdapter;
import th.or.nectec.android.widget.thai.addresspicker.repository.JsonSubdistrictRepository;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictChooser;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictListPresenter;
import th.or.nectec.entity.thai.Address;


public class SubdistrictListFragment extends Fragment {

    public static final String FRAGMENT_TAG = "subdistrict_list";

    private static final String DISTRICT_CODE = "district_code";
    ListView listView;
    SubdistrictAdapter provinceAdapter;

    String districtCode;

    SubdistrictChooser subdistrictChooser;
    SubdistrictListPresenter subdistrictListPresenter = new SubdistrictListPresenter() {
        @Override
        public void showSubdistrictList(List<Address> districts) {
            provinceAdapter = new SubdistrictAdapter(getActivity(), districts);
        }

        @Override
        public void showNotFoundSubdistrict() {
            Toast.makeText(getActivity(), "ไม่พบตำบล", Toast.LENGTH_LONG).show();
        }
    };

    public SubdistrictListFragment() {
        // Required empty public constructor
    }

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

        return view;
    }

    private void initInstances(View view) {
        districtCode = getArguments().getString(DISTRICT_CODE);

        listView = (ListView) view.findViewById(R.id.picker_list);
        subdistrictChooser = new SubdistrictChooser(new JsonSubdistrictRepository(getActivity()), subdistrictListPresenter);
        subdistrictChooser.showSubdistrictListByDistrictCode(districtCode);
        listView.setAdapter(provinceAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Address getData() {
        return listView.getCheckedItemPosition() == -1 ? null : provinceAdapter.getItem(listView.getCheckedItemPosition());
    }
}
