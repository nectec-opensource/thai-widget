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
import th.or.nectec.android.widget.thai.addresspicker.adapter.ProvinceAdapter;
import th.or.nectec.android.widget.thai.addresspicker.repository.JsonProvinceRepository;
import th.or.nectec.domain.thai.address.province.ProvinceChooser;
import th.or.nectec.domain.thai.address.province.ProvincePresenter;
import th.or.nectec.entity.thai.Province;
import th.or.nectec.entity.thai.Region;


public class ProvinceListFragment extends Fragment {

    public static final String FRAGMENT_TAG = "province_list";

    private static final String REGION = "region";
    ListView listView;
    ProvinceAdapter provinceAdapter;

    ProvinceChooser provinceChooser;
    ProvincePresenter provincePresenter = new ProvincePresenter() {
        @Override
        public void showProvinceList(List<Province> provinces) {
            provinceAdapter = new ProvinceAdapter(getActivity(), provinces);
        }

        @Override
        public void showNotFoundProvince() {
            Toast.makeText(getActivity(), "ไม่พบจังหวัด", Toast.LENGTH_LONG).show();
        }
    };

    public ProvinceListFragment() {
        // Required empty public constructor
    }

    public static ProvinceListFragment newInstance(String region) {
        ProvinceListFragment fragment = new ProvinceListFragment();
        Bundle args = new Bundle();
        args.putString(REGION, region);
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
        listView = (ListView) view.findViewById(R.id.picker_list);
        provinceChooser = new ProvinceChooser(new JsonProvinceRepository(getActivity()), provincePresenter);
        provinceChooser.showProvinceListByRegion(getRegionFromArguments());
        listView.setAdapter(provinceAdapter);
    }

    private Region getRegionFromArguments() {
        String regionName = getArguments().getString(REGION);
        Region region = Region.fromName(regionName);
        return region;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Province getData() {
        return listView.getCheckedItemPosition() == -1 ? null : provinceAdapter.getItem(listView.getCheckedItemPosition());
    }
}
