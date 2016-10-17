package adapter.listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lizhangqu on 2015/6/3.
 */
public interface OnTouchItemClickListener<T> {
    void onTouchListener(View view,MotionEvent event, T item);
}
