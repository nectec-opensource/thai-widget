/*
 * Copyright (c) 2016 NECTEC
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
 *
 */

package th.or.nectec.thai.address;

public class Address implements AddressEntity {
    SubDistrict subDistrict;
    District district;
    Province province;

    public Address(SubDistrict subDistrict, District district, Province province) {
        this.subDistrict = subDistrict;
        this.district = district;
        this.province = province;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public District getDistrict() {
        return district;
    }

    public Province getProvince() {
        return province;
    }

    @Override
    public int hashCode() {
        return subDistrict.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Address otherAddress = (Address) other;
        return getCode().equals(otherAddress.getCode()) && getName().equals(otherAddress.getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("subDistrict=").append(subDistrict);
        sb.append(", district=").append(district);
        sb.append(", province=").append(province);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getCode() {
        return subDistrict.getCode();
    }

    @Override
    public String getName() {
        return AddressPrinter.print(subDistrict, district, province);
    }
}
