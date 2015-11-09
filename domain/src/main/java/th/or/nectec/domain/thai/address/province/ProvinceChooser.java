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

package th.or.nectec.domain.thai.address.province;

import java.util.List;

import th.or.nectec.entity.thai.Address;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class ProvinceChooser {
    private final ProvinceRepository provinceRepository;
    private final ProvincePresenter provincePresenter;

    public ProvinceChooser(ProvinceRepository provinceRepository, ProvincePresenter provincePresenter) {
        this.provinceRepository = provinceRepository;
        this.provincePresenter = provincePresenter;
    }

    public void showProvinceListByRegion(String region) {
        List<Address> provinces = provinceRepository.findByRegion(region);
        if (provinces != null) {
            provincePresenter.showProvinceList(provinces);
        } else {
            provincePresenter.showNotFoundProvince();
        }
    }
}
