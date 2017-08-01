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

package nectec.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextUtilsTest {

    @Test public void isDigitOnly() {
        assertTrue("input only digit should return true", TextUtils.isDigitOnly("12346"));
        assertTrue("input only digit should return true", TextUtils.isDigitOnly("222222222"));
        assertFalse("input not only digit should return false", TextUtils.isDigitOnly("123adc"));
        assertFalse("empty should return false", TextUtils.isDigitOnly(""));
    }

    @Test public void isRepeatNumber() {
        assertFalse("not repeating number must return false",
                    TextUtils.isRepeatingNumber("029209355"));
        assertTrue("repeating number must return true", TextUtils.isRepeatingNumber("111111111"));
        assertTrue("repeating number must return true", TextUtils.isRepeatingNumber("222222222"));
    }

    @Test public void isRepeatPatternNumber() {
        assertFalse("not repeating number must return false",
                    TextUtils.isRepeatPatternNumber("029209355"));
        assertTrue("repeat pattern number must return true",
                   TextUtils.isRepeatPatternNumber("123123123"));
        assertTrue("repeat pattern number must return true",
                   TextUtils.isRepeatPatternNumber("00010001"));
        assertTrue("repeating number must also return true",
                   TextUtils.isRepeatPatternNumber("222222222"));
    }
}
