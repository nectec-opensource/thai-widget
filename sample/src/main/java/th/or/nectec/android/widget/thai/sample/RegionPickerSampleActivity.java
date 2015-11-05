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

package th.or.nectec.android.widget.thai.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import th.or.nectec.android.widget.thai.sample.repository.StubProvinceRepository;
import th.or.nectec.domain.thai.ThaiAddressPrinter;
import th.or.nectec.domain.thai.address.province.ProvinceChooser;
import th.or.nectec.domain.thai.address.province.ProvincePresenter;
import th.or.nectec.domain.thai.address.region.RegionChooser;
import th.or.nectec.domain.thai.address.region.RegionPresenter;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictChooser;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictPresenter;
import th.or.nectec.entity.ThaiAddress;

public class RegionPickerSampleActivity extends AppCompatActivity {

    TextView textView;
    RegionChooser regionChooser;
    RegionPresenter regionPresenter = new RegionPresenter() {
        @Override
        public void showRegionList(List<String> regions) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String region : regions) {
                stringBuilder.append(region);
                stringBuilder.append("\n");
            }

            textView.setText(stringBuilder);
        }

        @Override
        public void showNotFoundRegion() {
            textView.setText("ไม่เจอข้อมูลภูมิภาค");
        }
    };

    SubdistrictChooser subdistrictChooser;
    SubdistrictPresenter subdistrictPresenter = new SubdistrictPresenter() {
        @Override
        public void showSubdistrictList(List<ThaiAddress> subdistricts) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ThaiAddress eachSubdistrict : subdistricts) {
                stringBuilder.append(
                        ThaiAddressPrinter.buildShortAddress(
                                eachSubdistrict.getSubDistrict(), eachSubdistrict.getDistrict(), eachSubdistrict.getProvince()));
                stringBuilder.append("\n");
            }
            textView.setText(stringBuilder);
        }

        @Override
        public void showNotFoundSubdistrict() {
        }
    };

    ProvinceChooser provinceChooser;
    ProvincePresenter provincePresenter = new ProvincePresenter() {
        @Override
        public void showProvinceList(List<ThaiAddress> provinces) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ThaiAddress eachSubdistrict : provinces) {
                stringBuilder.append(
                        ThaiAddressPrinter.buildShortAddress(
                                eachSubdistrict.getSubDistrict(), eachSubdistrict.getDistrict(), eachSubdistrict.getProvince()));
                stringBuilder.append("\n");
            }
            textView.setText(stringBuilder);
        }

        @Override
        public void showNotFoundProvince() {
            textView.setText("ไม่เจอจังหวัด");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_picker);

        textView = (TextView) findViewById(R.id.text);
/*        regionChooser = new RegionChooser(new StubRegionRepository(), regionPresenter);
        regionChooser.showRegionList();*/

/*        subdistrictChooser = new SubdistrictChooser(new StubSubdistrictRepository(), subdistrictPresenter);
        subdistrictChooser.showSubdistrictByDistrictCodeList("1406");*/

        provinceChooser = new ProvinceChooser(new StubProvinceRepository(), provincePresenter);
        provinceChooser.showSubdistrictByDistrictCodeList("21");
    }

}
