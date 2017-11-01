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
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;

import nectec.thai.unit.Area;
import nectec.thai.widget.ViewUtils;
import th.or.nectec.thai.widget.R;

/**
 * Dialog for select area's size in Thai Unit of measurement
 */
public class AreaPickerDialog extends AlertDialog implements AreaPopup {

    public static final String DEFAULT_TITLE = "ระบุขนาดพื้นที่";
    private final OnAreaPickListener onAreaPickListener;
    private final OnClickListener onNegativeButtonClick = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            onAreaPickListener.onCancel();
            dismiss();
        }
    };
    private NumberPicker rai;
    private NumberPicker ngan;
    private NumberPicker squareWa;
    private final OnClickListener onPositiveButtonClick = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) currentFocus.clearFocus();
            Area area = new Area(rai.getValue(), ngan.getValue(), squareWa.getValue());
            onAreaPickListener.onAreaPick(area);
            dismiss();
        }
    };

    public AreaPickerDialog(Context context, OnAreaPickListener listener) {
        super(context);
        this.onAreaPickListener = listener;

        setTitle(DEFAULT_TITLE);
        setupView(context);
    }

    private void setupView(Context context) {
        View view = ViewUtils.inflateView(context, R.layout.thwig_dialog_area_picker);
        setView(view);
        findView(view);
        initRai();
        initNgan();
        initSquareWa();
        initButton();
    }

    private void findView(View view) {
        rai = (NumberPicker) view.findViewById(R.id.rai);
        ngan = (NumberPicker) view.findViewById(R.id.ngan);
        squareWa = (NumberPicker) view.findViewById(R.id.squareWa);
    }

    private void initRai() {
        rai.setMaxValue(10000);
        rai.setMinValue(0);
        rai.setValue(0);
    }

    private void initNgan() {
        ngan.setMaxValue(3);
        ngan.setMinValue(0);
        ngan.setValue(0);
    }

    private void initSquareWa() {
        squareWa.setMaxValue(99);
        squareWa.setMinValue(0);
        squareWa.setValue(0);
    }

    private void initButton() {
        setButton(BUTTON_POSITIVE, getContext().getString(R.string.ok), onPositiveButtonClick);
        setButton(BUTTON_NEGATIVE, getContext().getString(R.string.cancel), onNegativeButtonClick);
    }

    public void setMaxRai(int maxRai) {
        rai.setMaxValue(maxRai);
    }

    @Override
    public void setPopupTitle(String title) {
        setTitle(title);
    }

    @Override
    public void show(Area area) {
        updateValue(area);
        this.show();
    }

    private void updateValue(Area area) {
        rai.setValue(area.getRai());
        ngan.setValue(area.getNgan());
        squareWa.setValue((int) Math.round(area.getSquareWa()));
    }

    public interface OnAreaPickListener {
        void onAreaPick(Area area);

        void onCancel();
    }

}
