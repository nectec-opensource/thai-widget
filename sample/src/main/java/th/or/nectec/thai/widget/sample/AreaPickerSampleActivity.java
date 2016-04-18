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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import th.or.nectec.thai.unit.Area;
import th.or.nectec.thai.widget.unit.AreaPicker;
import th.or.nectec.thai.widget.unit.AreaPickerDialog;


public class AreaPickerSampleActivity extends Activity {

    private final AreaPickerDialog.OnAreaPickListener callback = new AreaPickerDialog.OnAreaPickListener() {
        @Override
        public void onAreaPick(Area area) {
            Toast.makeText(getBaseContext(), area.prettyPrint(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getBaseContext(), "onCancel", Toast.LENGTH_SHORT).show();
        }
    };

    private AreaPickerDialog areaPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample_area_picker);
        setupDialog();
        setupButton();
    }

    private void setupButton() {
        View viewById = findViewById(R.id.button);
        viewById.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                areaPickerDialog.setPopupTitle("ระบุพื้นที่เพาะปลูก");
                areaPickerDialog.show();
            }
        });
        findViewById(R.id.update_area).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AreaPicker areaPicker = (AreaPicker) findViewById(R.id.area_picker);
                areaPicker.setArea(new Area(1600));
            }
        });
    }

    private void setupDialog() {
        areaPickerDialog = new AreaPickerDialog(this, callback);
    }
}
