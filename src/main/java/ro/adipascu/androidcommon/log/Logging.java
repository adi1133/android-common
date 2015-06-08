package ro.adipascu.androidcommon.log;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;
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

    public static void init(Context context, boolean isDebug) {
        if (isDebug) {
            Timber.plant(new Timber.DebugTree());
        } else {
            CrashlyticsCore crashCore = new CrashlyticsCore();
//            Fabric.with(context, new Answers(), new Beta(), new CrashlyticsCore());
            Fabric.with(context, crashCore);
            Timber.plant(new CrashlyticsTree(crashCore));
        }
    }

    private static class CrashlyticsTree extends Timber.Tree {
        private final CrashlyticsCore crashlytics;

        public CrashlyticsTree(CrashlyticsCore crashlytics) {
            this.crashlytics = crashlytics;
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority > Log.DEBUG) {
                if (t != null)
                    crashlytics.logException(t);
                if (tag == null)
                    tag = Crashlytics.TAG;
                crashlytics.log(priority, tag, message);
            }
        }
    }
}
