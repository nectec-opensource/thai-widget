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

package th.or.nectec.entity;

/**
 * Created by N. Choatravee on 5/11/2558.
 */
public class ThaiAddress {
    String addressCode;
    String subDistrict;
    String district;
    String province;
    String postCode;

    @Override
    public String toString() {
        return "ThaiAddress{" +
                "addressCode='" + addressCode + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThaiAddress that = (ThaiAddress) o;

        if (!addressCode.equals(that.addressCode)) return false;
        if (!subDistrict.equals(that.subDistrict)) return false;
        if (!district.equals(that.district)) return false;
        if (!province.equals(that.province)) return false;
        return postCode.equals(that.postCode);
    }

    @Override
    public int hashCode() {
        int result = addressCode.hashCode();
        result = 31 * result + subDistrict.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + postCode.hashCode();
        return result;
    }
}
