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
        if (this.view != null)
            onDetach();
        this.view = view;
        if (this.view != null)
            onAttach(this.view);
    }

    public V getView() {
        return view;
    }
}
