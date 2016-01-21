/*
 * Copyright © 2015 NECTEC
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

package th.or.nectec.android.widget.thai.address;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import th.or.nectec.domain.thai.address.DistrictRepository;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.InvalidCodeFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class InMemoryJsonDistrictRepository implements DistrictRepository {

    public static InMemoryJsonDistrictRepository instance;
    ArrayList<District> allDistrict = new ArrayList<>();

    public InMemoryJsonDistrictRepository(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("district.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new Gson();

            reader.beginArray();
            while (reader.hasNext()) {
                District message = gson.fromJson(reader, District.class);
                allDistrict.add(message);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InMemoryJsonDistrictRepository getInstance(Context context) {
        if (instance == null)
            instance = new InMemoryJsonDistrictRepository(context);
        return instance;
    }

    @Override
    public List<District> findByProvinceCode(String provinceCode) {
        if (provinceCode.length() != 2)
            throw new InvalidCodeFormatException();

        List<District> queryDistrict = new ArrayList<>();
        for (District eachDistrict : allDistrict) {
            String queryDistrictCode = eachDistrict.getCode();
            if (queryDistrictCode.startsWith(provinceCode)) {
                queryDistrict.add(eachDistrict);
            }
        }
        return queryDistrict.isEmpty() ? null : queryDistrict;
    }

    @Override
    public District findByDistrictCode(String districtCode) {
        if (districtCode.length() != 4)
            throw new InvalidCodeFormatException();

        for (District eachDistrict : allDistrict) {
            String queryDistrictCode = eachDistrict.getCode();
            if (queryDistrictCode.startsWith(districtCode)) {
                return eachDistrict;
            }
        }
        return null;
    }
}