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

package nectec.thai.widget.unit;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import nectec.thai.unit.Area;
import nectec.thai.widget.ViewUtils;

public class AreaPicker extends AppCompatEditText implements AreaView, OnClickListener {

    protected static final String HINT_MESSAGE = "ระบุขนาดพื้นที่";

    private AreaPopup popup;
    private Area area = new Area(0);
    private OnAreaChangedListener listener;
    private final AreaPickerDialog.OnAreaPickListener dialogListener = new AreaPickerDialog.OnAreaPickListener() {
        @Override
        public void onAreaPick(Area area) {
            setArea(area);
        }

        @Override
        public void onCancel() {
            setText(null);
            setArea(new Area(0));
        }
    };

    public AreaPicker(Context context) {
        this(context, null);
    }

    public AreaPicker(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public AreaPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (TextUtils.isEmpty(getHint()))
            setHint(HINT_MESSAGE);
        setGravity(Gravity.CENTER_VERTICAL);
        ViewUtils.updatePaddingRight(this);
        if (!isInEditMode()) {
            setupPickerDialog();
        }
    }

    private void setupPickerDialog() {
        popup = new AreaPickerDialog(getContext(), dialogListener);
        setOnClickListener(this);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setLongClickable(false);
        setClickable(true);
    }

    @Override
    public Area getArea() {
        return area;
    }

    @Override
    public void onClick(View view) {
        popup.show(area);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        if (area == null)
            return super.onSaveInstanceState();
        Parcelable parcelable = super.onSaveInstanceState();
        AreaSaveState savedState = new AreaSaveState(parcelable);
        savedState.setArea(area);
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof AreaSaveState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        AreaSaveState ss = (AreaSaveState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setArea(ss.getArea());
    }

    @Override public void setArea(Area area) {
        if (area == null)
            throw new IllegalArgumentException("area must not be null");
        this.area = area;
        setText(area.prettyPrint());
        if (listener != null)
            listener.onAreaChanged(area);
    }

    public void setTitle(String title) {
        popup.setPopupTitle(title);
    }

    public void setOnAreaChangeListener(OnAreaChangedListener listener) {
        this.listener = listener;
    }

}
