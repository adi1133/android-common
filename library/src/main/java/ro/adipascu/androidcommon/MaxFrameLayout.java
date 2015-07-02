package ro.adipascu.androidcommon;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Adi Pascu on 4/2/2015.
 * Email mail@adipascu.ro
 */
public class MaxFrameLayout extends FrameLayout {
    private final int maxheight;

    public MaxFrameLayout(Context context) {
        super(context);
        maxheight = -1;
    }

    public MaxFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        maxheight = getMaxHeightAttr(context, attrs);
    }


    public MaxFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        maxheight = getMaxHeightAttr(context, attrs);
    }

    private int getMaxHeightAttr(Context context, AttributeSet attrs) {
        TypedArray x = context.obtainStyledAttributes(attrs, R.styleable.MaxFrameLayout);
        int ret = (int) x.getDimension(R.styleable.MaxFrameLayout_maxHeight, -1);
        x.recycle();
        return ret;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //   super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (maxheight < 0)
            throw new UnsupportedOperationException("invalid or missing maxHeight");
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxheight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
