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

package nectec.thai.widget.unit;

import android.app.Activity;
import nectec.thai.unit.Area;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.robolectric.Robolectric;

import static org.junit.Assert.assertEquals;

@Ignore
public class AreaPickerTest {

    private AreaPicker areaPicker;

    @Before
    public void setUp() throws Exception {
        areaPicker = new AreaPicker(mockActivity());
    }

    private Activity mockActivity() {
        return Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void testHint() throws Exception {
        assertEquals(AreaPicker.DEFAULT_HINT, areaPicker.getHint());
    }

    @Test
    public void testShowDefaultMessage() throws Exception {
        assertEquals("", areaPicker.getText());
    }

    @Test
    public void testResetArea() throws Exception {
        areaPicker.setArea(new Area(2, 0, 30));

        areaPicker.setArea(new Area(0));

        assertEquals("", areaPicker.getText());
    }

    @Test
    public void testShowAreaRaiNganSquareWa() throws Exception {
        areaPicker.setArea(new Area(3, 3, 40));

        assertEquals("3 ไร่ 3 งาน 40 ตารางวา", areaPicker.getText());
    }

    @Test
    public void testShowOnlyRai() throws Exception {
        areaPicker.setArea(new Area(2, 0, 0.0));

        assertEquals("2 ไร่", areaPicker.getText());
    }
}
