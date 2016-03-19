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

import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidDistrictCodeException;
import th.or.nectec.util.TextUtils;

public class District implements AddressEntity, Comparable<District> {
    private String code;
    private String name;

    public District(String code, String name) {
        setCode(code);
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.length() != 4 || !TextUtils.isDigitOnly(code))
            throw new InvalidDistrictCodeException(code);
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getProvinceCode() {
        return code.substring(0, 2);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        District otherDistrict = (District) other;

        return code.equals(otherDistrict.code) && name.equals(otherDistrict.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("District{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(District that) {
        return this.name.compareTo(that.name);
    }
}
