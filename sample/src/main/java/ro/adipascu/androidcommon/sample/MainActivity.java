package ro.adipascu.androidcommon.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.adipascu.androidcommon.Tools;
import ro.adipascu.androidcommon.mvp.ActivityAView;
import ro.adipascu.androidcommon.view.LoadingRecyclerHolder;

/**
 * Created by Adi Pascu on 7/21/2015.
 * Email mail@adipascu.ro
 */
public class MainActivity extends ActivityAView<MainPresenter> {
    @Bind(R.id.recycler)
    LoadingRecyclerHolder recycler;
    private CountingAdapter adapter;
//    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
//        adapter = new MainAdapter();
        adapter = new CountingAdapter();
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

    public void addNumber(long number) {
        adapter.addNumber(number);
    }


    static class CountingAdapter extends RecyclerView.Adapter<CountingAdapter.ViewHolder> {
        List<Long> numbers = new ArrayList<>();

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = Tools.inflate(android.R.layout.simple_list_item_1, parent);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setNumber(numbers.get(position));
        }

        @Override
        public int getItemCount() {
            return numbers.size();
        }


        public void addNumber(long number) {
            numbers.add(0, number);
            notifyDataSetChanged();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(android.R.id.text1)
            TextView textView1;


            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void setNumber(long number) {
                textView1.setText(String.valueOf(number));

            }
        }
    }
}
