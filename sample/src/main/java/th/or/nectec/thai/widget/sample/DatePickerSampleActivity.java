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

package th.or.nectec.thai.widget.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import th.or.nectec.thai.date.DatePrinter;
import th.or.nectec.thai.widget.date.DatePickerDialog;
import th.or.nectec.thai.widget.date.DateView;

import java.util.Calendar;

public class DatePickerSampleActivity extends AppCompatActivity {

    private final DateView.DatePickerCallback datePickerCallback = new DateView.DatePickerCallback() {
        @Override
        public void onPicked(DateView view, Calendar calendar) {
            Button button = (Button) findViewById(R.id.button);
            button.setText(DatePrinter.print(calendar));
        }

        @Override
        public void onCancel() {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_date_picker);

//        DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
//        datePicker.setMaxDate(1988, 9, 21);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(DatePickerSampleActivity.this, datePickerCallback);
                dialog.setMinDateIsToday();
                dialog.show();
            }
        });
    }
}
