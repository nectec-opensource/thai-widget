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

package th.or.nectec.thai.widget.date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.NumberPicker;

import th.or.nectec.thai.widget.R;
import th.or.nectec.thai.widget.utils.ViewUtils;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class DatePickerDialog extends AlertDialog implements DatePopup, NumberPicker.OnValueChangeListener {

    private static final String[] THAI_MONTH = {"มกราคม", "กุมภาพันธ์",
            "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

    protected final NumberPicker dayPicker;
    protected final NumberPicker monthPicker;
    protected final NumberPicker yearPicker;
    private Calendar calendar;
    private DatePickerCallback callback;

    private final DialogInterface.OnClickListener onPositiveButtonClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) currentFocus.clearFocus();
            if (callback != null) callback.onPicked(DatePickerDialog.this, calendar);
            dismiss();
        }
    };

    private final DialogInterface.OnClickListener onNegativeButtonClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (callback != null) callback.onCancel();
            dismiss();
        }
    };
    private Calendar maxDate;
    private Calendar minDate;

    public DatePickerDialog(Context context) {
        this(context, null);
    }

    public DatePickerDialog(Context context, DatePickerCallback datePickerCallback) {
        this(context, Calendar.getInstance(), datePickerCallback);
    }

    public DatePickerDialog(Context context, Calendar calendar, DatePickerCallback datePickerCallback) {
        super(context);
        this.calendar = calendar;
        this.callback = datePickerCallback;

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
        monthPicker.setDisplayedValues(THAI_MONTH);

        yearPicker = (NumberPicker) view.findViewById(R.id.year);
        yearPicker.setOnValueChangedListener(this);
        yearPicker.setMinValue(2400);
        yearPicker.setMaxValue(2600);

        setButton(BUTTON_POSITIVE, getContext().getString(R.string.ok), onPositiveButtonClick);
        setButton(BUTTON_NEGATIVE, getContext().getString(R.string.cancel), onNegativeButtonClick);

        updateDate(calendar);
    }

    public final void updateDate(Calendar calendar) {
        updateDate(calendar.get(YEAR), calendar.get(MONTH), calendar.get(DAY_OF_MONTH));
    }

    @Override
    public void updateDate(int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);

        if (dayPicker != null) {
            dayPicker.setValue(dayOfMonth);
            dayPicker.setMaxValue(calendar.getActualMaximum(DAY_OF_MONTH));
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
    public void setMaxDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        setMaxDate(calendar);
    }

    @Override
    public void setMinDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        setMinDate(calendar);
    }

    @Override
    public void setCallback(DatePickerCallback callback) {
        this.callback = callback;
    }

    public void setMaxDateIsToday() {
        setMaxDate(Calendar.getInstance());
    }

    public void setMaxDate(Calendar maxDate) {
        this.maxDate = maxDate;
        yearPicker.setMaxValue(maxDate.get(YEAR) + 543);

        updateMaxValueIfMaxDateSet();

        if (calendar.compareTo(maxDate) > 0) updateDate(maxDate);
    }

    public void setMinDateIsToday() {
        setMinDate(Calendar.getInstance());
    }

    public void setMinDate(Calendar minDate) {
        this.minDate = minDate;
        yearPicker.setMinValue(minDate.get(YEAR) + 543);

        updateMinValueIfMinDateSet();

        if (calendar.compareTo(minDate) < 0) updateDate(minDate);
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
    public void setPopupTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        updateValueAndUi();
    }

    protected void updateValueAndUi() {
        trimDayOverMaxDayOfNewMonth();
        updateMaxValueIfMaxDateSet();
        updateMinValueIfMinDateSet();
    }

    private void trimDayOverMaxDayOfNewMonth() {
        Calendar newCalendar = newCalendarFromPicker();
        updateDate(newCalendar);
    }

    protected Calendar newCalendarFromPicker() {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), 1);

        if (dayPicker.getValue() > newCalendar.getActualMaximum(DAY_OF_MONTH)) {
            dayPicker.setValue(newCalendar.getActualMaximum(DAY_OF_MONTH));
        }
        dayPicker.setMaxValue(newCalendar.getActualMaximum(DAY_OF_MONTH));
        newCalendar.set(DAY_OF_MONTH, dayPicker.getValue());
        return newCalendar;
    }

    private void updateMaxValueIfMaxDateSet() {
        if (maxDate == null)
            return;
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), 1);

        if (yearPicker.getValue() == maxDate.get(YEAR) + 543) {
            monthPicker.setOnValueChangedListener(null);
            dayPicker.setOnValueChangedListener(null);

            int maxMonth = maxDate.get(MONTH);
            monthPicker.setMaxValue(maxMonth);

            if (monthPicker.getValue() == maxMonth) {
                int maxDayOfMonth = maxDate.get(DAY_OF_MONTH);
                dayPicker.setMaxValue(maxDayOfMonth);
            }

            newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), dayPicker.getValue());
            updateDate(newCalendar);

            monthPicker.setOnValueChangedListener(this);
            dayPicker.setOnValueChangedListener(this);
        } else {
            monthPicker.setMaxValue(11);
        }
    }

    private void updateMinValueIfMinDateSet() {
        if (minDate == null)
            return;
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), 1);

        if (yearPicker.getValue() == minDate.get(YEAR) + 543) {
            monthPicker.setOnValueChangedListener(null);
            dayPicker.setOnValueChangedListener(null);

            int minMonth = minDate.get(MONTH);
            monthPicker.setMinValue(minMonth);
            String[] monthDisplay = new String[12 - minMonth];
            System.arraycopy(THAI_MONTH, minMonth, monthDisplay, 0, monthDisplay.length);
            monthPicker.setDisplayedValues(monthDisplay);

            if (monthPicker.getValue() == minMonth) {
                int minDayOfMonth = minDate.get(DAY_OF_MONTH);
                dayPicker.setMinValue(minDayOfMonth);
            } else {
                dayPicker.setMinValue(1);
            }

            newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), dayPicker.getValue());
            updateDate(newCalendar);

            monthPicker.setOnValueChangedListener(this);
            dayPicker.setOnValueChangedListener(this);
        } else {
            monthPicker.setMinValue(0);
            monthPicker.setDisplayedValues(THAI_MONTH);
            dayPicker.setMinValue(1);
        }
    }
}
