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
 *
 *
 */

package th.or.nectec.thai.widget.identity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import th.or.nectec.thai.identity.Identity;

public class HouseIdEditText extends EditText implements IdentityView {

    IdentityEditTextHandler hidHandler;

    public HouseIdEditText(Context context) {
        super(context);
        initialHandler();
    }

    private void initialHandler() {
        hidHandler = new HouseIdHandler(this);
    }


    public HouseIdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialHandler();
    }

    public HouseIdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialHandler();
    }

    @Override
    public Identity getIdentity() {
        return hidHandler.getId();
    }
}
