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

package th.or.nectec.thai.widget.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import th.or.nectec.thai.address.Address;
import th.or.nectec.thai.widget.address.AddressPicker;
import th.or.nectec.thai.widget.address.AddressPickerDialog;
import th.or.nectec.thai.widget.address.AddressView;

public class AddressPickerSampleActivity extends AppCompatActivity {
    Address selectedAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_picker);

        ((AddressPicker) findViewById(R.id.address_view)).setPopup(new AddressPickerDialog(this, R.style.AppTheme, null));
        ((AddressPicker) findViewById(R.id.address_view_set)).setAddressCode("141604");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddressPickerDialog(AddressPickerSampleActivity.this, android.R.style.Theme_Holo_Dialog, new AddressView.OnAddressChangedListener() {
                    @Override
                    public void onAddressChanged(Address address) {
                        selectedAddress = address;
                    }

                    @Override
                    public void onAddressCanceled() {
                        selectedAddress = null;
                    }
                }).show(selectedAddress);
            }
        });

    }

}
