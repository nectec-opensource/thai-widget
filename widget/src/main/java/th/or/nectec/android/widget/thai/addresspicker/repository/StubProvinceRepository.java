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

package th.or.nectec.android.widget.thai.addresspicker.repository;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import th.or.nectec.domain.thai.address.province.ProvinceRepository;
import th.or.nectec.entity.thai.Address;
import th.or.nectec.entity.thai.Region;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class StubProvinceRepository implements ProvinceRepository {

    ArrayList<Address> allProvince = new ArrayList<>();

    public StubProvinceRepository(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("province.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new Gson();

            reader.beginArray();
            while (reader.hasNext()) {
                Address message = gson.fromJson(reader, Address.class);
                allProvince.add(message);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> findByRegion(String region) {
        Region targetRegion = Region.fromName(region);
        List<Address> queryProvince = new ArrayList<>();
        for (Address eachProvince : allProvince) {
            Region queryRegion = eachProvince.getRegion();
            if (queryRegion.equals(targetRegion)) {
                queryProvince.add(eachProvince);
            }
        }
        return queryProvince.isEmpty() ? null : queryProvince;
    }
}