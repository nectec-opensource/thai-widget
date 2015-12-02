package th.or.nectec.domain.thai.address;

import java.util.ArrayList;

import th.or.nectec.domain.thai.address.district.DistrictRepository;
import th.or.nectec.domain.thai.address.province.ProvinceRepository;
import th.or.nectec.domain.thai.address.subdistrict.SubdistrictRepository;
import th.or.nectec.entity.thai.Address;
import th.or.nectec.entity.thai.District;
import th.or.nectec.entity.thai.Province;
import th.or.nectec.entity.thai.Subdistrict;

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
        Subdistrict subdistrict = subdistrictRepository.findByAddressCode(addressCode);
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
        ArrayList<Subdistrict> subdistricts = subdistrictRepository.findByName(subdistrict);
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
