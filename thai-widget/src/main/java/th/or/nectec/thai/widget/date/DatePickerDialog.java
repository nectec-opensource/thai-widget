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
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import th.or.nectec.thai.date.DatePrinter;
import th.or.nectec.thai.widget.R;
import th.or.nectec.thai.widget.utils.ViewUtils;

import java.util.Calendar;

import static java.util.Calendar.*;

public class DatePickerDialog extends AlertDialog implements DatePopup, NumberPicker.OnValueChangeListener {

    private static final String TAG = "DatePickerDialog";
    private final NumberPicker dayPicker;
    private final NumberPicker monthPicker;
    private final NumberPicker yearPicker;
    private Calendar calendar;
    private DatePickerCallback callback;
    private DialogInterface.OnClickListener onPositiveButtonClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) currentFocus.clearFocus();
            if (callback != null) callback.onPicked(DatePickerDialog.this, calendar);
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
    private Calendar maxDate;

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
        Log.e(TAG, String.format("updateDate %s %s %s", dayOfMonth, month, year));
        calendar.set(year, month, dayOfMonth);

        if (dayPicker != null) {
            dayPicker.setValue(dayOfMonth);
            monthPicker.setValue(month);
            yearPicker.setValue(year + 543);
        }

        setTitle(DatePrinter.print(calendar));
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
    public void setCallback(DatePickerCallback callback) {
        this.callback = callback;
    }

    public void setMaxDateIsToday() {
        setMaxDate(Calendar.getInstance());
    }

    public void setMaxDate(Calendar maxDate) {
        this.maxDate = maxDate;
        yearPicker.setMaxValue(maxDate.get(YEAR) + 543);

        updateMaxValueIfMaxDateSetted();

        if (calendar.compareTo(maxDate) > 0) updateDate(maxDate);
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
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), 1);

        int maxDayOfMonth = newCalendar.getActualMaximum(DAY_OF_MONTH);
        if (dayPicker.getValue() > maxDayOfMonth) {
            dayPicker.setValue(maxDayOfMonth);
        }
        dayPicker.setMaxValue(maxDayOfMonth);
        newCalendar.set(DAY_OF_MONTH, dayPicker.getValue());
        updateDate(newCalendar);

        updateMaxValueIfMaxDateSetted();

    }

    private void updateMaxValueIfMaxDateSetted() {
        if (maxDate == null)
            return;

        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), 1);


        if (yearPicker.getValue() == maxDate.get(YEAR) + 543) {
            monthPicker.setOnValueChangedListener(null);
            dayPicker.setOnValueChangedListener(null);
            int maxMonth = maxDate.get(MONTH);
            if (monthPicker.getValue() > maxMonth) {
                monthPicker.setValue(maxMonth);
                Log.d(TAG, "updateMaxValueIfMaxDateSetted set month value to " + maxMonth);
            }
            monthPicker.setMaxValue(maxMonth);

            if (monthPicker.getValue() == maxMonth) {
                int maxDayOfMonth = maxDate.get(DAY_OF_MONTH);
                if (dayPicker.getValue() > maxDayOfMonth) {
                    dayPicker.setValue(maxDayOfMonth);
                    Log.d(TAG, "updateMaxValueIfMaxDateSetted set DOM value to " + maxDayOfMonth);
                }
                dayPicker.setMaxValue(maxDayOfMonth);
            }

            monthPicker.setOnValueChangedListener(this);
            dayPicker.setOnValueChangedListener(this);
            newCalendar.set(yearPicker.getValue() - 543, monthPicker.getValue(), dayPicker.getValue());
            Log.e(TAG, String.format("updateMaxValueIfMaxDateSetted %s %s %s", newCalendar.get(DAY_OF_MONTH),
                    newCalendar.get(MONTH), newCalendar.get(YEAR)));
            updateDate(newCalendar);
        } else {
            monthPicker.setMaxValue(11);
        }
    }
}
