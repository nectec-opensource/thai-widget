/*
 * Copyright © 2016 NECTEC
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

package th.or.nectec.android.widget.thai.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import th.or.nectec.android.widget.thai.OnAddressChangedListener;
import th.or.nectec.android.widget.thai.address.AddressPicker;
import th.or.nectec.android.widget.thai.address.AddressPickerDialog;
import th.or.nectec.android.widget.thai.address.AppCompatAddressPicker;
import th.or.nectec.entity.thai.Address;

public class AddressPickerSampleActivity extends AppCompatActivity {

    TextView textView;
    AppCompatAddressPicker appCompatAddressPicker;
    AddressPicker addressPicker;
    private Button addressDialogCaller;
    private Address address;
    OnAddressChangedListener onAddressChangedListener = new OnAddressChangedListener() {


        @Override
        public void onAddressChanged(Address address) {
            AddressPickerSampleActivity.this.address = address;
            addressDialogCaller.setText(address.toString());
        }

        @Override
        public void onAddressCanceled() {
            Toast.makeText(AddressPickerSampleActivity.this, "address canceled", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_picker);

        textView = (TextView) findViewById(R.id.text_debug);
        appCompatAddressPicker = (AppCompatAddressPicker) findViewById(R.id.address_view);

        addressPicker = (AddressPicker) findViewById(R.id.address_view_2);
        addressPicker.setOnAddressChangedListener(new OnAddressChangedListener() {
            @Override
            public void onAddressChanged(Address address) {
                Toast.makeText(AddressPickerSampleActivity.this, address.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAddressCanceled() {
                Toast.makeText(AddressPickerSampleActivity.this, "address canceled", Toast.LENGTH_LONG).show();
            }
        });
        addressPicker.setAddressCode("141604");

        setupAddressDialogSample();
    }

    private void setupAddressDialogSample() {
        addressDialogCaller = (Button) findViewById(R.id.address_dialog);
        addressDialogCaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressPickerDialog dialog = new AddressPickerDialog(AddressPickerSampleActivity.this, onAddressChangedListener);
                if (address != null) {
                    dialog.show(address);
                } else {
                    dialog.show("100302");
                }
            }
        });
    }

}
