package Animation.TextViewAnimation;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nam Tran on 18-Jan-16.
 */
public class TypeWriter extends TextView {

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 500;
    public TypeWriter(Context context) {
        super(context);
    }

    public TypeWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TypeWriter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Handler handler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0,mIndex++));
            if (mIndex<=mText.length()){
                handler.postDelayed(characterAdder,mDelay);
            }
        }
    };

    public void animateText(CharSequence text){
        mText = text;
        mIndex = 0;
        setText("");
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder,mDelay);
    }

    public void setCharacterDelay(long millis)
    {
        mDelay = millis;
    }
}
