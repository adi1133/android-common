package ro.adipascu.androidcommon.mvp;

/**
 * Created by Adi Pascu.
 * Email mail@adipascu.ro
 */
public class AViewDelegate<P extends APresenter, V extends AView> {

    private final AViewDelegateCallback<P, V> delegateCallback;

    public AViewDelegate(AViewDelegateCallback<P, V> delegateCallback) {
        this.delegateCallback = delegateCallback;
    }

    public void onAttachedToWindow() {
        //noinspection unchecked
        delegateCallback.getPresenter().setView(delegateCallback.getView());
    }

    public void onDetachedFromWindow() {
        delegateCallback.getPresenter().setView(null);
    }
}
