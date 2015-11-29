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

package th.or.nectec.domain.thai.address.subdistrict;

import java.util.List;

import th.or.nectec.entity.thai.Address;

public class SubdistrictController {
    private final SubdistrictRepository subdistrictRepository;
    private final SubdistrictPresenter subdistrictListPresenter;

    public SubdistrictController(SubdistrictRepository subdistrictRepository, SubdistrictPresenter subdistrictPresenter) {
        this.subdistrictRepository = subdistrictRepository;
        this.subdistrictListPresenter = subdistrictPresenter;
    }

    public void showSubDistrictInfoByAddressCode(String addressCode) {
        Address addressInfo = subdistrictRepository.findByAddressCode(addressCode);
        if (addressInfo != null) {
            subdistrictListPresenter.showSubdistrictInfo(addressInfo);
        } else {
            subdistrictListPresenter.showNotFoundSubdistrict();
        }
    }

    public void showSubDistrictInfoByAddressData(String subdistrict, String district, String province) {
        Address addressInfo = subdistrictRepository.findByAddressInfo(subdistrict, district, province);
        if (addressInfo != null) {
            subdistrictListPresenter.showSubdistrictInfo(addressInfo);
        } else {
            subdistrictListPresenter.showNotFoundSubdistrict();
        }
    }
}
