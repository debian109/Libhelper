package UIHelper.MaterialIntro.listeners.scrollListeners;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import UIHelper.MaterialIntro.adapter.SlideFragment;
import UIHelper.MaterialIntro.SlidesAdapter;
import UIHelper.MaterialIntro.listeners.IPageScrolledListener;
import UIHelper.MaterialIntro.parallax.Parallaxable;

public class ParallaxScrollListener implements IPageScrolledListener {
    private SlidesAdapter adapter;

    public ParallaxScrollListener(SlidesAdapter adapter) {
        this.adapter = adapter;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void pageScrolled(int position, float offset) {
        if (position != adapter.slidesCount()) {
            Fragment fragment = adapter.getItem(position);
            Fragment fragmentNext = getNextFragment(position);

            if (fragment != null && fragment instanceof Parallaxable) {
                ((Parallaxable) fragment).setOffset(offset);
            }

            if (fragmentNext != null && fragment instanceof Parallaxable) {
                ((Parallaxable) fragmentNext).setOffset(offset - 1);
            }
        }
    }

    @Nullable
    private SlideFragment getNextFragment(int position) {
        if (position < adapter.getLastItemPosition()) {
            return adapter.getItem(position + 1);
        }
        return null;
    }
}