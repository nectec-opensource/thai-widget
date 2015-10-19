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

import static org.junit.Assert.*;

public class CitizenIdTest {

    private boolean invalid = false;
    private boolean valid = true;

    @Test
    public void LengthLessThan13shouldInvalid(){

        assertEquals("citizen-id length less than 13, must be invalid", invalid, CitizenId.isValid("00000") );
    }

    @Test
    public void LengthMoreThan13shouldInvalid(){

        assertEquals("citizen-id length more than 13, must be invalid", invalid, CitizenId.isValid("12345678910124") );
    }

    @Test
    public void NotOnlyDigitIdShouldInvalid(){
        assertEquals("Not Only Digit citizen-id, must be invalid", invalid, CitizenId.isValid("1a3456789101b") );
    }

    @Test
    public void lastPositionIs3ThenCheckDigitShouldBe3(){
        CitizenId cid = new CitizenId("0000500005003");
        assertEquals("check digit should be 3", 3, cid.getCheckDigit());
    }

    @Test
    public void lastPositionIs5ThenCheckDigitShouldBe5(){
        CitizenId cid = new CitizenId("0000500005005");
        assertEquals("check digit should be 5", 5, cid.getCheckDigit());
    }

    @Test
    public void calculateCheckDigitShouldBe2(){
        CitizenId cid = new CitizenId("1610255811112");
        assertEquals("calculate check digit result should be 2", 2, cid.calculateCheckDigit());
    }

    @Test
    public void calculateCheckDigitShouldBe5(){
        CitizenId cid = new CitizenId("1610255800005");
        assertEquals("calculate check digit result should be 5", 5, cid.calculateCheckDigit());
    }




    @Test
    public void validIdShouldReturnValid(){

        assertEquals("1610255822220 should be valid", valid, CitizenId.isValid("1610255822220") );
    }

    @Test
    public void invalidIdShouldReturnInValid(){
        assertEquals("0012300000000 should be invalid", invalid, CitizenId.isValid("0012300000000") );
    }

    @Test
    public void prettyPrint(){
        CitizenId cid = new CitizenId("1610255822220");
        assertEquals("pretty Print not as except ", "1-6102-55822-22-0",cid.prettyPrint() );
    }

    @Test
    public void prettyPrint2(){
        CitizenId cid = new CitizenId("1610255811112");
        assertEquals("pretty Print not as except ", "1-6102-55811-11-2",cid.prettyPrint() );
    }

}