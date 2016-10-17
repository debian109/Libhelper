package Animation.TextViewAnimation.animatetext;

import android.graphics.Canvas;
import android.util.AttributeSet;

import Animation.TextViewAnimation.HTextView;


/**
 * interface used in HTextView
 */
public interface IHText {
    void init(HTextView hTextView, AttributeSet attrs, int defStyle);
    void animateText(CharSequence text);
    void onDraw(Canvas canvas);
    void reset(CharSequence text);
}
