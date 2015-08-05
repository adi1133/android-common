package ro.adipascu.androidcommon;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;

import ro.adipascu.androidcommon.view.LoadingContainerView;

/**
 * Use {@link ro.adipascu.androidcommon.view.LoadingRecyclerHolder} instead
 */
@Deprecated
public class RecyclerHolderView extends ro.adipascu.androidcommon.view.LoadingRecyclerHolder {
    public RecyclerHolderView(Context context) {
        super(context);
    }

    public RecyclerHolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerHolderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Deprecated
    public void setBusy(boolean isBusy) {
        if (isBusy)
            showBusy();
        else
            showData();
    }

    @Deprecated
    public void error() {
        showError();
    }

    @Deprecated
    public void error(@StringRes int errString) {
        showError(errString);
    }

    @Deprecated
    public void clearError() {
        showData();
    }

    @Deprecated
    public interface OnRetryListener extends LoadingContainerView.OnRetryListener {
    }

}
