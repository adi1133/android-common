package ro.adipascu.androidcommon.log;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created by Adi Pascu on 5/3/2015.
 * Email mail@adipascu.ro
 */
public class Logging {


    public static void init(Context context, boolean isDebug) {
        if (isDebug) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Fabric.with(context, new Crashlytics());
            Timber.plant(new CrashlyticsTree());
        }
    }


    private static class CrashlyticsTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority > Log.DEBUG) {
                if (t != null)
                    Crashlytics.logException(t);
                if (tag == null)
                    tag = Crashlytics.TAG;
                Crashlytics.log(priority, tag, message);
            }
        }
    }

    public static final RestAdapter.Log RETROFIT_TIMBER = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Timber.tag("Retrofit");
            Timber.i(message);
        }
    };
}
