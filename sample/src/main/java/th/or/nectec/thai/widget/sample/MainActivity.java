/*
 * Copyright © 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.or.nectec.thai.widget.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.citizen_id:
                openSampleActivity(CitizenIdSampleActivity.class);
                break;
            case R.id.house_id:
                openSampleActivity(HouseIdSampleActivity.class);
                break;
            case R.id.area_picker:
                openSampleActivity(AreaPickerSampleActivity.class);
                break;
            case R.id.address_picker:
                openSampleActivity(AddressPickerSampleActivity.class);
                break;
            case R.id.date_picker:
                openSampleActivity(DatePickerSampleActivity.class);
                break;
        }
    }

    private void openSampleActivity(Class<? extends Activity> citizenIdSampleActivityClass) {
        Intent intent = new Intent(this, citizenIdSampleActivityClass);
        startActivity(intent);
    }

}
