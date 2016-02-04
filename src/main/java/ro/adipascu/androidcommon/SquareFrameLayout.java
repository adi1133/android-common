package ro.adipascu.androidcommon;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import static android.view.View.MeasureSpec.UNSPECIFIED;
import static android.view.View.MeasureSpec.getMode;
import static android.view.View.MeasureSpec.getSize;

/**
 * Created by Adrian Pascu on 4/2/2015.
 * mail@adipascu.ro
 */
public class SquareFrameLayout extends FrameLayout {

    public SquareFrameLayout(Context context) {
        super(context);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public SquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getSize(widthMeasureSpec);
        int height = getSize(heightMeasureSpec);
        int widthMode = getMode(widthMeasureSpec);
        int heightMode = getMode(widthMeasureSpec);
        if (widthMode == UNSPECIFIED && heightMode == UNSPECIFIED)
            throw new UnsupportedOperationException("invalid measure specs for square");
        if (width < height || height == UNSPECIFIED)
            heightMeasureSpec = widthMeasureSpec;
        else
            widthMeasureSpec = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        //   super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (maxheight < 0)
//            throw new UnsupportedOperationException("invalid or missing maxHeight");
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxheight, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}
