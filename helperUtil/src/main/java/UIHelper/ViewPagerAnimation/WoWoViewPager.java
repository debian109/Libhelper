package UIHelper.ViewPagerAnimation;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class WoWoViewPager extends ViewPager {

    /**
     * viewAnimations for many views
     */
    private ArrayList<ViewAnimation> viewAnimations;

    private int scrollDuration = 1000;

    public WoWoViewPager(Context context) {
        super(context);
        viewAnimations = new ArrayList<>();
    }

    public WoWoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewAnimations = new ArrayList<>();
    }

    /**
     * add a viewAnimation to WoWo
     * @param viewAnimation the new viewAnimation
     */
    public void addAnimation(ViewAnimation viewAnimation) {
        viewAnimations.add(viewAnimation);
    }

    /**
     * get the starting page and the positionOffset to play all pageAnimations in each viewAnimation
     * @param position start page
     * @param positionOffset positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        super.onPageScrolled(position, positionOffset, positionOffsetPixels);

        for (int i = 0; i < viewAnimations.size(); i++) {
            viewAnimations.get(i).play(position, positionOffset);
            viewAnimations.get(i).end(position - 1);
        }

    }

    public int getScrollDuration() {
        return scrollDuration;
    }

    /**
     * set the duration of swiping in ms
     * @param scrollDuration duration in ms
     */
    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            WoWoScroller scroller = new WoWoScroller(this.getContext(), new AccelerateInterpolator());
            scroller.setmDuration(this.scrollDuration);
            mScroller.set(this, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
