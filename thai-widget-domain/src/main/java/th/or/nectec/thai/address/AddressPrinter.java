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


public final class AddressPrinter {

    private AddressPrinter() {
    }

    public static String printFull(SubDistrict subDistrict, District district, Province province) {
        return printFull(subDistrict.getName(), district.getName(), province.getName());
    }

    public static String printFull(String subdistrict, String district, String province) {
        if ("กรุงเทพมหานคร".equals(province)) {
            return String.format("แขวง%s เขต%s กรุงเทพมหานคร", subdistrict, district);
        } else {
            return String.format("ตำบล%s อำเภอ%s จังหวัด%s", subdistrict, district, province);
        }
    }

    public static String print(SubDistrict subDistrict, District district, Province province) {
        return print(subDistrict.getName(), district.getName(), province.getName());
    }

    public static String print(String subdistrict, String district, String province) {
        if ("กรุงเทพมหานคร".equals(province)) {
            return String.format("แขวง%s เขต%s กรุงเทพมหานคร", subdistrict, district);
        } else {
            return String.format("ต.%s อ.%s จ.%s", subdistrict, district, province);
        }
    }


}
