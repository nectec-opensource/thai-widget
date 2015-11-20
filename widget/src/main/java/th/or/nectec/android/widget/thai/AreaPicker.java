/*
 * Copyright (c) 2015 NECTEC
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
 */

package th.or.nectec.android.widget.thai;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import th.or.nectec.domain.thai.Area;


/**
 * A view to display Area's size in thai unit of measurement. can edit value by AreaPickerDialog
 */
public class AreaPicker extends TextView implements AreaView {

    public static final String DEFAULT_MESSAGE = "ระบุขนาดพื้นที่";
    AreaPickerDialog pickerDialog;
    private Area area = new Area(0);
    private AreaPickerDialog.OnAreaPickListener onDialogPickListener = new AreaPickerDialog.OnAreaPickListener() {
        @Override
        public void onAreaPick(Area area) {
            setArea(area);
        }

        @Override
        public void onCancel() {
            setText(DEFAULT_MESSAGE);
            setArea(Area.fromSquareMeter(0));
        }
    };
    private OnClickListener onViewClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            pickerDialog.show(area);
        }
    };

    public AreaPicker(Context context) {
        this(context, null);
    }

    public AreaPicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
    }

    public AreaPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }


    private void initialize() {
        setText(DEFAULT_MESSAGE);
        setGravity(Gravity.CENTER_VERTICAL);
        if (!isInEditMode()) {
            setupPickerDialog();
        }
    }

    private void setupPickerDialog() {
        pickerDialog = new AreaPickerDialog(getContext(), onDialogPickListener);
        setOnClickListener(onViewClick);
    }

    @Override
    public Area getArea() {
        return area;
    }

    @Override
    public void setArea(Area area) {
        if (area == null)
            throw new NullPointerException("area must not be null");
        this.area = area;
        setText(area.prettyPrint());
    }
}
