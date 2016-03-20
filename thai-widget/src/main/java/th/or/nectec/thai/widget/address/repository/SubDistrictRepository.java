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
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidDistrictCodeException;
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidSubDistrictCodeException;
import th.or.nectec.thai.address.SubDistrict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SubDistrictRepository implements AddressRepository<SubDistrict> {

    private static SubDistrictRepository instance;
    private final List<SubDistrict> allSubDistrict;

    private SubDistrictRepository(Context context) {
        allSubDistrict = JsonParser.parse(context, "subdistrict.json", SubDistrict.class);
        Collections.sort(allSubDistrict);
    }

    public static SubDistrictRepository getInstance(Context context) {
        if (instance == null) { //NOPMD
            instance = new SubDistrictRepository(context);
        }
        return instance;
    }

    @Override
    public List<SubDistrict> find() {
        return allSubDistrict;
    }

    @Override
    public List<SubDistrict> findByParentCode(String districtCode) {
        if (districtCode.length() != 4)
            throw new InvalidDistrictCodeException(districtCode);
        List<SubDistrict> querySubDistrict = new ArrayList<>();
        for (SubDistrict eachSubDistrict : allSubDistrict) {
            String queryAddressCode = eachSubDistrict.getDistrictCode();
            if (queryAddressCode.equals(districtCode)) {
                querySubDistrict.add(eachSubDistrict);
            }
        }
        return querySubDistrict.isEmpty() ? null : querySubDistrict;
    }

    @Override
    public SubDistrict findByCode(String subDistrictCode) {
        if (subDistrictCode.length() != 6)
            throw new InvalidSubDistrictCodeException(subDistrictCode);
        for (SubDistrict eachSubDistrict : allSubDistrict) {
            String queryAddressCode = eachSubDistrict.getCode();
            if (queryAddressCode.equals(subDistrictCode)) {
                return eachSubDistrict;
            }
        }
        return null;
    }
}
