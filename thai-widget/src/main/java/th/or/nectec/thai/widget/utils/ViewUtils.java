package th.or.nectec.thai.widget.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public final class ViewUtils {

    private ViewUtils() {
    }

    @SuppressLint("InflateParams")
    public static View inflateView(Context context, int layoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId, null, false);
    }
}
