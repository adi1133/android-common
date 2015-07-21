package ro.adipascu.androidcommon.mvp;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Adi Pascu on 4/27/15.
 * Email mail@adipascu.ro
 */
//public class APresenter<V extends AView> {
public class APresenter<V> {
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    protected V view;

    public void attach(V v) {
        this.view = v;
    }

    final protected void unsubscribeOnDetach(@NonNull Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void detach() {
        compositeSubscription.clear();
        this.view = null;
    }

}
