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

package th.or.nectec.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextUtilsTest {

    @Test
    public void isDigitOnly() {
        assertEquals("input only digit should return true", true, TextUtils.isDigitOnly("12346"));
        assertEquals("input only digit should return true", true, TextUtils.isDigitOnly("222222222"));
        assertEquals("input not only digit should return false", false, TextUtils.isDigitOnly("123adc"));
        assertEquals("empty should return false", false, TextUtils.isDigitOnly(""));
    }

    @Test
    public void isRepeatNumber() {
        assertEquals("not repeating number must return false", false, TextUtils.isRepeatingNumber("029209355"));
        assertEquals("repeating number must return true", true, TextUtils.isRepeatingNumber("111111111"));
        assertEquals("repeating number must return true", true, TextUtils.isRepeatingNumber("222222222"));
    }

    @Test
    public void isRepeatPatternNumber() {
        assertEquals("not repeating number must return false", false, TextUtils.isRepeatPatternNumber("029209355"));
        assertEquals("repeat pattern number must return true", true, TextUtils.isRepeatPatternNumber("123123123"));
        assertEquals("repeat pattern number must return true", true, TextUtils.isRepeatPatternNumber("00010001"));
        assertEquals("repeating number must also return true", true, TextUtils.isRepeatPatternNumber("222222222"));
    }
}
