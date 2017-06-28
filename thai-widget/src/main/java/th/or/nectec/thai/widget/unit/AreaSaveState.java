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

package th.or.nectec.thai.widget.unit;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import nectec.thai.unit.Area;

class AreaSaveState extends View.BaseSavedState {

    public static final Parcelable.Creator<AreaSaveState> CREATOR =
            new Parcelable.Creator<AreaSaveState>() {
                public AreaSaveState createFromParcel(Parcel in) {
                    return new AreaSaveState(in);
                }

                public AreaSaveState[] newArray(int size) {
                    return new AreaSaveState[size];
                }
            };

    private int areaInSquareMeter;

    public AreaSaveState(Parcelable superState) {
        super(superState);
    }

    private AreaSaveState(Parcel in) {
        super(in);
        this.areaInSquareMeter = in.readInt();
    }

    public Area getArea() {
        return new Area(areaInSquareMeter);
    }

    public void setArea(Area area) {
        if (area != null)
            areaInSquareMeter = area.getSquareMetre();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(this.areaInSquareMeter);
    }
}
