package nectec.thai.widget.identity;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import nectec.thai.identity.Identity;

public class IdentityEditText<T extends Identity> extends AppCompatEditText {

    private IdentityHandler<T> idHandler;
    private IdentityWatcher<T> onInvalid;

    public IdentityEditText(Context context) {
        this(context, null);
    }

    public IdentityEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public IdentityEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init(IdentityHandler<T> handler, final String defaultErrMessage) {
        this.idHandler = handler;
        onInvalid = new IdentityWatcher<T>() {
            @Override
            public void onInvalid(T identity) {
                setError(defaultErrMessage);
            }

            @Override
            public void onValid(T identity) {
                setError(null);
            }
        };
        idHandler.setIdWatcher(onInvalid);
    }

    public void setOnInvalid(IdentityWatcher<T> onInvalid) {
        this.onInvalid = onInvalid;
        idHandler.setIdWatcher(onInvalid);
    }

    public Identity getIdentity() {
        return idHandler.getId();
    }

    public boolean isValid() {
        return idHandler.getId().validate();
    }
}
