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

package nectec.thai.widget.date;

import android.app.Activity;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.robolectric.Robolectric;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MARCH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SEPTEMBER;
import static java.util.Calendar.YEAR;
import static org.junit.Assert.assertEquals;

@Ignore
public class DatePickerDialogTest {

    private DatePickerDialog datePickerDialog;

    @Before
    public void setUp() throws Exception {
        datePickerDialog = new DatePickerDialog(Robolectric.buildActivity(Activity.class).create().get());
    }

    @Test
    public void testUpdateDate() throws Exception {
        datePickerDialog.updateDate(1988, SEPTEMBER, 21);

        assertEquals(1988, datePickerDialog.getYear());
        assertEquals(SEPTEMBER, datePickerDialog.getMonth());
        assertEquals(21, datePickerDialog.getDayOfMonth());
    }

    @Test
    public void testUpdateDateByCalendar() throws Exception {
        Calendar calendar = Calendar.getInstance(); //NOPMD
        calendar.set(1988, SEPTEMBER, 21);

        datePickerDialog.updateDate(calendar);

        assertEquals(1988, datePickerDialog.getYear());
        assertEquals(SEPTEMBER, datePickerDialog.getMonth());
        assertEquals(21, datePickerDialog.getDayOfMonth());
    }

    @Test
    public void testNumberPickerChange() throws Exception {
        datePickerDialog.updateDate(2016, FEBRUARY, 29);
        assertEquals(29, datePickerDialog.getDayOfMonth());

        datePickerDialog.yearPicker.setValue(2015 + 543);
        datePickerDialog.updateValueAndUi();

        assertEquals(28, datePickerDialog.getDayOfMonth());
        assertEquals(2015, datePickerDialog.getYear());
    }

    @Test
    public void testWhenMonthChangedMaximumDayMustChange() throws Exception {
        datePickerDialog.updateDate(2015, JANUARY, 31);
        assertEquals(31, datePickerDialog.dayPicker.getMaxValue());

        datePickerDialog.monthPicker.setValue(FEBRUARY);
        datePickerDialog.updateValueAndUi();
        assertEquals(28, datePickerDialog.dayPicker.getMaxValue());

        datePickerDialog.monthPicker.setValue(APRIL);
        datePickerDialog.updateValueAndUi();
        assertEquals(30, datePickerDialog.dayPicker.getMaxValue());

    }

    @Test
    public void testCalendarFromPicker() throws Exception {
        datePickerDialog.yearPicker.setValue(2016 + 543);
        datePickerDialog.monthPicker.setValue(MARCH);
        datePickerDialog.dayPicker.setValue(31);

        Calendar newCalendar = datePickerDialog.newCalendarFromPicker();
        assertEquals(31, newCalendar.get(DAY_OF_MONTH));
        assertEquals(MARCH, newCalendar.get(MONTH));
        assertEquals(2016, newCalendar.get(YEAR));
        assertEquals(31, newCalendar.getActualMaximum(DAY_OF_MONTH));

        datePickerDialog.monthPicker.setValue(FEBRUARY);
        newCalendar = datePickerDialog.newCalendarFromPicker();
        assertEquals(29, newCalendar.get(DAY_OF_MONTH));
    }

}
