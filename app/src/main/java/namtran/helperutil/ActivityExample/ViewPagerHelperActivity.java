package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import UIHelper.ViewPagerHelper.InfinitePagerAdapter;
import UIHelper.ViewPagerHelper.InfiniteViewPager;
import UIHelper.ViewPagerHelper.transform.CubeInTransformer;
import UIHelper.ViewPagerHelper.transform.FlipHorizontalTransformer;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

public class ViewPagerHelperActivity extends BaseActivity {

    InfiniteViewPager view;
    InfinitePagerAdapter pagerAdapter;

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_viewpager_helper_main);
    }

    @Override
    protected String title() {
        return getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = (InfiniteViewPager) findViewById(R.id.view);
        pagerAdapter = new InfinitePagerAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return ModelObject.values().length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ModelObject modelObject = ModelObject.values()[position];
                LayoutInflater inflater = LayoutInflater.from(ViewPagerHelperActivity.this);
                ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), container, false);
                container.addView(layout);
                return layout;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                ModelObject customPagerEnum = ModelObject.values()[position];
                return ViewPagerHelperActivity.this.getString(customPagerEnum.getTitleResId());
            }

        });

        view.setAdapter(pagerAdapter);
        view.setPageTransformer(true,new FlipHorizontalTransformer());
        view.setScrollDurationFactor(5d);

    }

    public enum ModelObject {

        RED(R.string.black, R.layout.viewpager_helper_view_black),
        BLUE(R.string.blue, R.layout.viewpager_helper_view_blue),
        GREEN(R.string.red, R.layout.viewpager_helper_view_red);

        private int mTitleResId;
        private int mLayoutResId;

        ModelObject(int titleResId, int layoutResId) {
            mTitleResId = titleResId;
            mLayoutResId = layoutResId;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public int getLayoutResId() {
            return mLayoutResId;
        }

    }
}
