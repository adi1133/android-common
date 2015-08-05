package ro.adipascu.androidcommon.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ro.adipascu.androidcommon.MaterialActivity;
import ro.adipascu.androidcommon.Tools;
import ro.adipascu.androidcommon.view.RecyclerHolder;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Adi Pascu on 7/21/2015.
 * Email mail@adipascu.ro
 */
public class SometimesEmptyActivity extends MaterialActivity {
    @InjectView(R.id.recycler)
    RecyclerHolder recyclerHolder;
    private SometimesEmptyAdapter adapter;
    private Subscription sub;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sub.unsubscribe();
        sub = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sometimes_empty_activity);
        initToolbar();
        this.adapter = new SometimesEmptyAdapter();
        recyclerHolder.setLayoutManager(new LinearLayoutManager(this));
        recyclerHolder.setAdapter(adapter);
        sub = Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(new Func1<Long, Boolean>() {
            @Override
            public Boolean call(Long aLong) {
                return aLong % 2 == 0;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                adapter.setEmpty(aBoolean);
            }
        });
    }

    static class SometimesEmptyAdapter extends RecyclerView.Adapter<SometimesEmptyAdapter.ViewHolder> {


        private boolean isEmpty = true;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(Tools.inflate(android.R.layout.simple_list_item_1, parent));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return isEmpty ? 0 : 40;
        }

        public void setEmpty(boolean isEmpty) {
            this.isEmpty = isEmpty;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @InjectView(android.R.id.text1)
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.inject(this, itemView);
                textView.setText("This is an element!!");
            }
        }
    }
}
