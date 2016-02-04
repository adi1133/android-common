package ro.adipascu.androidcommon.mvp;

import android.support.annotation.NonNull;

/**
 * Created by Adi Pascu on 4/27/15.
 * Email mail@adipascu.ro
 */
public interface AView<P extends APresenter> {
    @NonNull
    P getPresenter();
}
