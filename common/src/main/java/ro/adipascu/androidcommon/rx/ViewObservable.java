package ro.adipascu.androidcommon.rx;

import android.support.v7.widget.SearchView;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;

import rx.Observable;
import rx.functions.Func1;


@Deprecated
/**
 * Class is deprecated use RxBinding instead
 */
public class ViewObservable {
    /**
     * This is deprecated, use {@code RxSearchView#queryTextChangeEvents} instead
     */
    @Deprecated
    public static Observable<String> from(final SearchView searchView) {
        return RxSearchView.queryTextChangeEvents(searchView).map(new Func1<SearchViewQueryTextEvent, String>() {
            @Override
            public String call(SearchViewQueryTextEvent searchViewQueryTextEvent) {
                return searchViewQueryTextEvent.queryText().toString();
            }
        });
    }


}
