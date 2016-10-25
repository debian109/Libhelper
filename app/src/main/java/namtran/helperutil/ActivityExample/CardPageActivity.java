package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;
import namtran.helperutil.mFragment.PageFragment;

public class CardPageActivity extends BaseActivity {

    private ImageView cardpage_img_background;
    private ViewPager cardview_vp;
    private static final float thresholdOffset = 0.5f;
    private boolean scrollStarted, checkDirection;
    private int positionImage;
    private boolean isLeftorRight;
    private ViewPagerOnPageChangeListener page;
    private ViewPagerOnPageChangeListener pageNext;
    private ViewPagerOnPageChangeListener pagePrevios;

    public interface ViewPagerOnPageChangeListener{
        void onPageScrolled(float positionOffset, int positionOffsetPixels);
        void onPageScrolled(boolean isNext, float positionOffset, int positionOffsetPixels);
        void onPageSelected(int position);
    }

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
        cardview_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                page = (ViewPagerOnPageChangeListener) adapter.getItem(position);
                page.onPageScrolled(positionOffset,positionOffsetPixels);

                /*Log.d("Value offset","positionOffset : " + positionOffset
                        + " \n" + "positionOffsetPixels : " + positionOffsetPixels);
                if (checkDirection) {

                    page = (ViewPagerOnPageChangeListener) adapter.getItem(position);

                    if (thresholdOffset > positionOffset) {
                        Toast.makeText(CardPageActivity.this, "Swiped Right", Toast.LENGTH_SHORT).show();
                        isLeftorRight = true;
                        pageNext = (ViewPagerOnPageChangeListener) adapter.getItem(position + 1);
                    } else {
                        Toast.makeText(CardPageActivity.this, "Swiped Left", Toast.LENGTH_SHORT).show();
                        isLeftorRight = false;
                        pagePrevios = (ViewPagerOnPageChangeListener) adapter.getItem(position - 1);
                    }
                    checkDirection = false;
                }

                if (page != null)
                    page.onPageScrolled(positionOffset,positionOffsetPixels);

                if (isLeftorRight){

                    if (pageNext != null)
                        pageNext.onPageScrolled(true,positionOffset,positionOffsetPixels);

                    Log.d("Position", "Right");

                }else {

                    if (pagePrevios != null)
                        pagePrevios.onPageScrolled(false,positionOffset,positionOffsetPixels);

                    Log.d("Position", "LEFT");

                }*/

            }

            @Override
            public void onPageSelected(int position) {
                if (page != null)
                    page.onPageSelected(position);
                if (pageNext != null)
                    pageNext.onPageSelected(position);
                if (pagePrevios != null)
                    pagePrevios.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true;
                    checkDirection = true;
                }else {
                    scrollStarted = false;
                }
            }
        });
    }
}
