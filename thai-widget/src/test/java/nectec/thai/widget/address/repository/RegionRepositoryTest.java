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

package nectec.thai.widget.address.repository;

import java.util.List;
import nectec.thai.address.Region;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegionRepositoryTest {

    @Test
    public void testFind() throws Exception {
        List<Region> regions = RegionRepository.getInstance().find();

        assertEquals(6, regions.size());
    }

    @Test
    public void testFindMustFoundCenterRegion() throws Exception {
        List<Region> regions = RegionRepository.getInstance().find();

        assertTrue(regions.contains(Region.CENTER));
    }

    @Test
    public void testFindMustFoundNorthRegion() throws Exception {
        List<Region> regions = RegionRepository.getInstance().find();

        assertTrue(regions.contains(Region.NORTH));

    }
}
