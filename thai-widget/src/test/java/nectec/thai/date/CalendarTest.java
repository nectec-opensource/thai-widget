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

package nectec.thai.date;

import java.util.Calendar;
import org.junit.Test;

import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.MARCH;
import static java.util.Calendar.SEPTEMBER;
import static org.junit.Assert.assertEquals;

public class CalendarTest {

    @Test
    public void testActualMaximum() throws Exception {
        Calendar calendar = Calendar.getInstance();

        calendar.set(1988, SEPTEMBER, 21);
        assertEquals(30, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        calendar.set(2016, FEBRUARY, 10);
        assertEquals(29, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.MONTH, MARCH);
        assertEquals(31, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
}
