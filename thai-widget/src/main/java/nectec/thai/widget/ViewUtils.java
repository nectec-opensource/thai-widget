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

package nectec.thai.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import th.or.nectec.thai.widget.R;

public final class ViewUtils {

    private ViewUtils() {
    }

    @SuppressLint("InflateParams")
    public static View inflateView(Context context, int layoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId, null, false);
    }

    public static void updatePaddingRight(View view) {
        int minPaddingRight = view.getContext().getResources().getDimensionPixelOffset(R.dimen.spinner_padding_right);
        int paddingRight = view.getPaddingRight();
        if (paddingRight < minPaddingRight)
            paddingRight = minPaddingRight;
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), paddingRight, view.getPaddingBottom());
    }
}
