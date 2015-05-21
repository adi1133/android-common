package ro.adipascu.androidcommon.mvp;

/**
 * Created by Adi Pascu on 4/27/15.
 * Email mail@adipascu.ro
 */
public interface AView<P extends APresenter> {
    P getPresenter();
}
