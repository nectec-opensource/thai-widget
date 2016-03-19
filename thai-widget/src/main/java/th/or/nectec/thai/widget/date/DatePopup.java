package th.or.nectec.thai.widget.date;

import java.util.Calendar;

public interface DatePopup {
    void updateDate(int year, int month, int dayOfMonth);

    int getYear();

    int getMonth();

    int getDayOfMonth();

    void show(int year, int month, int dayOfMonth);

    void show(Calendar calendar);

    void setCallback(DatePickerCallback callback);

    interface DatePickerCallback {
        void onPicked(DatePopup popup, Calendar calendar);

        void onCancel();
    }
}
