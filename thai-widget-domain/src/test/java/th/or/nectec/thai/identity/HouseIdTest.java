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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HouseIdTest {

    @Test
    public void validFormatId() {
        HouseId hid = new HouseId("12345678901");
        assertTrue("12345678901 is valid format id", hid.isValidFormat());
    }

    @Test
    public void acceptPrettyId() {
        HouseId hid = new HouseId("1234-567890-1");
        assertTrue("1234-567890-1 is valid format", hid.isValidFormat());
    }

    @Test
    public void setInvalidFormatId() {
        HouseId hid;
        hid = new HouseId("123456");
        assertFalse("Id's length 6 must be invalid", hid.isValidFormat());
        hid = new HouseId("1234567890");
        assertFalse("Id's length 10 must be invalid", hid.isValidFormat());
        hid = new HouseId("");
        assertFalse("Id's length 0 must be invalid", hid.isValidFormat());
        hid = new HouseId("123456789012");
        assertFalse("Id's length 12 must be invalid", hid.isValidFormat());
        hid = new HouseId("123456789012345");
        assertFalse("Id's length 15 must be invalid", hid.isValidFormat());
        hid = new HouseId("ab34/6789x1");
        assertFalse("Not only digit must be invalid", hid.isValidFormat());
        hid = new HouseId("1234x678901");
        assertFalse("Not only digit must be invalid", hid.isValidFormat());
        hid = new HouseId("-----------");
        assertFalse("only slash sign must be invalid", hid.isValidFormat());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullIdException() {
        new HouseId(null);
    }

    @Test
    public void checkDigit() {
        HouseId hid;
        hid = new HouseId("1234x678901");
        assertFalse("test id must be invalid format id", hid.isValidFormat());
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
        assertFalse("test id must be invalid format id", hid.isValidFormat());
        assertFalse("validate invalid format id must be invalid", hid.validate());

        hid = new HouseId("74020749965");
        assertTrue("Valid Id must return valid", hid.validate());

        hid = new HouseId("12040847961");
        assertTrue("Valid Id must return valid", hid.validate());

        hid = new HouseId("2-7920-01391-7");
        assertTrue("Valid Id in pretty format must be valid", hid.validate());

        hid = new HouseId("11111111111");
        assertFalse("repeating number must be invalid", hid.validate());
    }

    @Test
    public void prettyPrint() {
        HouseId hid;
        hid = new HouseId("74020749965");
        assertEquals("pretty print should work", "7402-074996-5", hid.prettyPrint());
        hid = new HouseId("7402074");
        assertEquals("pretty print should work with not complete id", "7402-074", hid.prettyPrint());
        hid = new HouseId("7402");
        assertEquals("pretty print should work with not complete id", "7402", hid.prettyPrint());
        hid = new HouseId("7402074996");
        assertEquals("pretty print should work with not complete id", "7402-074996", hid.prettyPrint());
    }

}
