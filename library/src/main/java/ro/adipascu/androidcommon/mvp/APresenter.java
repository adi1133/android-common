package ro.adipascu.androidcommon.mvp;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.internal.util.SubscriptionList;

/**
 * Created by Adi Pascu on 4/27/15.
 * Email mail@adipascu.ro
 */
//public class APresenter<V extends AView> {
public class APresenter<V> {
    private final SubscriptionList subscriptionList = new SubscriptionList();
    protected V view;

    public void attach(V v) {
        this.view = v;
    }

    final protected void unsubscribeOnDetach(@NonNull Subscription subscription) {
        subscriptionList.add(subscription);
    }

    public void detach() {
        subscriptionList.unsubscribe();
        this.view = null;
    }

}
