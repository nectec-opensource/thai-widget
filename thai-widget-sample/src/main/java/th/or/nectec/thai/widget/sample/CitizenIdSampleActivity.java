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
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import nectec.thai.identity.CitizenId;
import nectec.thai.widget.identity.CitizenIdEditText;
import nectec.thai.widget.identity.IdentityWatcher;

public class CitizenIdSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_citizen_id);

        CitizenIdEditText cid = (CitizenIdEditText) findViewById(R.id.citizen_id);
        final TextInputLayout layout = (TextInputLayout) findViewById(R.id.citizen_id_layout);
        cid.setOnInvalid(new IdentityWatcher<CitizenId>() {

            @Override
            public void onInvalid(CitizenId identity) {
                layout.setError(CitizenIdEditText.DEFAULT_ERROR_MESSAGE);
            }

            @Override
            public void onValid(CitizenId identity) {
                layout.setError(null);
            }
        });

    }
}
