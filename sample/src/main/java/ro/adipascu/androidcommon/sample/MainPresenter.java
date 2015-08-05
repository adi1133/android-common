package ro.adipascu.androidcommon.sample;

import java.util.concurrent.TimeUnit;

import ro.adipascu.androidcommon.mvp.APresenter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Adi Pascu on 7/21/2015.
 * Email mail@adipascu.ro
 */
public class MainPresenter extends APresenter<MainActivity> {


    private static Subscription xxx;

    @Override
    public void attach(MainActivity mainActivity) {
        super.attach(mainActivity);
        Subscription sub = Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                view.addNumber(aLong);
            }
        });
        xxx = sub;
        unsubscribeOnDetach(xxx);
    }

    @Override
    public void detach() {
        super.detach();
//        xxx.unsubscribe();
    }
}
