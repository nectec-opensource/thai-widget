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

package th.or.nectec.domain.thai;

import th.or.nectec.util.TextUtils;

public class CitizenId {

    private static final int[] MULTIPLIER_TABLE = {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static int LENGTH = 13;

    private String id;

    public CitizenId(String id) {
        this.id = id;
    }

    public static boolean isValid(String id) {
        CitizenId cid = new CitizenId(id);
        return cid.validate();
    }

    public boolean validate() {
        if (id.length() != LENGTH)
            return false;

        if (!TextUtils.isDigitOnly(id))
            return false;

        return calculateCheckDigit() == getCheckDigit();
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
        int n13 = (11 - x) % 10;
        return n13;
    }

    public String prettyPrint() {
        return printer.print(id);
    }

    private PrettyPrinter printer = new IdPrettyPrinter() {
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
}
