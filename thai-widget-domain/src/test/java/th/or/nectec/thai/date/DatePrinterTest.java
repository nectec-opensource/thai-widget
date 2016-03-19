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
