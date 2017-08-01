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

package nectec.thai.address;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressPrinterTest {

    @Test
    public void testPrint() throws Exception {
        assertEquals("ต.บางรักพัฒนา อ.บางบัวทอง จ.นนทบุรี",
                     AddressPrinter.print("บางรักพัฒนา", "บางบัวทอง", "นนทบุรี"));
    }

    @Test
    public void testPrintAddressInBangkok() throws Exception {
        assertEquals("แขวงสนามบิน เขตดอนเมือง กรุงเทพมหานคร",
                     AddressPrinter.print("สนามบิน", "ดอนเมือง", "กรุงเทพมหานคร"));
    }

    @Test
    public void testPrintFull() throws Exception {
        assertEquals("ตำบลบางรักพัฒนา อำเภอบางบัวทอง จังหวัดนนทบุรี",
                     AddressPrinter.printFull("บางรักพัฒนา", "บางบัวทอง", "นนทบุรี"));
    }

    @Test
    public void testPrintFullAddressInBangkok() throws Exception {
        assertEquals("แขวงสนามบิน เขตดอนเมือง กรุงเทพมหานคร",
                     AddressPrinter.printFull("สนามบิน", "ดอนเมือง", "กรุงเทพมหานคร"));
    }
}
