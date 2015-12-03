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
    Subdistrict subdistrict;
    District district;
    Province province;
    Region region;

    @Override
    public String toString() {
        return "Address{" +
                "addressCode='" + subdistrict.getCode() + '\'' +
                ", subdistrict='" + subdistrict.getName() + '\'' +
                ", district='" + district.getName() + '\'' +
                ", province='" + province.getName() + '\'' +
                ", postcode='" + subdistrict.getPostcode() + '\'' +
                ", region='" + region.toString() + '\'' +
                '}';
    }

    public Region getRegion() {
        return province.getRegion();
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getSubdistrictCode() {
        return subdistrict.getCode();
    }

    public String getDistrictCode() {
        return district.getCode();
    }

    public String getProvinceCode() {
        return province.getCode();
    }

    public Subdistrict getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(Subdistrict subdistrict) {
        this.subdistrict = subdistrict;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getPostcode() {
        return subdistrict.getPostcode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!subdistrict.equals(address.subdistrict)) return false;
        if (!district.equals(address.district)) return false;
        if (!province.equals(address.province)) return false;
        return region == address.region;

    }

    @Override
    public int hashCode() {
        int result = subdistrict.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }
}
