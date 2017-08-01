/*
 * Copyright (c) 2016 NECTEC
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
 *
 */

package nectec.thai.widget.address.repository;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import th.or.nectec.thai.address.AddressRepository;
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidProvinceCodeException;
import th.or.nectec.thai.address.Province;
import th.or.nectec.thai.address.Region;

public final class ProvinceRepository implements AddressRepository<Province> {

    private static ProvinceRepository instance;
    private final List<Province> allProvince;

    private ProvinceRepository(Context context) {
        allProvince = JsonParser.parse(context, "province.json", Province.class);
        Collections.sort(allProvince);
    }

    public static ProvinceRepository getInstance(Context context) {
        if (instance == null)//NOPMD
            instance = new ProvinceRepository(context);
        return instance;
    }

    @Override
    public List<Province> find() {
        return allProvince;
    }

    @Override
    public List<Province> findByParentCode(String regionName) {
        Region region = Region.fromName(regionName);
        List<Province> queryProvince = new ArrayList<>();
        for (Province eachProvince : allProvince) {
            if (eachProvince.getRegion().equals(region)) {
                queryProvince.add(eachProvince);
            }
        }
        return queryProvince.isEmpty() ? null : queryProvince;
    }

    @Override
    public Province findByCode(String provinceCode) {
        if (provinceCode.length() != 2)
            throw new InvalidProvinceCodeException(provinceCode);
        for (Province eachProvince : allProvince) {
            if (eachProvince.getCode().equals(provinceCode)) {
                return eachProvince;
            }
        }
        return null;
    }
}
