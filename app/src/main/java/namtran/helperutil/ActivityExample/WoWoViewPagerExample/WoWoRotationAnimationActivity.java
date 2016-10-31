package namtran.helperutil.ActivityExample.WoWoViewPagerExample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import UIHelper.ViewPagerAnimation.Eases.EaseType;
import UIHelper.ViewPagerAnimation.ViewAnimation;
import UIHelper.ViewPagerAnimation.WoWoRotationAnimation;
import UIHelper.ViewPagerAnimation.WoWoViewPager;
import UIHelper.ViewPagerAnimation.WoWoViewPagerAdapter;
import namtran.helperutil.R;

public class WoWoRotationAnimationActivity extends AppCompatActivity {

    private WoWoViewPager wowo;
    private WoWoViewPagerAdapter adapter;

    private EaseType easeType = EaseType.EaseInCubic;
    private boolean useSameEaseTypeBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wowo_rotation_animation);

        init();

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        adapter = new WoWoViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentsNumber(5);
        adapter.setColorRes(R.color.white);
        wowo.setAdapter(adapter);
        setPageTV(wowo);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        TextView textView1 = (TextView)findViewById(R.id.test1);
        TextView textView2 = (TextView)findViewById(R.id.test2);
        setAnimation(
                textView1,
                textView1.getPivotX(),
                textView1.getPivotY());
        setAnimation(
                textView2,
                textView2.getPivotX() - 50,
                textView2.getPivotY() - 50);
    }

    private void setAnimation(View view, float pivotX, float pivotY) {
        ViewAnimation animation = new ViewAnimation(view);
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0f, 1f,
                pivotX, pivotY,
                0,
                0,
                180,
                easeType,
                useSameEaseTypeBack));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                1, 0f, 1f,
                pivotX, pivotY,
                0,
                60,
                180,
                easeType,
                useSameEaseTypeBack));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                2, 0f, 1f,
                pivotX, pivotY,
                -45,
                60,
                180,
                easeType,
                useSameEaseTypeBack));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                3, 0f, 1f,
                pivotX, pivotY,
                0,
                0,
                0,
                easeType,
                useSameEaseTypeBack));
        wowo.addAnimation(animation);
    }

    private void setPageTV(WoWoViewPager wowo) {
        wowo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
