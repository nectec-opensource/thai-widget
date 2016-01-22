package th.or.nectec.thai.widget.address;

import th.or.nectec.thai.address.Address;

public interface AddressPopup {
    void show(Address address);

    void show(String addressCode);

    void setOnAddressChangedListener(AddressView.OnAddressChangedListener onAddressChangedListener);
}
