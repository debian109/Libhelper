package UIHelper.ViewPagerAnimation;

import android.view.View;

import java.util.ArrayList;

/**
 * view animation contains page animations
 */
public class ViewAnimation {

    private View onView;
    private ArrayList<ArrayList<PageAnimation>> pageAnimations;

    public ViewAnimation(View onView) {
        this.onView = onView;
    }

    /**
     * add a pageAnimation to the view
     * the pageAnimation plays from pageAnimation.getPage()
     * @param pageAnimation the new pageAnimation
     */
    public void addPageAnimaition(PageAnimation pageAnimation) {
        int page = pageAnimation.getPage();

        if (pageAnimations == null) pageAnimations = new ArrayList<>();
        while (page + 1 > pageAnimations.size()) {
            pageAnimations.add(new ArrayList<PageAnimation>());
        }

        ArrayList<PageAnimation> pageAnimationsInPage = pageAnimations.get(page);
        if (pageAnimationsInPage == null) pageAnimationsInPage = new ArrayList<>();
        pageAnimationsInPage.add(pageAnimation);
    }

    /**
     * play all pageAnimations starting playing at page
     * @param page the starting page, the corresponding animations will end at page + 1
     * @param positionOffset the positionOffset of viewpager
     */
    public void play(int page, float positionOffset) {
        if (page >= pageAnimations.size()) return;
        ArrayList<PageAnimation> pageAnimationsInPage = pageAnimations.get(page);
        if (pageAnimationsInPage != null) {
            for (PageAnimation pageAnimation : pageAnimationsInPage) {
                pageAnimation.play(onView, positionOffset);
            }
        }
    }

    /**
     * force to end the animation
     * @param page the end position of the page
     */
    public void end(int page) {
        if (page >= pageAnimations.size() || page < 0) return;
        ArrayList<PageAnimation> pageAnimationsInPage = pageAnimations.get(page);
        if (pageAnimationsInPage != null) {
            for (PageAnimation pageAnimation : pageAnimationsInPage) {
                pageAnimation.end(onView);
            }
        }
    }

}
