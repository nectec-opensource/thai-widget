package th.or.nectec.domain.thai;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import th.or.nectec.domain.thai.address.AddressController;
import th.or.nectec.domain.thai.address.AddressPresenter;
import th.or.nectec.domain.thai.address.district.DistrictRepository;
import th.or.nectec.domain.thai.address.province.ProvinceRepository;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictRepository;
import th.or.nectec.entity.thai.Address;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.Province;
import th.or.nectec.entity.thai.Region;
import th.or.nectec.entity.thai.Subdistrict;


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
        address.setAddressCode(addressCode);
        address.setSubdistrict(subdistrictName);
        address.setDistrict(districtName);
        address.setProvince(provinceName);
        address.setRegion(Region.CENTER);
    }

    @Test
    public void testFindByAddressCode() throws Exception {

        context.checking(new Expectations() {{
            oneOf(subdistrictRepository).findByAddressCode(with(addressCode));
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
            oneOf(subdistrictRepository).findByAddressCode(with(addressCode));
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
