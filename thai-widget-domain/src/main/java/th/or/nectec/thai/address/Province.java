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

package th.or.nectec.thai.address;

import th.or.nectec.thai.address.InvalidAddressCodeFormatException.InvalidProvinceCodeException;
import th.or.nectec.util.TextUtils;

public class Province implements AddressEntity, Comparable<Province> {

    private String code;
    private String name;
    private Region region;


    public Province(String code, String name, Region region) {
        setCode(code);
        this.name = name;
        this.region = region;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.length() != 2 || !TextUtils.isDigitOnly(code))
            throw new InvalidProvinceCodeException(code);
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Province otherProvince = (Province) other;
        return code.equals(otherProvince.code) && name.equals(otherProvince.name) && region == otherProvince.region;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Province{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", region=").append(region);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Province that) {
        return this.name.compareTo(that.name);
    }
}
