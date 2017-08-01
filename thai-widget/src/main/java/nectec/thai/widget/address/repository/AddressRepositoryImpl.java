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
import th.or.nectec.thai.address.Address;
import th.or.nectec.thai.address.District;
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidSubDistrictCodeException;
import th.or.nectec.thai.address.Province;
import th.or.nectec.thai.address.SubDistrict;
import th.or.nectec.util.TextUtils;

public final class AddressRepositoryImpl {

    private static AddressRepositoryImpl instance;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final SubDistrictRepository subDistrictRepository;

    private AddressRepositoryImpl(SubDistrictRepository subDistrictRepository,
                                  DistrictRepository districtRepository,
                                  ProvinceRepository provinceRepository) {
        this.subDistrictRepository = subDistrictRepository;
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
    }

    public static AddressRepositoryImpl getInstance(Context context) {
        if (instance == null)//NOPMD
            instance = new AddressRepositoryImpl(SubDistrictRepository.getInstance(context),
                    DistrictRepository.getInstance(context),
                    ProvinceRepository.getInstance(context));
        return instance;
    }

    public Address findByCode(String subDistrictCode) {
        if (subDistrictCode.length() != 6 || !TextUtils.isDigitOnly(subDistrictCode))
            throw new InvalidSubDistrictCodeException(subDistrictCode);

        SubDistrict subDistrict = subDistrictRepository.findByCode(subDistrictCode);
        District district = districtRepository.findByCode(subDistrict.getDistrictCode());
        Province province = provinceRepository.findByCode(district.getProvinceCode());
        return new Address(subDistrict, district, province);
    }
}
