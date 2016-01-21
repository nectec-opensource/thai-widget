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

import static org.junit.Assert.assertEquals;

public class CitizenIdTest {

    private boolean invalid = false;
    private boolean valid = true;

    @Test
    public void InvalidFormatCitizenId() {
        assertEquals("Not Only Digit citizen-id, must be invalid", invalid, CitizenId.isValid("1a3456789101b"));
        assertEquals("citizen-id length more than 13, must be invalid", invalid, CitizenId.isValid("12345678910124"));
        assertEquals("citizen-id length less than 13, must be invalid", invalid, CitizenId.isValid("00000"));
    }

    @Test
    public void validId() {
        assertEquals("1610255800005 should be valid", valid, CitizenId.isValid("1610255800005"));
        assertEquals("1610255811112 should be valid", valid, CitizenId.isValid("1610255811112"));
        assertEquals("1610255822220 should be valid", valid, CitizenId.isValid("1610255822220"));
        assertEquals("pretty format id should be valid", valid, CitizenId.isValid("1-6102-55800-00-5"));
    }

    @Test
    public void invalIdWithValidFormat() {
        assertEquals("0012300000000 should be invalid", invalid, CitizenId.isValid("0012300000000"));
        assertEquals("0012300000000 should be invalid", invalid, CitizenId.isValid("0012300000000"));
    }

    @Test
    public void prettyPrint() {
        CitizenId cid;
        cid = new CitizenId("1610255822220");
        assertEquals("pretty Print not as except ", "1-6102-55822-22-0", cid.prettyPrint());
        cid = new CitizenId("1610255811112");
        assertEquals("pretty Print not as except ", "1-6102-55811-11-2", cid.prettyPrint());
        cid = new CitizenId("161025");
        assertEquals("pretty Print not as except ", "1-6102-5", cid.prettyPrint());
    }

    @Test
    public void getCheckDigit() {
        CitizenId cid;
        cid = new CitizenId("0000000000003");
        assertEquals("check digit should be 3", 3, cid.getCheckDigit());
        cid = new CitizenId("0000000000005");
        assertEquals("check digit should be 5", 5, cid.getCheckDigit());
        cid = new CitizenId("000000000000x");
        assertEquals("check digit should be -1", -1, cid.getCheckDigit());
    }

    @Test
    public void calculateCheckDigit() {
        CitizenId cid;
        cid = new CitizenId("161025581111");
        assertEquals("calculate check digit result should be 2", 2, cid.calculateCheckDigit());
        cid = new CitizenId("161025580000");
        assertEquals("calculate check digit result should be 5", 5, cid.calculateCheckDigit());
    }

    @Test
    public void equals() {
        CitizenId c1 = new CitizenId("161025581111");
        CitizenId c2 = new CitizenId("161025581111");
        assertEquals("2 Object with same id should be equal", c1, c2);

    }

    @Test
    public void repeatNumberWithValidCheckDigit() {
        CitizenId cid;
        cid = new CitizenId("0000000000001");
        assertEquals("repeat number with valid check digit should be Invalid", invalid, cid.validate());
        cid = new CitizenId("1234123412340");
        assertEquals("repeat pattern number with valid check digit should be Invalid", invalid, cid.validate());
    }

}