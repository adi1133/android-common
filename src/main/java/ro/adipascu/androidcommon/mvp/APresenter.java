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
    private boolean isDestroyed;

    protected void onAttach(V v) {
    }

    protected void onForeground() {
    }

    protected void onBackground() {
    }

    protected void onDetach() {
    }

    protected void onDestroy() {
    }

    //todo: use rx lifecycle
    @Deprecated
    final protected void unsubscribeOnDetach(@NonNull Subscription subscription) {
    }


    public void setView(V view) {
        assertNotDestroyed();
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
        assertNotDestroyed();
        if (isForeground)
            throw new UnsupportedOperationException("can not be foregrounded twice");
        isForeground = true;
        onForeground();
    }

    public void background() {
        assertNotDestroyed();
        if (!isForeground)
            throw new UnsupportedOperationException("can not be backgrounded twice");
        onBackground();
        isForeground = false;
    }

    public void destroy() {
        assertNotDestroyed();
        isDestroyed = true;
        onDestroy();
    }


    private void assertNotDestroyed() {
        if (isDestroyed)
            throw new UnsupportedOperationException("can not do this operation on a destroyed presenter");
    }
}
