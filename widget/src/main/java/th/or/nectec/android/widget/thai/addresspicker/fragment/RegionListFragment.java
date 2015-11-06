/*
 * Copyright (c) 2015 NECTEC
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

package th.or.nectec.android.widget.thai.addresspicker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import th.or.nectec.android.widget.thai.R;
import th.or.nectec.android.widget.thai.addresspicker.repository.EnumRegionRepository;
import th.or.nectec.domain.thai.address.region.RegionChooser;
import th.or.nectec.domain.thai.address.region.RegionPresenter;
import th.or.nectec.entity.thai.Region;

import java.util.ArrayList;
import java.util.List;


public class RegionListFragment extends Fragment {

    public static final String FRAGMENT_TAG = "region_list";

    private static final String ADDRESS_CODE = "address_code";
    ListView regionList;
    ArrayAdapter<String> regionAdapter;

    RegionChooser regionChooser;
    RegionPresenter regionPresenter = new RegionPresenter() {
        @Override
        public void showRegionList(List<Region> regions) {
            List<String> regionStringList = mapToListOfString(regions);
            regionAdapter = new ArrayAdapter(getActivity(), R.layout.address_picker_list_item, regionStringList);
        }

        @Override
        public void showNotFoundRegion() {
            Toast.makeText(getActivity(), "ไม่พบภูมิภาค", Toast.LENGTH_LONG).show();
        }

        public List<String> mapToListOfString(List<Region> regions) {
            List<String> stringList = new ArrayList<>();
            for (Region region : regions) {
                stringList.add(region.toString());
            }
            return stringList;
        }
    };

    public RegionListFragment() {
        // Required empty public constructor
    }

    public static RegionListFragment newInstance(String addressCode) {
        RegionListFragment fragment = new RegionListFragment();
        Bundle args = new Bundle();
        args.putString(ADDRESS_CODE, addressCode);
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
        regionList = (ListView) view.findViewById(R.id.picker_list);
        regionChooser = new RegionChooser(new EnumRegionRepository(), regionPresenter);
        regionChooser.showRegionList();
        regionList.setAdapter(regionAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public String getRegion() {
        return regionList.getCheckedItemPosition() == -1 ? null : regionAdapter.getItem(regionList.getCheckedItemPosition());
    }
}
