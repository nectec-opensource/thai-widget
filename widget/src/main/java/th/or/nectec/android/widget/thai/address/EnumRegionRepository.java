/*
 * Copyright (c) 2015 NECTEC
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

package th.or.nectec.android.widget.thai.address;

import th.or.nectec.domain.thai.address.RegionRepository;
import th.or.nectec.entity.thai.Region;

import java.util.ArrayList;
import java.util.List;

class EnumRegionRepository implements RegionRepository {
    private static final List<Region> REGIONS = new ArrayList<>();

    static {
        REGIONS.add(Region.CENTER);
        REGIONS.add(Region.NORTH);
        REGIONS.add(Region.EAST_NORTH);
        REGIONS.add(Region.EAST);
        REGIONS.add(Region.WEST);
        REGIONS.add(Region.SOUTH);
    }

    @Override
    public List<Region> find() {
        return REGIONS;
    }
}
