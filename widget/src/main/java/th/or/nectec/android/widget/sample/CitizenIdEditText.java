package th.or.nectec.android.widget.sample;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import th.or.nectec.domain.thai.CitizenId;

/**
 * Created by blaze on 10/17/2015 AD.
 */
public class CitizenIdEditText extends EditText{
    public CitizenIdEditText(Context context) {
        super(context);
    }

    public CitizenIdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CitizenIdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                String id = text.replaceAll("-", "");

                CitizenId cid = new CitizenId(id);
                setText(cid.prettyPrint());
            }
        });
    }
}
