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

package th.or.nectec.thai.widget.address.repository;

import android.content.Context;
import th.or.nectec.thai.address.AddressRepository;
import th.or.nectec.thai.address.District;
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidDistrictCodeException;
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidProvinceCodeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistrictRepository implements AddressRepository<District> {

    private static DistrictRepository instance;
    private List<District> allDistrict = new ArrayList<>();

    private DistrictRepository(Context context) {
        allDistrict = JsonParser.parse(context, "district.json", District.class);
        Collections.sort(allDistrict);
    }

    public static DistrictRepository getInstance(Context context) {
        if (instance == null)
            instance = new DistrictRepository(context);
        return instance;
    }

    @Override
    public List<District> find() {
        return allDistrict;
    }

    @Override
    public List<District> findByParentCode(String provinceCode) {
        if (provinceCode.length() != 2)
            throw new InvalidProvinceCodeException(provinceCode);
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
    public District findByCode(String districtCode) {
        if (districtCode.length() != 4)
            throw new InvalidDistrictCodeException(districtCode);

        for (District eachDistrict : allDistrict) {
            String queryDistrictCode = eachDistrict.getCode();
            if (queryDistrictCode.startsWith(districtCode)) {
                return eachDistrict;
            }
        }
        return null;
    }
}
