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

package th.or.nectec.thai.address;


import th.or.nectec.util.TextUtils;

public class AddressPrinter {

    public static String printFull(SubDistrict subDistrict, District district, Province province) {
        return printFull(subDistrict.getName(), district.getName(), province.getName());
    }

    public static String printFull(String subdistrict, String district, String province) {
        String address = "";
        if (TextUtils.isEmpty(province)) {
            if (province.equals("กรุงเทพมหานคร")) {
                if (TextUtils.isEmpty(subdistrict)) {
                    address += "แขวง" + subdistrict + " ";
                }
                if (TextUtils.isEmpty(district)) {
                    address += "เขต" + district + " ";
                }
                address += province;
            } else {
                if (TextUtils.isEmpty(subdistrict)) {
                    address += "ตำบล" + subdistrict + " ";
                }
                if (TextUtils.isEmpty(district)) {
                    address += "อำเภอ" + district + " ";
                }
                address += "จังหวัด" + province;
            }
        }
        return address;
    }

    public static String print(SubDistrict subDistrict, District district, Province province) {
        return print(subDistrict.getName(), district.getName(), province.getName());
    }

    public static String print(String subdistrict, String district, String province) {
        String address = "";
        if (TextUtils.isEmpty(province)) {
            if (province.equals("กรุงเทพมหานคร")) {
                if (TextUtils.isEmpty(subdistrict)) {
                    address += "แขวง" + subdistrict + " ";
                }
                if (TextUtils.isEmpty(district)) {
                    address += "เขต" + district + " ";
                }
                address += province;

            } else {
                if (TextUtils.isEmpty(subdistrict)) {
                    address += "ต." + subdistrict + " ";
                }
                if (TextUtils.isEmpty(district)) {
                    address += "อ." + district + " ";
                }
                address += "จ." + province;
            }
        }
        return address;
    }


}
