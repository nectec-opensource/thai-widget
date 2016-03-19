package th.or.nectec.thai.date;

import java.util.Calendar;

public class DatePrinter {

    public static final String[] THAI_MONTH = {"มกราคม", "กุมภาพันธ์",
            "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
    private static final String SPACE = " ";

    public static String print(Calendar calendar) {
        return print(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String print(int year, int month, int dayOfMonth) {
        return String.valueOf(dayOfMonth)
                + SPACE
                + THAI_MONTH[month]
                + SPACE
                + String.valueOf(year + 543);
    }
}
