package ro.adipascu.androidcommon.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import ro.adipascu.androidcommon.R;
import ro.adipascu.androidcommon.Tools;


public class LoadingRecyclerHolder extends LoadingContainerView {

    private RecyclerHolder recyclerHolder;

    public LoadingRecyclerHolder(Context context) {
        super(context);
        init();
    }

    public LoadingRecyclerHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingRecyclerHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        recyclerHolder = (RecyclerHolder) Tools.inflate(R.layout.common_recycler_holder, this);
        addView(recyclerHolder);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerHolder.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerHolder.setAdapter(adapter);
    }

    public RecyclerView getRecyclerView() {
        return recyclerHolder.getRecyclerView();
    }
}
