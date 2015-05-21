package ro.adipascu.androidcommon;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

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
        setSupportActionBar(toolbar);
//      //todo: fix this issue  noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
        return toolbar;
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
}
