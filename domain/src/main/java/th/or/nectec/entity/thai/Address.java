/*
 * Copyright (c) 2015 NECTEC
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

package th.or.nectec.entity.thai;

public class Address {
    String addressCode;
    String subdistrict;
    String district;
    String province;
    String postcode;
    Region region;

    @Override
    public String toString() {
        return "Address{" +
                "addressCode='" + addressCode + '\'' +
                ", subdistrict='" + subdistrict + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", postcode='" + postcode + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getDistrictCode() {
        return addressCode.substring(0, 4);
    }

    public String getProvinceCode() {
        return addressCode.substring(0, 2);
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!addressCode.equals(address.addressCode)) return false;
        if (!subdistrict.equals(address.subdistrict)) return false;
        if (!district.equals(address.district)) return false;
        if (!province.equals(address.province)) return false;
        if (postcode != null ? !postcode.equals(address.postcode) : address.postcode != null)
            return false;
        if (region != address.region) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressCode.hashCode();
        result = 31 * result + subdistrict.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + region.hashCode();
        return result;
    }
}
