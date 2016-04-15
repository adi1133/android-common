package ro.adipascu.androidcommon.mvp;

import android.support.annotation.NonNull;

import rx.Subscription;

/**
 * Created by Adi Pascu on 4/27/15.
 * Email mail@adipascu.ro
 */
//public class APresenter<V extends AView> {
public class APresenter<V> {
    protected V view;
    private boolean isForeground;

    public void onAttach(V v) {
    }

    public void onDetach() {
    }

    public void onForeground() {
    }

    public void onBackground() {
    }

    //todo: use rx lifecycle
    @Deprecated
    final protected void unsubscribeOnDetach(@NonNull Subscription subscription) {
    }


    public void setView(V view) {
        if (this.view != null) {
            if (isForeground)
                background();
            onDetach();
        }
        this.view = view;
        if (this.view != null)
            onAttach(this.view);
    }

    public V getView() {
        return view;
    }

    public void foreground() {
        if (isForeground)
            throw new UnsupportedOperationException("can not be foregrounded twice");
        isForeground = true;
    }

    public void background() {
        if (!isForeground)
            throw new UnsupportedOperationException("can not be backgrounded twice");
        isForeground = false;
    }
}
