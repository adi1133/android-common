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
    private CompositeSubscription subscriptionList = new CompositeSubscription();
    protected V view;

    public void attach(V v) {
        this.view = v;
    }

    final protected void unsubscribeOnDetach(@NonNull Subscription subscription) {
        subscriptionList.add(subscription);
    }

    public void detach() {
        subscriptionList.clear();
        this.view = null;
    }

}
