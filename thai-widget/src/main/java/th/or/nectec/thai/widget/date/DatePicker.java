package th.or.nectec.thai.widget.date;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import th.or.nectec.thai.date.DatePrinter;

import java.util.Calendar;
import java.util.Locale;

public class DatePicker extends Button {

    private final DatePopup popup;
    private Calendar calendar;
    private final DatePopup.DatePickerCallback datePickerCallback = new DatePickerDialog.DatePickerCallback() {
        @Override
        public void onPicked(DatePopup popup, Calendar calendar) {
            DatePicker.this.calendar = calendar;
            setText(DatePrinter.print(calendar));
        }

        @Override
        public void onCancel() {

        }
    };

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Locale locale = context.getResources().getConfiguration().locale;

        calendar = Calendar.getInstance(locale);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        setText(DatePrinter.print(calendar));
        popup = new DatePickerDialog(context, datePickerCallback);
    }

    @Override
    public boolean performClick() {
        popup.show(calendar);
        return super.performClick();
    }

    public int getYear() {
        return popup.getYear();
    }

    public int getMonth() {
        return popup.getMonth();
    }

    public int getDayOfMonth() {
        return popup.getDayOfMonth();
    }

}
