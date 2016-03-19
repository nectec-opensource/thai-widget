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
