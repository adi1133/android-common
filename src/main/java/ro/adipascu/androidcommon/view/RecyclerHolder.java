package ro.adipascu.androidcommon.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import ro.adipascu.androidcommon.R;
import ro.adipascu.androidcommon.Tools;


public class RecyclerHolder extends FrameLayout implements RecyclerHolderInterface {
    private RecyclerView recyclerView;
    private TextView emptyView;
    private RecyclerView.AdapterDataObserver adapterObserver = new AdapterObserver();

    public RecyclerHolder(Context context) {
        super(context);
        init();
    }

    public RecyclerHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode())
            return;
        inflate(getContext(), R.layout.compund_recycler_holder, this);
        recyclerView = (RecyclerView) findViewById(R.id.common_recycler);
        emptyView = (TextView) findViewById(R.id.common_empty);
        emptyView.setText(R.string.common_empty);
        //todo: accept attribute to set emptyView text/string
    }


    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return getRecyclerView().getAdapter();
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter adapter) {
        RecyclerView.Adapter oldAdapter = getAdapter();
        if (oldAdapter != null)
            oldAdapter.unregisterAdapterDataObserver(adapterObserver);
        adapter.registerAdapterDataObserver(adapterObserver);
        getRecyclerView().setAdapter(adapter);
        updateEmptyVisibility();

    }

    @Override
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        getRecyclerView().setLayoutManager(layoutManager);
    }

    private void updateEmptyVisibility() {
        boolean isEmpty = getAdapter().getItemCount() == 0;
        Tools.visible(emptyView, isEmpty);
        Tools.visible(recyclerView, !isEmpty);
    }

    @Override
    public void setEmptyText(@StringRes int emptyText) {
        emptyView.setText(emptyText);
    }

    private class AdapterObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            RecyclerView.Adapter adapter = getAdapter();
            if (adapter == null)
                throw new UnsupportedOperationException();
            updateEmptyVisibility();
        }
    }
}
