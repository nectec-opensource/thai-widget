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

package th.or.nectec.thai.widget.address;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Button;
import th.or.nectec.thai.address.Address;
import th.or.nectec.thai.widget.R;
import th.or.nectec.thai.widget.address.repository.AddressRepositoryImpl;

public class AddressPicker extends Button implements AddressView {

    private Address address;
    private AddressRepositoryImpl addressRepository;
    private AddressPopup popup;
    private OnAddressChangedListener onAddressChangedListener;
    private OnAddressChangedListener onPopupAddressChangedListener = new OnAddressChangedListener() {
        @Override
        public void onAddressChanged(Address address) {
            setAddressCode(address.getCode());
            if (onAddressChangedListener != null)
                onAddressChangedListener.onAddressChanged(address);
        }

        @Override
        public void onAddressCanceled() {
            emptyView();
            if (onAddressChangedListener != null)
                onAddressChangedListener.onAddressCanceled();
        }
    };

    public AddressPicker(Context context) {
        this(context, null);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        emptyView();
        addressRepository = AddressRepositoryImpl.getInstance(context);
        popup = new AddressPickerDialog(context, onPopupAddressChangedListener);
    }

    private void emptyView() {
        address = null;
        setText(R.string.please_define_address);
    }

    public void setPopup(AddressPopup popup) {
        this.popup = popup;
        this.popup.setOnAddressChangedListener(onPopupAddressChangedListener);
    }

    @Override
    public boolean performClick() {
        popup.show(address);
        return true;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        if (address == null)
            return super.onSaveInstanceState();
        Parcelable parcelable = super.onSaveInstanceState();
        AddressSavedState savedState = new AddressSavedState(parcelable);
        savedState.addressCode = address.getCode();
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof AddressSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        AddressSavedState ss = (AddressSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setAddressCode(ss.addressCode);
    }

    @Override
    public void setAddressCode(String addressCode) {
        address = addressRepository.findByCode(addressCode);
        if (address != null)
            setText(address.getName());
    }

    @Override
    public void setOnAddressChangedListener(OnAddressChangedListener onAddressChangedListener) {
        this.onAddressChangedListener = onAddressChangedListener;
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
