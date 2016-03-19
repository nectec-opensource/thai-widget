package th.or.nectec.thai.date;

import org.junit.Test;

import java.util.Calendar;

import static java.util.Calendar.*;
import static org.junit.Assert.assertEquals;

public class CalendarTest {

    @Test
    public void testActualMaximum() throws Exception {
        Calendar calendar = Calendar.getInstance();

        calendar.set(1988, SEPTEMBER, 21);
        assertEquals(30, calendar.getActualMaximum(DAY_OF_MONTH));

        calendar.set(2016, FEBRUARY, 10);
        assertEquals(29, calendar.getActualMaximum(DAY_OF_MONTH));

        calendar.set(MONTH, MARCH);
        assertEquals(31, calendar.getActualMaximum(DAY_OF_MONTH));
    }
}
