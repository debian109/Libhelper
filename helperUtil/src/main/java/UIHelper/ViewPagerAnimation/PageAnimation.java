package UIHelper.ViewPagerAnimation;

import android.view.View;

/**
 * PageAnimation play a kind of animation for a view started from a page in viewpager
 */
public abstract class PageAnimation {

    /**
     * start playing this animation at page
     * the animation will be played when the page + 1 page is shown
     */
    private int page;

    /**
     * the animation only plays when the offset of page is large than startOffset
     */
    private float startOffset;

    /**
     * the animation only plays when the offset of page is less than startOffset
     */
    private float endOffset;

    /**
     *
     * @param onView animation will be played on onView
     * @param positionOffset the offset when the viewpager is moving
     *                       notice that the animation will be played in
     *                       positionOffset * percent
     */
    public abstract void play(View onView, float positionOffset);

    public void end(View onView) {
        play(onView, 1);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public float getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(float startOffset) {
        this.startOffset = startOffset;
    }

    public float getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(float endOffset) {
        this.endOffset = endOffset;
    }

}
