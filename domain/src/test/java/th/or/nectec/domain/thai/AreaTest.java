/*
 * Copyright © 2015 NECTEC
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

package th.or.nectec.domain.thai;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AreaTest {

    @Test
    public void squareMeterToRaiNganSquareWa() throws Exception {
        Area rai = Area.fromSquareMeter(1600);
        assertEquals(1,rai.getRai());
        assertEquals(0,rai.getNgan());
        assertEquals(0,rai.getSquareWa());
    }

    @Test
    public void testToString() throws Exception {
        Area rai = Area.fromSquareMeter(1600);
        assertEquals("1-0-0", rai.toString());
    }

    @Test
    public void testEqual() throws Exception {
        Area area1 = Area.fromSquareMeter(1600);
        Area area2 = Area.fromRaiNganSqaureWa(1, 0, 0);
        assertEquals(area1, area2);
    }

    @Test
    public void RaiNganSquareWaToSquareMeter() throws Exception {
        Area rai = Area.fromRaiNganSqaureWa(1, 0, 0);
        assertEquals(1600, rai.getSquareMeter());
        assertEquals(1,rai.getRai());
        assertEquals(0,rai.getNgan());
        assertEquals(0,rai.getSquareWa());
    }

    @Test
    public void prettyPrint() throws Exception {
        assertEquals("1 ไร่", Area.fromRaiNganSqaureWa(1, 0, 0).prettyPrint());
        assertEquals("2 งาน", Area.fromRaiNganSqaureWa(0, 2, 0).prettyPrint());
        assertEquals("1 ตารางวา", Area.fromRaiNganSqaureWa(0, 0, 1).prettyPrint());
        assertEquals("1 ไร่ 99 ตารางวา", Area.fromRaiNganSqaureWa(0, 0, 499).prettyPrint());
        assertEquals("2 ไร่ 3 งาน 99 ตารางวา", Area.fromRaiNganSqaureWa(0, 7, 499).prettyPrint());
    }

    @Test
    public void rounding() throws Exception {
        Area ex1 = Area.fromSquareMeter(2400);
        assertEquals(1, ex1.getRai());
        assertEquals(2, ex1.getNgan());
        assertEquals(0, ex1.getSquareWa());

        Area ex2 = Area.fromSquareMeter(1599);
        assertEquals(1, ex2.getRai());
        assertEquals(0, ex2.getNgan());
        assertEquals(0, ex2.getSquareWa());

        Area ex3 = Area.fromSquareMeter(1605);
        assertEquals(1, ex3.getRai());
        assertEquals(0, ex3.getNgan());
        assertEquals(1, ex3.getSquareWa());

        Area ex4 = Area.fromSquareMeter(1601);
        assertEquals(1, ex4.getRai());
        assertEquals(0, ex4.getNgan());
        assertEquals(0, ex4.getSquareWa());
    }


}