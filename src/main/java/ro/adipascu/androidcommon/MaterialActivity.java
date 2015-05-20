package ro.adipascu.androidcommon;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Adi Pascu on 5/12/2015.
 * Email mail@adipascu.ro
 */
public class MaterialActivity extends AppCompatActivity {
    protected Toolbar initToolbar() {
        return initToolbar(false);
    }

    protected Toolbar initToolbar(boolean homeAsUp) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.core_up);
        setSupportActionBar(toolbar);
//      //todo: fix this issue  noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
        return toolbar;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        VMSActivity.checkPlayServices(this);
    }
}
