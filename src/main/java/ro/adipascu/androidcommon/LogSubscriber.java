package ro.adipascu.androidcommon;

import rx.Subscriber;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created by Adi Pascu on 5/5/2015.
 * Email mail@adipascu.ro
 */
public class LogSubscriber<T> extends Subscriber<T> {
    private final String tag;
    private final Action1<T> onNextListener;

    public LogSubscriber(String tag) {
        this.tag = tag;
        onNextListener = null;
    }

    public LogSubscriber(String tag, Action1<T> onNext) {
        this.tag = tag;
        onNextListener = onNext;
    }

    @Override
    public void onCompleted() {
        Timber.tag(tag);
        Timber.d("onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Timber.tag(tag);
        Timber.d(e, "onError");
    }

    @Override
    public void onNext(T t) {
        Timber.tag(tag);
        Timber.d("onNext " + String.valueOf(t));
        if (onNextListener != null)
            onNextListener.call(t);
    }
}
