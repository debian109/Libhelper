package UIHelper.ViewPagerAnimation;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class WoWoScroller extends Scroller {

    private int mDuration = 5000;

    public WoWoScroller(Context context) {
        super(context);
    }

    public WoWoScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public WoWoScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }
}
