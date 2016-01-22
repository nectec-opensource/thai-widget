package th.or.nectec.thai.widget.address.repository;


import android.content.Context;
import th.or.nectec.thai.address.Address;
import th.or.nectec.thai.address.District;
import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidSubDistrictCodeException;
import th.or.nectec.thai.address.Province;
import th.or.nectec.thai.address.SubDistrict;
import th.or.nectec.util.TextUtils;

public class AddressRepositoryImpl {

    private static AddressRepositoryImpl instance;
    private ProvinceRepository provinceRepository;
    private DistrictRepository districtRepository;
    private SubDistrictRepository subDistrictRepository;

    private AddressRepositoryImpl(SubDistrictRepository subDistrictRepository, DistrictRepository districtRepository, ProvinceRepository provinceRepository) {
        this.subDistrictRepository = subDistrictRepository;
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
    }

    public static AddressRepositoryImpl getInstance(Context context) {
        if (instance == null)
            instance = new AddressRepositoryImpl(SubDistrictRepository.getInstance(context), DistrictRepository.getInstance(context), ProvinceRepository.getInstance(context));
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
