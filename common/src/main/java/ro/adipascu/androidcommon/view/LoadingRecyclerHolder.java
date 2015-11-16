package ro.adipascu.androidcommon.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import ro.adipascu.androidcommon.R;
import ro.adipascu.androidcommon.Tools;


public class LoadingRecyclerHolder extends LoadingContainerView implements RecyclerHolderInterface {

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

    @Override
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerHolder.setLayoutManager(layoutManager);
    }

    @Override
    public void setEmptyText(@StringRes int emptyText) {
        recyclerHolder.setEmptyText(emptyText);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerHolder.setAdapter(adapter);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerHolder.getRecyclerView();
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return recyclerHolder.getAdapter();
    }

    @Deprecated
    @Override
    public void showMessage(@StringRes int messageStr) {
        if (getRecyclerView().getAdapter().getItemCount() == 0) {
            throw new UnsupportedOperationException("the adapter should have 0 items");
        }
        setEmptyText(messageStr);
    }

}
