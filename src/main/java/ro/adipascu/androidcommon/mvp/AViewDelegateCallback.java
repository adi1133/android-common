package ro.adipascu.androidcommon.mvp;

/**
 * Created by Adi Pascu.
 * Email mail@adipascu.ro
 */
public interface AViewDelegateCallback<P extends APresenter, V extends AView> extends AView<P> {
    V getView();
}
