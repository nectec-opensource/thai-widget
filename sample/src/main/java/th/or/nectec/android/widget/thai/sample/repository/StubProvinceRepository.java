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

package th.or.nectec.android.widget.thai.sample.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import th.or.nectec.domain.thai.address.province.ProvinceRepository;
import th.or.nectec.entity.ThaiAddress;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class StubProvinceRepository implements ProvinceRepository {

    Context context;
    ArrayList<ThaiAddress> allProvince = new ArrayList<>();

    public StubProvinceRepository() {
        ThaiAddress subdistrict1 = new ThaiAddress();
        subdistrict1.setAddressCode("140605");
        subdistrict1.setSubDistrict("บางกระสั้น");
        subdistrict1.setDistrict("บางปะอิน");
        subdistrict1.setProvince("พระนครศรีอยุธยา");
        subdistrict1.setPostCode("13160");
        subdistrict1.setRegion("ภาคกลาง");
        allProvince.add(subdistrict1);

        ThaiAddress subdistrict2 = new ThaiAddress();
        subdistrict2.setAddressCode("140602");
        subdistrict2.setSubDistrict("เชียงรากน้อย");
        subdistrict2.setDistrict("บางปะอิน");
        subdistrict2.setProvince("พระนครศรีอยุธยา");
        subdistrict2.setPostCode("13180");
        subdistrict2.setRegion("ภาคกลาง");
        allProvince.add(subdistrict2);

        ThaiAddress subdistrict3 = new ThaiAddress();
        subdistrict3.setAddressCode("140601");
        subdistrict3.setSubDistrict("บ้านเลน");
        subdistrict3.setDistrict("บางปะอิน");
        subdistrict3.setProvince("พระนครศรีอยุธยา");
        subdistrict3.setPostCode("13160");
        subdistrict3.setRegion("ภาคกลาง");
        allProvince.add(subdistrict3);

        ThaiAddress subdistrict4 = new ThaiAddress();
        subdistrict4.setAddressCode("140611");
        subdistrict4.setSubDistrict("เกาะเกิด");
        subdistrict4.setDistrict("บางปะอิน");
        subdistrict4.setProvince("พระนครศรีอยุธยา");
        subdistrict4.setPostCode("13160");
        subdistrict4.setRegion("ภาคกลาง");
        allProvince.add(subdistrict4);

        ThaiAddress subdistrict5 = new ThaiAddress();
        subdistrict5.setAddressCode("140811");
        subdistrict5.setSubDistrict("เชียงรากน้อย");
        subdistrict5.setDistrict("บางไทร");
        subdistrict5.setProvince("พระนครศรีอยุธยา");
        subdistrict5.setPostCode("13160");
        subdistrict5.setRegion("ภาคกลาง");
        allProvince.add(subdistrict5);


        ThaiAddress subdistrict6 = new ThaiAddress();
        subdistrict6.setAddressCode("100101");
        subdistrict6.setSubDistrict("พระบรมมหาราชวัง");
        subdistrict6.setDistrict("พระนคร");
        subdistrict6.setProvince("กรุงเทพมหานคร");
        subdistrict6.setPostCode("10200");
        subdistrict6.setRegion("ภาคกลาง");
        allProvince.add(subdistrict6);

        ThaiAddress subdistrict7 = new ThaiAddress();
        subdistrict7.setAddressCode("210302");
        subdistrict7.setSubDistrict("วังหว้า");
        subdistrict7.setDistrict("แกลง");
        subdistrict7.setProvince("ระยอง");
        subdistrict7.setPostCode("21110");
        subdistrict7.setRegion("ภาคตะวันออก");
        allProvince.add(subdistrict7);
    }

    @Override
    public List<ThaiAddress> findByRegion(String region) {
        List<ThaiAddress> queryProvince = new ArrayList<>();
        for (ThaiAddress eachProvince : allProvince) {
            String queryAddressCode = eachProvince.getAddressCode();
            if (queryAddressCode.equals(region)) {
                queryProvince.add(eachProvince);
            }
        }
        return queryProvince.isEmpty() ? null : queryProvince;
    }
}