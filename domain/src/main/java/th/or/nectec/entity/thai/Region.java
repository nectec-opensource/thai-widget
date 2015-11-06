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

import com.google.gson.annotations.SerializedName;

public enum Region {
    @SerializedName(Name.ภาคกลาง)CENTER(Name.ภาคกลาง),
    @SerializedName(Name.ภาคเหนือ)NORTH(Name.ภาคเหนือ),
    @SerializedName(Name.ภาคตะวันออกเฉียงเหนือ)EAST_NORTH(Name.ภาคตะวันออกเฉียงเหนือ),
    @SerializedName(Name.ภาคตะวันออก)EAST(Name.ภาคตะวันออก),
    @SerializedName(Name.ภาคตะวันตก)WEST(Name.ภาคตะวันตก),
    @SerializedName(Name.ภาคใต้)SOUTH(Name.ภาคใต้);


    private String regionName;

    Region(String regionName) {
        this.regionName = regionName;
    }

    public static Region fromName(String regionName) {
        switch (regionName) {
            case Name.ภาคกลาง:
                return Region.CENTER;
            case Name.ภาคเหนือ:
                return Region.NORTH;
            case Name.ภาคตะวันออกเฉียงเหนือ:
                return Region.EAST_NORTH;
            case Name.ภาคตะวันออก:
                return Region.EAST;
            case Name.ภาคตะวันตก:
                return Region.WEST;
            case Name.ภาคใต้:
                return Region.SOUTH;
            default:
                throw new IllegalArgumentException("input name not match any region name");
        }
    }

    @Override
    public String toString() {
        return this.regionName;
    }

    private static class Name {
        public static final String ภาคกลาง = "ภาคกลาง";
        public static final String ภาคเหนือ = "ภาคเหนือ";
        public static final String ภาคตะวันออกเฉียงเหนือ = "ภาคตะวันออกเฉียงเหนือ";
        public static final String ภาคตะวันออก = "ภาคตะวันออก";
        public static final String ภาคตะวันตก = "ภาคตะวันตก";
        public static final String ภาคใต้ = "ภาคใต้";
    }
}
