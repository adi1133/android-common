package ro.adipascu.androidcommon.log;

import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created by Adi Pascu on 5/3/2015.
 * Email mail@adipascu.ro
 */
public class Logging {
    public static final RestAdapter.Log RETROFIT_TIMBER = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Timber.tag("Retrofit");
            Timber.i(message);
        }
    };
}
