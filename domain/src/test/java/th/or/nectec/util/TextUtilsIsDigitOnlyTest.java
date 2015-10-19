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
package th.or.nectec.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextUtilsIsDigitOnlyTest {

    @Test
    public void digitOnlyStringShouldTrue(){
        assertEquals("input only digit should return true", true, TextUtils.isDigitOnly("12346"));
    }

    @Test
    public void notDigitOnlyStringShouldFalse(){
        assertEquals("input not only digit should return false", false, TextUtils.isDigitOnly("123adc"));
    }

    @Test
    public void emptyStringShouldReturnFalse(){
        assertEquals("empty should return false", false, TextUtils.isDigitOnly(""));
    }
}