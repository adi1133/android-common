package ro.adipascu.androidcommon;

import android.os.Bundle;

/**
 * Created by Adi Pascu on 5/20/2015.
 * Email mail@adipascu.ro
 */
public abstract class ActivityAView<P extends APresenter> extends MaterialActivity {


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


    protected abstract P getPresenter();
}
