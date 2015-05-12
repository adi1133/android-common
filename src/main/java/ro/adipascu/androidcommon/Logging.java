package ro.adipascu.androidcommon;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
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
}
