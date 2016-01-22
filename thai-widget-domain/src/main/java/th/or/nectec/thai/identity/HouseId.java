/*
 * Copyright © 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.or.nectec.thai.identity;

import th.or.nectec.util.TextUtils;

/**
 * A class that represent an identity of house in thailand
 */
public class HouseId implements Identity {


    public static final int LENGTH = 11;
    private static final int[] MULTIPLIER_TABLE = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private String id;
    private PrettyPrinter printer = new IdPrettyPrinter() {
        @Override
        boolean positionToInsertSeparatorBefore(int position) {
            switch (position) {
                case 4:
                case 10:
                    return true;
            }
            return false;
        }
    };

    public HouseId(String id) {
        if (id == null)
            throw new NullPointerException("ID must not be null");
        this.id = id.replace(printer.separator(), "");
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseId)) return false;

        HouseId houseId = (HouseId) o;
        return id.equals(houseId.id);
    }

    @Override
    public String toString() {
        return printer.print(id);
    }

    @Override
    public boolean isValidFormat() {
        return !(id.length() != LENGTH || !TextUtils.isDigitOnly(id));
    }


    protected int getCheckDigit() {
        if (!isValidFormat())
            return -1;
        int lastIndex = HouseId.LENGTH - 1;
        return Character.digit(id.charAt(lastIndex), 10);
    }


    @Override
    public boolean validate() {
        return isValidFormat() &&
                !TextUtils.isRepeatingNumber(id) &&
                calculateCheckDigit() == getCheckDigit();
    }

    protected int calculateCheckDigit() {
        int sum = 0;
        for (int position = 0; position < LENGTH - 1; position++) {
            sum += Character.digit(id.charAt(position), 10) * MULTIPLIER_TABLE[position];
        }
        int x = sum % 11;
        return (11 - x) % 10;
    }

    @Override
    public String prettyPrint() {
        return printer.print(id);
    }

    @Override
    public String getId() {
        return id;
    }


}
