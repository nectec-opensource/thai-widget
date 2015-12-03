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

package th.or.nectec.entity.thai;

import th.or.nectec.util.TextUtils;

public class Subdistrict implements AddressEntity {
    String code;
    String name;
    String postcode;


    public Subdistrict(String code, String name) {
        setCode(code);
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.length() == 6 && TextUtils.isDigitOnly(code))
            this.code = code;
        else
            throw new InvalidCodeFormatException();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subdistrict that = (Subdistrict) o;

        return code.equals(that.code) && name.equals(that.name)
                && !(postcode != null ? !postcode.equals(that.postcode) : that.postcode != null);
    }

    @Override
    public String toString() {
        return "Address{" +
                "code='" + code + '\'' +
                ", name='" + name;
    }
}
