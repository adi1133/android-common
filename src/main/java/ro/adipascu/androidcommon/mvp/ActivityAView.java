package ro.adipascu.androidcommon.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Adi Pascu on 5/20/2015.
 * Email mail@adipascu.ro
 */
public abstract class ActivityAView<P extends APresenter> extends AppCompatActivity implements AView<P> {
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
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public ActionBar getSupportActionBar() {
        ActionBar bar = super.getSupportActionBar();
        if (bar == null)
            throw new NullPointerException();
        return bar;
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
