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

package th.or.nectec.android.widget.thai.address;

import android.content.Context;
import th.or.nectec.android.widget.thai.utils.JsonParser;
import th.or.nectec.domain.thai.address.SubdistrictRepository;
import th.or.nectec.entity.thai.InvalidCodeFormatException;
import th.or.nectec.entity.thai.Subdistrict;

import java.util.ArrayList;
import java.util.List;

class InMemoryJsonSubdistrictRepository implements SubdistrictRepository {

    private static final String SUBDISTRICT_JSON = "subdistrict.json";
    private static InMemoryJsonSubdistrictRepository instance;
    private List<Subdistrict> allSubdistrict = new ArrayList<>();

    public InMemoryJsonSubdistrictRepository(Context context) {
        allSubdistrict = JsonParser.list(context, SUBDISTRICT_JSON, Subdistrict.class);
    }

    public static InMemoryJsonSubdistrictRepository getInstance(Context context) {
        if (instance == null) {
            instance = new InMemoryJsonSubdistrictRepository(context);
        }
        return instance;
    }

    @Override
    public List<Subdistrict> findByDistrictCode(String districtCode) {
        if (districtCode.length() != 4)
            throw new InvalidCodeFormatException();
        List<Subdistrict> querySubdistrict = new ArrayList<>();
        for (Subdistrict eachSubdistrict : allSubdistrict) {
            String queryAddressCode = eachSubdistrict.getDistrictCode();
            if (queryAddressCode.equals(districtCode)) {
                querySubdistrict.add(eachSubdistrict);
            }
        }
        return querySubdistrict.isEmpty() ? null : querySubdistrict;
    }

    @Override
    public Subdistrict findBySubdistrictCode(String addressCode) {
        for (Subdistrict eachSubdistrict : allSubdistrict) {
            String queryAddressCode = eachSubdistrict.getCode();
            if (queryAddressCode.equals(addressCode)) {
                return eachSubdistrict;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Subdistrict> findByName(String subdistrict) {
        ArrayList<Subdistrict> subdistricts = new ArrayList<>();
        for (Subdistrict eachSubdistrict : allSubdistrict) {
            if (eachSubdistrict.getName().equals(subdistrict)) {
                subdistricts.add(eachSubdistrict);
            }
        }
        return subdistricts.isEmpty() ? null : subdistricts;
    }
}