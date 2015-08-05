package ro.adipascu.androidcommon.sample;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Adi Pascu on 7/21/2015.
 * Email mail@adipascu.ro
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
