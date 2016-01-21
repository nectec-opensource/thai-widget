package th.or.nectec.android.widget.thai.address;

import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.entity.thai.Address;

public interface AddressPopup {
    void show(Address address);
    void show(String addressCode);
    void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener);
}
