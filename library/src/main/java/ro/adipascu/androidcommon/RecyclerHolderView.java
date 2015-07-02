package ro.adipascu.androidcommon;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import timber.log.Timber;


public class RecyclerHolderView extends FrameLayout {
    private static final String BUNDLE_INNER = "inner";
    private int id;
    private RecyclerView recyclerView;
    private View busyView;
    private View emptyView;
    private Button errorRetryButtonView;
    private TextView errorTextView;
    private RecyclerView.AdapterDataObserver adapterObserver = new AdapterObserver();
    private RecyclerView.Adapter adapter;
    private boolean isBusy = true;
    private boolean hasError;
    private OnRetryListener onRetryListener;

    public RecyclerHolderView(Context context) {
        super(context);
        initLayout();

    }

    public RecyclerHolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
        this.id = getId();
        if (id == NO_ID) {
            throw new UnsupportedOperationException("missing id");
        }

    }

    public RecyclerHolderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
        this.id = getId();
        if (id == NO_ID) {
            throw new UnsupportedOperationException("missing id");
        }
    }

//    @Override
//    protected Parcelable onSaveInstanceState() {
//        Parcelable superState = super.onSaveInstanceState();
//        SavedState savedState = new SavedState(superState);
//
//        return savedState;
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Parcelable state) {
//        SavedState savedState = (SavedState) state;
//        super.onRestoreInstanceState(savedState.getSuperState());
//    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray container) {
        super.dispatchFreezeSelfOnly(container);
        Timber.d("tag", "dispatchSaveInstanceState" + container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray container) {
        super.dispatchThawSelfOnly(container);
        Timber.d("tag", "dispatchRestoreInstanceState" + container);
    }


    private void initLayout() {
//        setSaveEnabled(false);
        if (isInEditMode())
            return;
        inflate(getContext(), R.layout.compund_recycler_holder, this);
        recyclerView = (RecyclerView) findViewById(R.id.common_recycler);
        busyView = findViewById(R.id.common_busy);
        emptyView = findViewById(R.id.common_empty);
        errorRetryButtonView = (Button) findViewById(R.id.common_retry_button);
        errorTextView = (TextView) findViewById(R.id.common_error_text);
        errorRetryButtonView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasError) {
                    hasError = false;
                    onRetryListener.onRetry();
                }
            }
        });
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        getRecyclerView().setAdapter(adapter);
        if (this.adapter != null)
            this.adapter.unregisterAdapterDataObserver(adapterObserver);
        this.adapter = adapter;
        adapter.registerAdapterDataObserver(adapterObserver);
    }

    public void setBusy(boolean isBusy) {
        if (this.isBusy != isBusy) {
            this.isBusy = isBusy;
            checkVisibility();
        }
    }

    private void checkVisibility() {
        recyclerView.setVisibility(GONE);
        busyView.setVisibility(GONE);
        emptyView.setVisibility(GONE);

        errorRetryButtonView.setVisibility(GONE);
        errorTextView.setVisibility(GONE);

        if (hasError) {
            if (onRetryListener != null)
                errorRetryButtonView.setVisibility(VISIBLE);
            errorTextView.setVisibility(VISIBLE);
        } else if (isBusy)
            busyView.setVisibility(VISIBLE);
        else if (adapter == null)
            throw new UnsupportedOperationException();
        else if (adapter.getItemCount() == 0)
            emptyView.setVisibility(VISIBLE);
        else
            recyclerView.setVisibility(VISIBLE);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        getRecyclerView().setLayoutManager(layoutManager);
    }

    public void error() {
        hasError = true;
        checkVisibility();
    }

    public void error(@StringRes int errString) {
        errorTextView.setText(errString);
        error();
    }

    public void clearError() {
        hasError = false;
        checkVisibility();
    }

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
    }

    public interface OnRetryListener {
        void onRetry();
    }

    public static class SavedState extends BaseSavedState {

        public SavedState(Parcelable superState) {
            super(superState);
        }
    }

    private class AdapterObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (adapter == null)
                throw new UnsupportedOperationException();
            checkVisibility();

        }
    }


}
