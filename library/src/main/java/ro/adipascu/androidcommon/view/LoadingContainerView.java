package ro.adipascu.androidcommon.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ro.adipascu.androidcommon.R;
import ro.adipascu.androidcommon.Tools;

/**
 * Created by Adi Pascu on 7/3/2015.
 * Email mail@adipascu.ro
 */
public class LoadingContainerView extends FrameLayout {
    private View busyContainerView;
    private View errorContainerView;
    //    private Status status;
    private TextView errorTextView;
    private Button errorButton;
    private TextView busyTextView;
    private OnRetryListener onRetryListener;
    private List<WeakReference<View>> childViews;

    public LoadingContainerView(Context context) {
        super(context);
        init();
    }


    public LoadingContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        busyContainerView = Tools.inflate(R.layout.common_busy, this);
        errorContainerView = Tools.inflate(R.layout.common_error, this);

        busyTextView = (TextView) busyContainerView.findViewById(R.id.common_loading_text);

        errorTextView = (TextView) errorContainerView.findViewById(R.id.common_error_text);
        errorButton = (Button) errorContainerView.findViewById(R.id.common_error_retry_button);
        errorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRetryListener != null)
                    onRetryListener.onRetry();
            }
        });

        setOnRetryListener(null);
        addView(busyContainerView);
        addView(errorContainerView);
        childViews = new ArrayList<>();

        showBusy();
    }

    public void showError(@StringRes int errorStr) {
        errorTextView.setText(errorStr);
        Tools.visible(busyContainerView, false);
        Tools.visible(errorContainerView, true);
        setChildrenVisibility(false);
    }

    public void showError() {
        showError(R.string.common_error);
    }

    public void showBusy(@StringRes int busyStr) {
        busyTextView.setText(busyStr);
        Tools.visible(busyContainerView, true);
        Tools.visible(errorContainerView, false);
        setChildrenVisibility(false);
    }

    public void showBusy() {
        showBusy(R.string.common_busy);
    }

    public void showData() {
        Tools.visible(busyContainerView, false);
        Tools.visible(errorContainerView, false);
        setChildrenVisibility(true);
    }


    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
        Tools.visible(errorButton, onRetryListener != null);
    }


    private void setChildrenVisibility(boolean isVisible) {
        Iterator<WeakReference<View>> it = childViews.iterator();
        while (it.hasNext()) {
            WeakReference<View> viewRef = it.next();
            View view = viewRef.get();
            if (view == null)
                it.remove();
            else if (indexOfChild(view) >= 0)
                Tools.visible(view, isVisible);
            else
                Log.d(LoadingContainerView.class.getSimpleName(), "reference warning");
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (childViews != null)
            childViews.add(new WeakReference<>(child));

    }

    //    public enum Status {
//        CONTENT,
//        ERROR,
//        BUSY
//    }
    public interface OnRetryListener {
        void onRetry();
    }
}
