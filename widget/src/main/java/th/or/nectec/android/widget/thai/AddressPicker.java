/*
 * Copyright 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
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

package th.or.nectec.android.widget.thai;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import th.or.nectec.domain.thai.ThaiAddressPrinter;
import th.or.nectec.entity.ThaiAddress;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class AddressPicker extends Button implements AddressView, AddressChangedListener {

    ThaiAddress thaiAddress;

    public AddressPicker(Context context) {
        super(context);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        setOnAddressChangedListener(this);
    }

    @Override
    public void setAddressCode(String addressCode) {

    }

    @Override
    public void setAddress(String subdistrict, String district, String province) {
        setText(ThaiAddressPrinter.buildShortAddress(subdistrict, district, province));
    }

    @Override
    public void setOnAddressChangedListener(AddressChangedListener addressChangedListener) {

    }

    @Override
    public ThaiAddress getAddress() {
        return thaiAddress;
    }

    @Override
    public void onAddressChanged(ThaiAddress thaiAddress) {
        this.thaiAddress = thaiAddress;
    }

    @Override
    public void onAddressCanceled() {
        this.thaiAddress = null;
    }
}
