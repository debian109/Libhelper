package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;
import namtran.helperutil.mFragment.PageFragment;

public class CardPageActivity extends BaseActivity {

    private ImageView cardpage_img_background;
    private ViewPager cardview_vp;
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_cardpage_main);
    }

    @Override
    protected String title() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardpage_img_background = (ImageView) findViewById(R.id.cardpage_img_background);
        cardview_vp = (ViewPager) findViewById(R.id.pager);

        final FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return PageFragment.create(position);
            }
        };
        cardview_vp.setAdapter(adapter);
        cardview_vp.setClipToPadding(false);
        cardview_vp.setPageMargin(20);
    }
}
