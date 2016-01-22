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

package th.or.nectec.thai.address;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ProvinceTest {


    private Province bangkok;

    @Before
    public void setUp() throws Exception {
        bangkok = new Province("10", "กรุงเทพมหานคร", Region.CENTER);
    }


    @Test(expected = InvalidAddressCodeFormatException.class)
    public void setInvalidProvinceCode() {
        Province bangkok = new Province("10", "กรุงเทพมหานคร", Region.CENTER);
        bangkok.setCode("102");
    }

    @Test
    public void getField() throws Exception {
        assertEquals("10", bangkok.getCode());
        assertEquals("กรุงเทพมหานคร", bangkok.getName());
        assertEquals(Region.CENTER, bangkok.getRegion());
    }
}
