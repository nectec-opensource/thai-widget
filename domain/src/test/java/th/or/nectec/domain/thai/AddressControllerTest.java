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

package th.or.nectec.domain.thai;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import th.or.nectec.domain.thai.address.*;
import th.or.nectec.entity.thai.*;

import java.util.ArrayList;


public class AddressControllerTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private SubdistrictRepository subdistrictRepository;
    private DistrictRepository districtRepository;
    private ProvinceRepository provinceRepository;
    private AddressPresenter addressPresenter;
    private String addressCode;
    private Subdistrict subdistrict;
    private District district;
    private Province province;
    private Address address;
    private String subdistrictName;
    private String districtName;
    private String provinceName;

    @Before
    public void setup() {
        subdistrictRepository = context.mock(SubdistrictRepository.class);
        districtRepository = context.mock(DistrictRepository.class);
        provinceRepository = context.mock(ProvinceRepository.class);
        addressPresenter = context.mock(AddressPresenter.class);

        addressCode = "100101";
        subdistrictName = "พระบรมมหาราชวัง";
        subdistrict = new Subdistrict(addressCode, subdistrictName);

        String districtCode = "1001";
        districtName = "พระนคร";
        district = new District(districtCode, districtName);

        String provinceCode = "10";
        provinceName = "กรุงเทพมหานคร";
        Region provinceRegion = Region.CENTER;
        province = new Province(provinceCode, provinceName, provinceRegion);

        address = new Address();
        address.setSubdistrict(subdistrict);
        address.setDistrict(district);
        address.setProvince(province);
        address.setRegion(Region.CENTER);
    }

    @Test
    public void testFindByAddressCode() throws Exception {

        context.checking(new Expectations() {{
            oneOf(subdistrictRepository).findBySubdistrictCode(with(addressCode));
            will(returnValue(subdistrict));
            oneOf(districtRepository).findByDistrictCode(with(subdistrict.getDistrictCode()));
            will(returnValue(district));
            oneOf(provinceRepository).findByProvinceCode(with(subdistrict.getProvinceCode()));
            will(returnValue(province));
            oneOf(addressPresenter).displayAddressInfo(with(address));
        }});

        AddressController addressController = new AddressController(subdistrictRepository, districtRepository, provinceRepository, addressPresenter);
        addressController.showByAddressCode(addressCode);
    }

    @Test
    public void testAddressNotFoundByAddressCode() throws Exception {

        context.checking(new Expectations() {{
            oneOf(subdistrictRepository).findBySubdistrictCode(with(addressCode));
            will(returnValue(null));
            oneOf(addressPresenter).alertAddressNotFound();
        }});

        AddressController addressController = new AddressController(subdistrictRepository, districtRepository, provinceRepository, addressPresenter);
        addressController.showByAddressCode(addressCode);
    }

    @Test
    public void testFindByAddressInfo() throws Exception {

        final ArrayList<Subdistrict> subdistricts = new ArrayList<>();
        subdistricts.add(subdistrict);

        context.checking(new Expectations() {{
            oneOf(subdistrictRepository).findByName(with(subdistrictName));
            will(returnValue(subdistricts));
            oneOf(districtRepository).findByDistrictCode(with(subdistrict.getDistrictCode()));
            will(returnValue(district));
            oneOf(provinceRepository).findByProvinceCode(with(subdistrict.getProvinceCode()));
            will(returnValue(province));
            oneOf(addressPresenter).displayAddressInfo(with(address));
        }});

        AddressController addressController = new AddressController(subdistrictRepository, districtRepository, provinceRepository, addressPresenter);
        addressController.showByAddressInfo(subdistrictName, districtName, provinceName);
    }

    @Test
    public void testAddressNotFoundByAddressInfo() throws Exception {

        context.checking(new Expectations() {{
            oneOf(subdistrictRepository).findByName(with(subdistrictName));
            will(returnValue(null));
            oneOf(addressPresenter).alertAddressNotFound();
        }});

        AddressController addressController = new AddressController(subdistrictRepository, districtRepository, provinceRepository, addressPresenter);
        addressController.showByAddressInfo(subdistrictName, districtName, provinceName);
    }
}
