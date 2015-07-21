package ro.adipascu.androidcommon.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.InjectView;
import ro.adipascu.androidcommon.mvp.ActivityAView;
import ro.adipascu.androidcommon.view.LoadingRecyclerHolder;

/**
 * Created by Adi Pascu on 7/21/2015.
 * Email mail@adipascu.ro
 */
public class MainActivity extends ActivityAView<MainPresenter> {
    @InjectView(R.id.recycler)
    LoadingRecyclerHolder recycler;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        adapter = new MainAdapter();
        recycler.showBusy();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.showData();
        viewsReady();
    }

    @NonNull
    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }
}
