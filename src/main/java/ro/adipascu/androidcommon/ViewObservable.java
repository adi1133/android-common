package ro.adipascu.androidcommon;

import android.support.v7.widget.SearchView;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by Adi Pascu on 5/4/2015.
 * Email mail@adipascu.ro
 */
public class ViewObservable {
    public static Observable<String> from(final SearchView searchView) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                subscriber.onNext(searchView.getQuery().toString());
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        subscriber.onNext(s);
                        return checkSub();
                    }

                    private boolean checkSub() {
                        if (subscriber.isUnsubscribed()) {
                            searchView.setOnQueryTextListener(null);
                            return false;
                        }
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        subscriber.onNext(s);
                        return checkSub();
                    }
                });
            }
        });
    }


}
