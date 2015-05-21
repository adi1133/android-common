package ro.adipascu.androidcommon.mvp;

import android.os.Bundle;

import ro.adipascu.androidcommon.MaterialActivity;

/**
 * Created by Adi Pascu on 5/20/2015.
 * Email mail@adipascu.ro
 */
public abstract class ActivityAView<P extends APresenter> extends MaterialActivity implements AView<P> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //noinspection unchecked
        getPresenter().attach(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detach();
    }
}
