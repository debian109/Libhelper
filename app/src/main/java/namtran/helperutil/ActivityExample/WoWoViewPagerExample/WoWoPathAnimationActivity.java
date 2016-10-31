package namtran.helperutil.ActivityExample.WoWoViewPagerExample;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import UIHelper.ViewPagerAnimation.Eases.EaseType;
import UIHelper.ViewPagerAnimation.ViewAnimation;
import UIHelper.ViewPagerAnimation.WoWoPathAnimation;
import UIHelper.ViewPagerAnimation.WoWoPathView;
import UIHelper.ViewPagerAnimation.WoWoUtil;
import UIHelper.ViewPagerAnimation.WoWoViewPager;
import UIHelper.ViewPagerAnimation.WoWoViewPagerAdapter;
import namtran.helperutil.R;

public class WoWoPathAnimationActivity extends AppCompatActivity {

    private WoWoViewPager wowo;
    private WoWoViewPagerAdapter adapter;

    private EaseType easeType = EaseType.EaseInCubic;
    private boolean useSameEaseTypeBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wowo_path_animation);

        init();

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        adapter = new WoWoViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentsNumber(2);
        adapter.setColorsRes(new Integer[]{
                R.color.light_blue,
                R.color.my_pink});
        wowo.setAdapter(adapter);
        setPageTV(wowo);
    }

    int screenW = 0;
    int screenH = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        screenW = WoWoUtil.getScreenWidth(this);
        screenH = WoWoUtil.getScreenHeight(this);

        setPath();
    }

    private void setPath() {
        WoWoPathView pathView = (WoWoPathView)findViewById(R.id.pathview);
        ViewGroup.LayoutParams layoutParams = pathView.getLayoutParams();
        layoutParams.height = screenH;
        // set the pathView a little wider,
        // then the airplane can hide
        layoutParams.width = screenW + 200;
        pathView.setLayoutParams(layoutParams);

        // use this to adjust the path
        int xoff = -300;
        int yoff = screenH - 616 - 300;
        float xScale = 1.5f;
        float yScale = 1;

        Path path = new Path();
        path.moveTo(xScale * (565 + xoff), screenH + yoff);
        path.cubicTo(
                xScale * (509 + xoff), yScale * (385 + yoff),
                xScale * (144 + xoff), yScale * (272 + yoff),
                xScale * (394 + xoff), yScale * (144 + yoff));
        path.cubicTo(
                xScale * (477 + xoff), yScale * (99 + yoff),
                xScale * (596 + xoff), yScale * (91 + yoff),
                xScale * (697 + xoff), yScale * (128 + yoff));
        path.cubicTo(
                xScale * (850 + xoff), yScale * (189 + yoff),
                xScale * (803 + xoff), yScale * (324 + yoff),
                xScale * (66 + xoff), yScale * (307 + yoff));

        // set the path to pathView
        pathView.setPath(path);
        ViewAnimation animation = new ViewAnimation(pathView);
        animation.addPageAnimaition(new WoWoPathAnimation(
                0, 0f, 1f,
                easeType,
                useSameEaseTypeBack));
        wowo.addAnimation(animation);
    }

    private void setPageTV(WoWoViewPager wowo) {
        wowo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((TextView)findViewById(R.id.page)).setText((position + 1) + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        useSameEaseTypeBack = getIntent().getBooleanExtra("useSameEaseTypeBack", true);
        int easeTypeNumber = getIntent().getIntExtra("easeType", -1);
        switch (easeTypeNumber) {
            case 0: easeType = EaseType.EaseInSine; break;
            case 1: easeType = EaseType.EaseOutSine; break;
            case 2: easeType = EaseType.EaseInOutSine; break;
            case 3: easeType = EaseType.EaseInQuad; break;
            case 4: easeType = EaseType.EaseOutQuad; break;
            case 5: easeType = EaseType.EaseInOutQuad; break;
            case 6: easeType = EaseType.EaseInCubic; break;
            case 7: easeType = EaseType.EaseOutCubic; break;
            case 8: easeType = EaseType.EaseInOutCubic; break;
            case 9: easeType = EaseType.EaseInQuart; break;
            case 10: easeType = EaseType.EaseOutQuart; break;
            case 11: easeType = EaseType.EaseInOutQuart; break;
            case 12: easeType = EaseType.EaseInQuint; break;
            case 13: easeType = EaseType.EaseOutQuint; break;
            case 14: easeType = EaseType.EaseInOutQuint; break;
            case 15: easeType = EaseType.EaseInExpo; break;
            case 16: easeType = EaseType.EaseOutExpo; break;
            case 17: easeType = EaseType.EaseInOutExpo; break;
            case 18: easeType = EaseType.EaseInCirc; break;
            case 19: easeType = EaseType.EaseOutCirc; break;
            case 20: easeType = EaseType.EaseInOutCirc; break;
            case 21: easeType = EaseType.EaseInBack; break;
            case 22: easeType = EaseType.EaseOutBack; break;
            case 23: easeType = EaseType.EaseInOutBack; break;
            case 24: easeType = EaseType.EaseInElastic; break;
            case 25: easeType = EaseType.EaseOutElastic; break;
            case 26: easeType = EaseType.EaseInOutElastic; break;
            case 27: easeType = EaseType.EaseInBounce; break;
            case 28: easeType = EaseType.EaseOutBounce; break;
            case 29: easeType = EaseType.EaseInOutBounce; break;
            case 30: easeType = EaseType.Linear; break;
        }
    }
}
