package ro.adipascu.androidcommon.mvp;

import android.os.Bundle;

import ro.adipascu.androidcommon.MaterialActivity;

/**
 * Created by Adi Pascu on 5/20/2015.
 * Email mail@adipascu.ro
 */
public abstract class ActivityAView<P extends APresenter> extends MaterialActivity implements AView<P> {
    boolean called = false;

    /**
     * Call this method when the views are set up and {@link #getPresenter()} will not return null
     */
    protected final void viewsReady() {
        called = true;
        //noinspection unchecked
        getPresenter().attach(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (called)
            throw new UnsupportedOperationException("call viewsReady after super.onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!called)
            throw new UnsupportedOperationException("call viewsReady before onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detach();
    }
}
