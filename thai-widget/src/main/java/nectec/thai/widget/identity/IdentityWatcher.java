package nectec.thai.widget.identity;

import nectec.thai.identity.Identity;

public interface IdentityWatcher<T extends Identity> {
    void onInvalid(T identity);

    void onValid(T identity);
}
