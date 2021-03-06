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

import java.util.ArrayList;
import java.util.List;
import nectec.thai.address.Region;

public final class RegionRepository {

    private static RegionRepository instance = new RegionRepository();
    private final List<Region> regions = new ArrayList<>();

    private RegionRepository() {
        regions.add(Region.CENTER);
        regions.add(Region.NORTH);
        regions.add(Region.EAST_NORTH);
        regions.add(Region.EAST);
        regions.add(Region.WEST);
        regions.add(Region.SOUTH);
    }

    public static RegionRepository getInstance() {
        return instance;
    }

    public List<Region> find() {
        return regions;
    }
}
