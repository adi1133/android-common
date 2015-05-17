package ro.adipascu.androidcommon;

import android.support.v7.widget.SearchView;

import rx.Observable;
import rx.Subscriber;
import rx.android.AndroidSubscriptions;
import rx.functions.Action0;


/**
 * Created by Adi Pascu on 5/4/2015.
 * Email mail@adipascu.ro
 */
public class ViewObservable {
    public static Observable<String> from(final SearchView searchView) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> observer) {
                observer.onNext(searchView.getQuery().toString());
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        observer.onNext(s);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        observer.onNext(s);
                        return true;
                    }
                });
                observer.add(AndroidSubscriptions.unsubscribeInUiThread(new Action0() {
                    @Override
                    public void call() {
                        searchView.setOnQueryTextListener(null);
                    }
                }));
            }
        });
    }


}
