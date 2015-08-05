package ro.adipascu.androidcommon;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Adi Pascu on 5/12/2015.
 * Email mail@adipascu.ro
 */
public abstract class MaterialActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected Toolbar initToolbar() {
        return initToolbar(false);
    }

    protected Toolbar initToolbar(boolean homeAsUp) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//      //todo: fix this issue  noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
        return toolbar;
    }

    @NonNull
    @Override
    public ActionBar getSupportActionBar() {
        //noinspection ConstantConditions
        return super.getSupportActionBar();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.inject(this);
    }

    protected void setSubtitle(String subtitle) {
        getSupportActionBar().setSubtitle(subtitle);
    }

    protected void setSubtitle(@StringRes int subtitle) {
        getSupportActionBar().setSubtitle(subtitle);
    }

    protected void setSubtitle(@StringRes int resId, Object... formatArgs) {
        getSupportActionBar().setSubtitle(getString(resId, formatArgs));
    }
}
