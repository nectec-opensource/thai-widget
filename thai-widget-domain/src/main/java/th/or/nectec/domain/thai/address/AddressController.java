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

package th.or.nectec.domain.thai.address;

import th.or.nectec.entity.thai.Address;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.Province;
import th.or.nectec.entity.thai.Subdistrict;

import java.util.List;

public class AddressController {
    private final SubdistrictRepository subdistrictRepository;
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private AddressPresenter addressPresenter;

    public AddressController(SubdistrictRepository subdistrictRepository, DistrictRepository districtRepository, ProvinceRepository provinceRepository, AddressPresenter addressPresenter) {
        this.subdistrictRepository = subdistrictRepository;
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
        this.addressPresenter = addressPresenter;
    }


    public void showByAddressCode(String addressCode) {
        Subdistrict subdistrict = subdistrictRepository.findBySubdistrictCode(addressCode);
        if (subdistrict == null) {
            addressPresenter.alertAddressNotFound();
            return;
        }
        District district = districtRepository.findByDistrictCode(subdistrict.getDistrictCode());
        Province province = provinceRepository.findByProvinceCode(subdistrict.getProvinceCode());

        Address address = new Address();
        address.setSubdistrict(subdistrict);
        address.setDistrict(district);
        address.setProvince(province);
        address.setRegion(province.getRegion());
        addressPresenter.displayAddressInfo(address);
    }

    public void showByAddressInfo(String subdistrict, String district, String province) {
        List<Subdistrict> subdistricts = subdistrictRepository.findByName(subdistrict);
        if (subdistricts == null) {
            addressPresenter.alertAddressNotFound();
            return;
        }
        for (Subdistrict eachSubdistrict : subdistricts) {
            District districtInfo = districtRepository.findByDistrictCode(eachSubdistrict.getDistrictCode());
            if (!districtInfo.getName().equals(district))
                continue;
            Province provinceInfo = provinceRepository.findByProvinceCode(eachSubdistrict.getProvinceCode());
            if (provinceInfo.getName().equals(province)) {
                Address address = new Address();
                address.setSubdistrict(eachSubdistrict);
                address.setDistrict(districtInfo);
                address.setProvince(provinceInfo);
                address.setRegion(provinceInfo.getRegion());
                addressPresenter.displayAddressInfo(address);
                return;
            }
        }
    }
}
