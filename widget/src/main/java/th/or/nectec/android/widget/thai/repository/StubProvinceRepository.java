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

package th.or.nectec.android.widget.thai.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import th.or.nectec.domain.thai.address.province.ProvinceRepository;
import th.or.nectec.entity.ThaiAddress;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class StubProvinceRepository implements ProvinceRepository {

    ArrayList<ThaiAddress> allProvince = new ArrayList<>();

    public StubProvinceRepository(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("RefAddress.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new Gson();

            String currentProvinceCode = "";
            reader.beginArray();
            while (reader.hasNext()) {
                ThaiAddress eachAddress = gson.fromJson(reader, ThaiAddress.class);
                String readingProvinceCode = eachAddress.getAddressCode().substring(0, 2);
                currentProvinceCode = currentProvinceCode.equals(readingProvinceCode) ? currentProvinceCode : readingProvinceCode;
                if (!eachAddress.getAddressCode().startsWith(currentProvinceCode)) {
                    allProvince.add(eachAddress);
                }

            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ThaiAddress> findByRegion(String region) {
        List<ThaiAddress> queryProvince = new ArrayList<>();
        for (ThaiAddress eachProvince : allProvince) {
            String queryRegion = eachProvince.getRegion();
            if (queryRegion.equals(region)) {
                queryProvince.add(eachProvince);
            }
        }
        return queryProvince.isEmpty() ? null : queryProvince;
    }
}