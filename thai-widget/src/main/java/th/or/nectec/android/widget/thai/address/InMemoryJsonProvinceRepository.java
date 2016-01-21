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
import th.or.nectec.domain.thai.address.ProvinceRepository;
import th.or.nectec.entity.thai.Province;
import th.or.nectec.entity.thai.Region;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class InMemoryJsonProvinceRepository implements ProvinceRepository {

    private static InMemoryJsonProvinceRepository instance;
    ArrayList<Province> allProvince = new ArrayList<>();

    private InMemoryJsonProvinceRepository(Context context) {
        allProvince = JsonAdapter.parse(context, "province.json", Province.class);
    }

    public static InMemoryJsonProvinceRepository getInstance(Context context) {
        if (instance == null)
            instance = new InMemoryJsonProvinceRepository(context);
        return instance;
    }

    @Override
    public List<Province> findByRegion(Region targetRegion) {
        List<Province> queryProvince = new ArrayList<>();
        for (Province eachProvince : allProvince) {
            if (eachProvince.getRegion().equals(targetRegion)) {
                queryProvince.add(eachProvince);
            }
        }
        return queryProvince.isEmpty() ? null : queryProvince;
    }

    @Override
    public Province findByProvinceCode(String provinceCode) {
        for (Province eachProvince : allProvince) {
            if (eachProvince.getCode().equals(provinceCode)) {
                return eachProvince;
            }
        }
        return null;
    }

    @Override
    public List<Province> find() {
        return allProvince;
    }
}