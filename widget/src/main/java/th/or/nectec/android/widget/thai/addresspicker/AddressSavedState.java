package th.or.nectec.android.widget.thai.addresspicker;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class AddressSavedState extends View.BaseSavedState {
    public String addressCode;

    public AddressSavedState(Parcelable superState) {
        super(superState);
    }

    private AddressSavedState(Parcel in) {
        super(in);
        this.addressCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(this.addressCode);
    }

    public static final Parcelable.Creator<AddressSavedState> CREATOR =
            new Parcelable.Creator<AddressSavedState>() {
                public AddressSavedState createFromParcel(Parcel in) {
                    return new AddressSavedState(in);
                }

                public AddressSavedState[] newArray(int size) {
                    return new AddressSavedState[size];
                }
            };
}
