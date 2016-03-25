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

import com.google.gson.annotations.SerializedName;

public enum Region {
    @SerializedName("ภาคกลาง")CENTER("ภาคกลาง"),
    @SerializedName("ภาคเหนือ")NORTH("ภาคเหนือ"),
    @SerializedName("ภาคตะวันออกเฉียงเหนือ")EAST_NORTH("ภาคตะวันออกเฉียงเหนือ"),
    @SerializedName("ภาคตะวันออก")EAST("ภาคตะวันออก"),
    @SerializedName("ภาคตะวันตก")WEST("ภาคตะวันตก"),
    @SerializedName("ภาคใต้")SOUTH("ภาคใต้");

    private final String regionName;

    Region(String regionName) {
        this.regionName = regionName;
    }

    public static Region fromName(String regionName) {
        switch (regionName) {
            case "ภาคกลาง":
                return Region.CENTER;
            case "ภาคเหนือ":
                return Region.NORTH;
            case "ภาคตะวันออกเฉียงเหนือ":
                return Region.EAST_NORTH;
            case "ภาคตะวันออก":
                return Region.EAST;
            case "ภาคตะวันตก":
                return Region.WEST;
            case "ภาคใต้":
                return Region.SOUTH;
            default:
                throw new IllegalArgumentException("input name not match any region name");
        }
    }

    @Override
    public String toString() {
        return this.regionName;
    }
}
