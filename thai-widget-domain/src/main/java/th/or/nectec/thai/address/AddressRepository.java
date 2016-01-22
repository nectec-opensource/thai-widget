package th.or.nectec.thai.address;

import java.util.List;


public interface AddressRepository<T extends AddressEntity> {

    List<T> find();

    List<T> findByParentCode(String code);

    T findByCode(String code);
}
