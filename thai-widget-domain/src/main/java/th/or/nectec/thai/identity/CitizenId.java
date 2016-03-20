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

package th.or.nectec.thai.identity;

import th.or.nectec.util.TextUtils;

/**
 * A that represent an identity of thai citizen
 */
public class CitizenId implements Identity {

    private static final int[] MULTIPLIER_TABLE = {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int LENGTH = 13;

    private final String id;
    private final PrettyPrinter printer = new IdPrettyPrinter() {
        @Override
        boolean positionToInsertSeparatorBefore(int position) {
            switch (position) {
                case 1:
                case 5:
                case 10:
                case 12:
                    return true;
            }
            return false;
        }
    };

    public CitizenId(String id) {
        if (id == null)
            throw new IllegalArgumentException("id must not be null");
        id = id.replace(printer.separator(), "");
        this.id = id;
    }

    public static boolean isValid(String id) {
        CitizenId cid = new CitizenId(id);
        return cid.validate();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitizenId)) return false;

        CitizenId citizenId = (CitizenId) o;
        return id.equals(citizenId.id);
    }

    @Override
    public String toString() {
        return printer.print(id);
    }

    @Override
    public boolean isValidFormat() {
        return id.length() == LENGTH && TextUtils.isDigitOnly(id);

    }


    @Override
    public boolean validate() {
        return isValidFormat() && !isRepeatNumber() && calculateCheckDigit() == getCheckDigit();

    }


    private boolean isRepeatNumber() {
        String idWithoutCheckDigit = id.substring(0, 12);
        return TextUtils.isRepeatingNumber(idWithoutCheckDigit) || TextUtils.isRepeatPatternNumber(idWithoutCheckDigit);
    }

    protected int getCheckDigit() {
        int lastIndex = CitizenId.LENGTH - 1;
        return Character.digit(id.charAt(lastIndex), 10);
    }

    protected int calculateCheckDigit() {
        int sum = 0;
        for (int position = 0; position < CitizenId.LENGTH - 1; position++) {
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
