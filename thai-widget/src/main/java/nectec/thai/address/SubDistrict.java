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

package nectec.thai.address;

import nectec.util.TextUtils;

public class SubDistrict implements AddressEntity, Comparable<SubDistrict> {
    private final String code;
    private final String name;
    private final String postcode;

    public SubDistrict(String code, String name, String postcode) {
        if (code.length() != 6 || !TextUtils.isDigitOnly(code))
            throw new InvalidAddressCodeFormatException.InvalidSubDistrictCodeException(code);
        this.code = code;
        if (postcode.length() != 5 || !TextUtils.isDigitOnly(postcode))
            throw new InvalidAddressCodeFormatException("Invalid postcode format", postcode);
        this.postcode = postcode;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDistrictCode() {
        return code.substring(0, 4);
    }

    public String getProvinceCode() {
        return code.substring(0, 2);
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        SubDistrict otherSubDistrict = (SubDistrict) other;
        return code.equals(otherSubDistrict.code) && name.equals(otherSubDistrict.name);
    }

    @Override
    public String toString() {
        return "SubDistrict{" + "code='" + code + '\''
                + ", name='" + name + '\''
                + ", postcode='" + postcode + '\''
                + '}';
    }

    @Override
    public int compareTo(SubDistrict that) {
        return this.name.compareTo(that.name);
    }
}
