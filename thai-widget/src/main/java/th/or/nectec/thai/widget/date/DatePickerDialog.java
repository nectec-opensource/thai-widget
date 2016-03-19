package th.or.nectec.thai.widget.date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.NumberPicker;
import th.or.nectec.thai.date.DatePrinter;
import th.or.nectec.thai.widget.R;
import th.or.nectec.thai.widget.utils.ViewUtils;

import java.util.Calendar;

import static java.util.Calendar.*;

public class DatePickerDialog extends AlertDialog implements DatePopup, NumberPicker.OnValueChangeListener {

    private final NumberPicker dayPicker;
    private final NumberPicker monthPicker;
    private final NumberPicker yearPicker;
    private Calendar calendar;
    private DatePickerCallback callback;
    private DialogInterface.OnClickListener onPositiveButtonClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            getButton(BUTTON_POSITIVE).requestFocus();
            if (callback != null) {
                callback.onPicked(DatePickerDialog.this, calendar);
            }
            dismiss();
        }
    };
    private DialogInterface.OnClickListener onNegativeButtonClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (callback != null) callback.onCancel();
            dismiss();
        }
    };

    public DatePickerDialog(Context context) {
        this(context, Calendar.getInstance());
    }

    public DatePickerDialog(Context context, Calendar calendar) {
        super(context);
        this.calendar = calendar;
        setTitle(DatePrinter.print(calendar));

        View view = ViewUtils.inflateView(getContext(), R.layout.dialog_date_picker);
        setView(view);

        dayPicker = (NumberPicker) view.findViewById(R.id.day);
        dayPicker.setOnValueChangedListener(this);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(calendar.getActualMaximum(DAY_OF_MONTH));
        monthPicker = (NumberPicker) view.findViewById(R.id.month);
        monthPicker.setOnValueChangedListener(this);
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(11);
        monthPicker.setDisplayedValues(DatePrinter.THAI_MONTH);
        yearPicker = (NumberPicker) view.findViewById(R.id.year);
        yearPicker.setOnValueChangedListener(this);
        yearPicker.setMinValue(2400);
        yearPicker.setMaxValue(2600);

        setButton(BUTTON_POSITIVE, getContext().getString(R.string.ok), onPositiveButtonClick);
        setButton(BUTTON_NEGATIVE, getContext().getString(R.string.cancel), onNegativeButtonClick);

        updateDate(calendar);
    }

    public void updateDate(Calendar calendar) {
        updateDate(calendar.get(YEAR), calendar.get(MONTH), calendar.get(DAY_OF_MONTH));
    }

    @Override
    public void updateDate(int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        if (dayPicker != null) {
            dayPicker.setValue(dayOfMonth);
            monthPicker.setValue(month);
            yearPicker.setValue(year + 543);
        }
    }

    @Override
    public int getYear() {
        return calendar.get(YEAR);
    }

    @Override
    public int getMonth() {
        return calendar.get(MONTH);
    }

    @Override
    public int getDayOfMonth() {
        return calendar.get(DAY_OF_MONTH);
    }

    @Override
    public void show(int year, int month, int dayOfMonth) {
        updateDate(year, month, dayOfMonth);
        show();
    }

    @Override
    public void show(Calendar calendar) {
        updateDate(calendar);
        show();
    }

    @Override
    public void setCallback(DatePickerCallback callback) {
        this.callback = callback;
    }

    public DatePickerDialog(Context context, DatePickerCallback datePickerCallback) {
        this(context, Calendar.getInstance());
        this.callback = datePickerCallback;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), 1);

        int maxDayOfMonth = newCalendar.getActualMaximum(DAY_OF_MONTH);
        if (dayPicker.getValue() > maxDayOfMonth) {
            dayPicker.setValue(maxDayOfMonth);
        }
        dayPicker.setMaxValue(maxDayOfMonth);

        newCalendar.set(DAY_OF_MONTH, dayPicker.getValue());
        setTitle(DatePrinter.print(newCalendar));
        calendar = newCalendar;
    }
}
