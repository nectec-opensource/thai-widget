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

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import java.util.Calendar;

class CalendarSavedState extends View.BaseSavedState {

    public static final Parcelable.Creator<CalendarSavedState> CREATOR =
            new Parcelable.Creator<CalendarSavedState>() {
                public CalendarSavedState createFromParcel(Parcel in) {
                    return new CalendarSavedState(in);
                }

                public CalendarSavedState[] newArray(int size) {
                    return new CalendarSavedState[size];
                }
            };

    private long timeInMills;

    public CalendarSavedState(Parcelable superState) {
        super(superState);
    }

    private CalendarSavedState(Parcel in) {
        super(in);
        this.timeInMills = in.readLong();
    }

    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMills);
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        if (calendar != null)
            timeInMills = calendar.getTimeInMillis();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeLong(this.timeInMills);
    }
}
