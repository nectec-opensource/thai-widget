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

package th.or.nectec.thai.date;

import org.junit.Test;

import java.util.Calendar;

import static java.util.Calendar.SEPTEMBER;
import static org.junit.Assert.assertEquals;


public class DatePrinterTest {

    @Test
    public void testPrint() throws Exception {
        assertEquals("21 กันยายน 2531", DatePrinter.print(1988, 8, 21));
        assertEquals("20 เมษายน 2511", DatePrinter.print(1968, 3, 20));
    }

    @Test
    public void testPrintCalendar() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1988, SEPTEMBER, 21);

        assertEquals("21 กันยายน 2531", DatePrinter.print(calendar));
    }
}
