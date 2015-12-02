package th.or.nectec.android.widget.thai.addresspicker.handler;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.addresspicker.AddressPickerDialogFragment;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonDistrictRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonProvinceRepository;
import th.or.nectec.android.widget.thai.addresspicker.repository.InMemoryJsonSubdistrictRepository;
import th.or.nectec.domain.thai.ThaiAddressPrinter;
import th.or.nectec.domain.thai.address.AddressController;
import th.or.nectec.domain.thai.address.AddressPresenter;
import th.or.nectec.entity.thai.Address;

public class AddressPickerHandler implements OnAddressChangedListener, AddressPresenter {
    private Context context;
    private Activity activity;
    private AddressPickerDialogFragment addressPickerDialogFragment;
    private OnAddressChangedListener onAddressChangedListener;
    private AddressController addressController;
    private TextView textView;
    private Address address;

    public AddressPickerHandler(TextView view, Context context) {
        this.textView = view;
        this.context = context;
        init();
    }

    public void init() {
        if (this.context instanceof Activity) {
            activity = (Activity) this.context;
        }

        if (activity == null)
            return;

        FragmentManager fragmentManager = activity.getFragmentManager();
        AddressPickerDialogFragment addressPickerDialogFragment = (AddressPickerDialogFragment) fragmentManager.findFragmentByTag(AddressPickerDialogFragment.FRAGMENT_TAG);

        if (addressPickerDialogFragment != null) {
            this.addressPickerDialogFragment = addressPickerDialogFragment;
        } else {
            this.addressPickerDialogFragment = new AddressPickerDialogFragment();
        }

        this.addressPickerDialogFragment.setOnAddressChangedListener(this);

        addressController = new AddressController(InMemoryJsonSubdistrictRepository.getInstance(context), new InMemoryJsonDistrictRepository(context), new InMemoryJsonProvinceRepository(context), this);
        textView.setText("กรุณาระบุ ตำบล อำเภอ จังหวัด");
    }

    @Override
    public void displayAddressInfo(Address address) {
        retrieveAddress(address);
    }

    @Override
    public void alertAddressNotFound() {
        Toast.makeText(context, "ไม่พบข้อมูลของที่อยู่", Toast.LENGTH_LONG).show();
    }

    public boolean performClick() {
        boolean handle = false;
        if (this.addressPickerDialogFragment != null) {
            FragmentManager fm = activity.getFragmentManager();

            if (!this.addressPickerDialogFragment.isAdded()) {
                this.addressPickerDialogFragment.show(fm, "dialog");
                handle = true;
                if (address != null) {
                    this.addressPickerDialogFragment.restoreAddressField(address);
                }
            }
        }
        return handle;
    }

    public void setAddressCode(String addressCode) {
        addressController.showByAddressCode(addressCode);
    }

    public void setAddress(String subdistrict, String district, String province) {
        addressController.showByAddressInfo(subdistrict, district, province);
    }

    @Override
    public void onAddressChanged(Address address) {
        retrieveAddress(address);
    }

    @Override
    public void onAddressCanceled() {
        if (onAddressChangedListener != null)
            onAddressChangedListener.onAddressCanceled();
    }

    private void retrieveAddress(Address address) {
        this.address = address;
        textView.setText(ThaiAddressPrinter.buildShortAddress(address.getSubdistrict(), address.getDistrict(), address.getProvince()));
        if (onAddressChangedListener != null)
            onAddressChangedListener.onAddressChanged(address);
    }

    public void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener) {
        this.onAddressChangedListener = onAddressChangedListener;
    }

    public Address getAddress() {
        return address;
    }
}