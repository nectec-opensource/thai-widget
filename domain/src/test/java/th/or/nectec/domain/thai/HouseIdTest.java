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

import org.junit.Test;
import th.or.nectec.util.TextUtils;

import static org.junit.Assert.assertEquals;

public class HouseIdTest {

    private boolean invalid = false;
    private boolean valid = true;

    @Test
    public void validFormatId(){
       HouseId hid = new HouseId("12345678901");
       assertEquals("12345678901 is valid format id",valid, hid.isValidFormat());
    }

    @Test
    public void acceptPrettyId() {
        HouseId hid = new HouseId("1-2345-67890-1");
        assertEquals("1-2345-67890-1 is valid format", valid, hid.isValidFormat());
    }

    @Test
    public void setInvalidFormatId(){
        HouseId hid;
        hid = new HouseId("123456");
        assertEquals("Id's length 6 must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("1234567890");
        assertEquals("Id's length 10 must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("");
        assertEquals("Id's length 0 must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("123456789012");
        assertEquals("Id's length 12 must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("123456789012345");
        assertEquals("Id's length 15 must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("ab34/6789x1");
        assertEquals("Not only digit must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("1234x678901");
        assertEquals("Not only digit must be invalid", invalid, hid.isValidFormat());
        hid = new HouseId("-----------");
        assertEquals("only slash sign must be invalid", invalid, hid.isValidFormat());
    }

    @Test(expected = NullPointerException.class)
    public void nullIdException() {
        HouseId hid = new HouseId(null);
    }

    @Test
    public void checkDigit() {
        HouseId hid;
        hid = new HouseId("1234x678901");
        assertEquals("test id must be invalid format id", invalid, hid.isValidFormat());
        assertEquals("check digit of invalid format should be -1 ", -1, hid.getCheckDigit());
        hid = new HouseId("12345678901");
        assertEquals("check digit must be 1", 1, hid.getCheckDigit());
        hid = new HouseId("09876543210");
        assertEquals("check digit must be 0", 0, hid.getCheckDigit());
        hid = new HouseId("78901234567");
        assertEquals("check digit must be 7", 7, hid.getCheckDigit());
        hid = new HouseId("90123456789");
        assertEquals("check digit must be 9", 9, hid.getCheckDigit());
    }

    @Test
    public void validdateId() {
        HouseId hid;
        hid = new HouseId("123456789012345");
        assertEquals("test id must be invalid format id", invalid, hid.isValidFormat());
        assertEquals("validate invalid format id must be invalid", invalid, hid.validate());
        hid = new HouseId("74020749965");
        assertEquals("Valid Id must return valid", valid, hid.validate());
        hid = new HouseId("12040847961");
        assertEquals("Valid Id must return valid", valid, hid.validate());
        hid = new HouseId("2-7920-01391-7");
        assertEquals("Valid Id in pretty format must be valid", valid, hid.validate());

    }


    private static class HouseId {


        public static final int LENGTH = 11;
        private String id;

        public HouseId(String id) {
            if (id == null)
                throw new NullPointerException("ID must not be null");
            this.id = id.replace("-", "");
        }

        public  boolean isValidFormat() {
            return !(id.length() != LENGTH || !TextUtils.isDigitOnly(id));
        }

        protected int getCheckDigit() {
            if (!isValidFormat())
                return -1;
            int lastIndex = HouseId.LENGTH - 1;
            return Character.digit(id.charAt(lastIndex), 10);
        }

        public boolean validate() {
            return isValidFormat() && calculateCheckDigit() == getCheckDigit();

        }

        protected int calculateCheckDigit() {
            int sum = 0;
            for (int position = 0; position < LENGTH - 1; position++) {
                sum += Character.digit(id.charAt(position), 10) * MULTIPLIER_TABLE[position];
            }
            int x = sum % 11;
            int n11 = (11 - x) % 10;
            return n11;
        }


        private static final int[] MULTIPLIER_TABLE = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};


    }
}