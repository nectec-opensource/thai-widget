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

package th.or.nectec.android.widget.thai;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AddressPickerDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String ADDRESS_CODE = "address_code";
    FragmentManager fragmentManager;
    Button backButton, nextButton;
    RegionListFragment regionListFragment;
    private String addressCode;

    public AddressPickerDialogFragment() {
        // Required empty public constructor
    }

    public static AddressPickerDialogFragment newInstance(String addressCode) {
        AddressPickerDialogFragment fragment = new AddressPickerDialogFragment();
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
        fragmentManager.beginTransaction().add(R.id.container, regionListFragment, RegionListFragment.FRAGMENT_TAG).commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {

        } else if (id == R.id.next) {
            String region = TextUtils.isEmpty(regionListFragment.getRegion()) ? "ไปเลือกภูมิภาคก่อนเลย" : regionListFragment.getRegion();
            Toast.makeText(getActivity(), region, Toast.LENGTH_LONG).show();
        }
    }
}
