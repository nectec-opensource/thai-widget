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

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Locale;
import nectec.thai.date.DatePrinter;
import nectec.thai.widget.ViewUtils;

public class DatePicker extends EditText implements DateView {

    protected static final String HINT_MESSAGE = "ระบุวันที";

    private final DatePopup popup;
    private Calendar calendar;
    private DatePickerCallback callback;

    private OnDateChangedListener onDateChangedListener;

    private final DatePopup.DatePickerCallback datePickerCallback = new DatePickerDialog.DatePickerCallback() {
        @Override public void onPicked(DateView view, Calendar calendar) {
            setCalendar(calendar);
            if (callback != null) callback.onPicked(DatePicker.this, calendar);
        }

        @Override public void onCancel() {
            removeCalendar();
        }
    };
    private boolean undefinedAsDefault;

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!undefinedAsDefault) {
            setCalendar(defaultCalendar());
        }

        if (TextUtils.isEmpty(getHint()))
            setHint(HINT_MESSAGE);
        ViewUtils.updatePaddingRight(this);

        popup = new DatePickerDialog(context, datePickerCallback);
    }

    public final void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        setText(DatePrinter.print(calendar));

        if (onDateChangedListener != null) onDateChangedListener.onDateChanged(calendar);
    }

    private Calendar defaultCalendar() {
        Locale locale = getContext().getResources().getConfiguration().locale;
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        return calendar;
    }

    public void setUndefinedAsDefault() {
        removeCalendar();
        undefinedAsDefault = true;
    }

    private void removeCalendar() {
        callback = null;
        calendar = null;
        if (onDateChangedListener != null)
            onDateChangedListener.onDateChanged(null);
        setText(null);
    }

    public Calendar getCalendar() {
        return calendar == null ? null : (Calendar) calendar.clone();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setLongClickable(false);
        setClickable(true);
    }

    @Override public boolean performClick() {
        if (calendar == null) calendar = defaultCalendar();
        popup.show(calendar);
        return super.performClick();
    }

    @Override public void updateDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        setCalendar(calendar);
    }

    @Override public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    @Override public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    @Override public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override public void setMaxDate(int year, int month, int dayOfMonth) {
        Calendar maxCalendar = defaultCalendar();
        maxCalendar.set(year, month, dayOfMonth);
        if (this.calendar.compareTo(maxCalendar) > 0) setCalendar(maxCalendar);
        popup.setMaxDate(year, month, dayOfMonth);
    }

    @Override public void setMinDate(int year, int month, int dayOfMonth) {
        Calendar maxCalendar = defaultCalendar();
        maxCalendar.set(year, month, dayOfMonth);
        if (this.calendar.compareTo(maxCalendar) < 0) setCalendar(maxCalendar);
        popup.setMinDate(year, month, dayOfMonth);
    }

    @Override public void setCallback(DatePickerCallback callback) {
        this.callback = callback;
    }

    @Override public Parcelable onSaveInstanceState() {
        if (calendar == null) return super.onSaveInstanceState();
        Parcelable parcelable = super.onSaveInstanceState();
        CalendarSavedState savedState = new CalendarSavedState(parcelable);
        savedState.setCalendar(calendar);
        return savedState;
    }

    @Override public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof CalendarSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        CalendarSavedState ss = (CalendarSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setCalendar(ss.getCalendar());
    }

    public void setOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.onDateChangedListener = onDateChangedListener;
    }

    public void setPopupTitle(String title) {
        popup.setPopupTitle(title);
    }

    public interface OnDateChangedListener {
        void onDateChanged(Calendar calendar);
    }
}
