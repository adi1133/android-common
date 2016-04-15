package ro.adipascu.androidcommon.mvp;

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
    private void viewsReady() {
        if (!called) {
            called = true;
            //noinspection unchecked
            getPresenter().onAttach(this);
        }
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        viewsReady();
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
            throw new UnsupportedOperationException("call setContentView before onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDetach();
    }
}
