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

package th.or.nectec.thai.address;/*
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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegionTest {

    @Test
    public void testFromName() {
        assertEquals(Region.CENTER, Region.fromName("ภาคกลาง"));
        assertEquals(Region.EAST_NORTH, Region.fromName("ภาคตะวันออกเฉียงเหนือ"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFromName() {
        Region.fromName("กลาง");
    }

    @Test
    public void testToString() {
        assertEquals("ภาคเหนือ", Region.NORTH.toString());
        assertEquals("ภาคใต้", Region.SOUTH.toString());
    }

    @Test
    public void testEqual() {
        assertEquals(Region.NORTH, Region.NORTH);
        assertEquals(Region.NORTH, Region.fromName("ภาคเหนือ"));
    }
}
