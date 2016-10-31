package namtran.helperutil.ActivityExample.WoWoViewPagerExample;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import UIHelper.ViewPagerAnimation.Color.ColorChangeType;
import UIHelper.ViewPagerAnimation.Eases.EaseType;
import UIHelper.ViewPagerAnimation.ViewAnimation;
import UIHelper.ViewPagerAnimation.WoWoAlphaAnimation;
import UIHelper.ViewPagerAnimation.WoWoPathAnimation;
import UIHelper.ViewPagerAnimation.WoWoPathView;
import UIHelper.ViewPagerAnimation.WoWoRotationAnimation;
import UIHelper.ViewPagerAnimation.WoWoScaleAnimation;
import UIHelper.ViewPagerAnimation.WoWoShapeColorAnimation;
import UIHelper.ViewPagerAnimation.WoWoTranslationAnimation;
import UIHelper.ViewPagerAnimation.WoWoUtil;
import UIHelper.ViewPagerAnimation.WoWoViewPager;
import UIHelper.ViewPagerAnimation.WoWoViewPagerAdapter;
import namtran.helperutil.R;

public class AppIntroExampleActivity extends AppCompatActivity {

    private WoWoViewPager wowo;
    private WoWoViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wowo_viewpager_app_intro_example);

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        adapter = new WoWoViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentsNumber(4);
        adapter.setColorRes(R.color.black_background);
        wowo.setAdapter(adapter);

    }

    private int screenW = 1;
    private int screenH = 1;
    private int circleR = 1;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        screenW = WoWoUtil.getScreenWidth(this);
        screenH = WoWoUtil.getScreenHeight(this);
        circleR = (int)Math.sqrt(screenW * screenW + screenH * screenH) + 10;

        RelativeLayout base = (RelativeLayout)findViewById(R.id.base);
        ViewGroup.LayoutParams layoutParams = base.getLayoutParams();
        layoutParams.height = circleR * 2;
        layoutParams.width = circleR * 2;
        base.setLayoutParams(layoutParams);

        RelativeLayout subBase = (RelativeLayout)findViewById(R.id.sub_base);
        layoutParams = subBase.getLayoutParams();
        layoutParams.height = screenH;
        layoutParams.width = screenW;
        subBase.setLayoutParams(layoutParams);

        setCircleAnimation();
        setNightonkeAnimation();
        setPresentsAnimation();
        setBlueStickAnimation();
        setOrangeStickAnimation();
        setWoWoAnimation();
        setViewPagerAnimation();

        setMusicStand();
        setMusicNotes();

        setBigCloud();
        setLittleCloud();
        setPath();
        setOptimized();

        setSun();
        setFree();
        setNightonkeCloud();

    }

    private void setCircleAnimation() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.circle));
        animation.addPageAnimaition(new WoWoShapeColorAnimation(
                0, 0, 1,
                ContextCompat.getColor(this, R.color.gray),
                ContextCompat.getColor(this, R.color.light_blue),
                ColorChangeType.RGB,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoScaleAnimation(
                0, 0, 1,
                circleR * 2 / findViewById(R.id.circle).getWidth(),
                circleR * 2 / findViewById(R.id.circle).getHeight(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setNightonkeAnimation() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.nightonke));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.nightonke).getTranslationX(),
                findViewById(R.id.nightonke).getTranslationY(),
                -screenW,
                findViewById(R.id.nightonke).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setPresentsAnimation() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.presents));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.presents).getTranslationX(),
                findViewById(R.id.presents).getTranslationY(),
                -screenW,
                findViewById(R.id.presents).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setBlueStickAnimation() {
        ImageView blueStick = (ImageView) findViewById(R.id.blue_stick);
        ViewGroup.LayoutParams layoutParams = blueStick.getLayoutParams();
        layoutParams.height = screenH * 4 / 5;
        layoutParams.width = layoutParams.height * 466 / 1002;
        blueStick.setLayoutParams(layoutParams);
        ViewAnimation animation = new ViewAnimation(blueStick);
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.blue_stick).getTranslationX(),
                findViewById(R.id.blue_stick).getTranslationY(),
                blueStick.getTranslationX(),
                screenH,
                EaseType.Linear,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setOrangeStickAnimation() {
        ImageView orangeStick = (ImageView) findViewById(R.id.orange_stick);
        ViewGroup.LayoutParams layoutParams = orangeStick.getLayoutParams();
        layoutParams.height = screenH * 7 / 10;
        layoutParams.width = layoutParams.height * 234 / 866;
        orangeStick.setLayoutParams(layoutParams);
        ViewAnimation animation = new ViewAnimation(orangeStick);
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.orange_stick).getTranslationX(),
                findViewById(R.id.orange_stick).getTranslationY(),
                -screenW,
                orangeStick.getTranslationY(),
                EaseType.Linear,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setWoWoAnimation() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.wowo));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 0,
                -20,
                findViewById(R.id.wowo).getPivotY(),
                0,
                0,
                -15,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 1,
                -20,
                findViewById(R.id.wowo).getPivotY(),
                0,
                0,
                -150,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.wowo).getTranslationX(),
                findViewById(R.id.wowo).getTranslationY(),
                -screenW / 3,
                findViewById(R.id.wowo).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setViewPagerAnimation() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.viewpager));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 0,
                -20,
                findViewById(R.id.viewpager).getPivotY(),
                0,
                0,
                15,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 1,
                -20,
                findViewById(R.id.viewpager).getPivotY(),
                0,
                0,
                150,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.viewpager).getTranslationX(),
                findViewById(R.id.viewpager).getTranslationY(),
                -screenW / 3,
                findViewById(R.id.viewpager).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setMusicStand() {
        ImageView musicStand = (ImageView) findViewById(R.id.music_stand);
        ViewGroup.LayoutParams layoutParams = musicStand.getLayoutParams();
        layoutParams.height = (screenH > 1184 ? 1184 : screenH);
        layoutParams.width = layoutParams.height * 750 / 1184;
        musicStand.setLayoutParams(layoutParams);
        ViewAnimation animation = new ViewAnimation(musicStand);
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0.8f,
                screenW,
                musicStand.getTranslationY(),
                -screenW,
                musicStand.getTranslationY(),
                EaseType.Linear,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 0.5f,
                0,
                0,
                0,
                screenH,
                EaseType.Linear,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setMusicNotes() {
        ImageView musicNotes = (ImageView) findViewById(R.id.music_notes);
        ViewGroup.LayoutParams layoutParams = musicNotes.getLayoutParams();
        layoutParams.height = (screenH > 1184 ? 1184 : screenH);
        layoutParams.width = layoutParams.height * 750 / 1184;
        musicNotes.setLayoutParams(layoutParams);
        ViewAnimation animation = new ViewAnimation(musicNotes);
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                screenW,
                musicNotes.getTranslationY(),
                0,
                0,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0.2f, 1,
                screenW,
                musicNotes.getTranslationY(),
                -screenW,
                musicNotes.getTranslationY(),
                EaseType.EaseOutBack,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 0.5f,
                0,
                0,
                0,
                screenH,
                EaseType.Linear,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setBigCloud() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.big_cloud));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                0,
                -screenH / 2,
                0,
                0,
                EaseType.EaseOutBack,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                -screenH / 2,
                0,
                screenH / 2,
                EaseType.EaseOutBack,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                0,
                0,
                -screenW,
                0,
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setLittleCloud() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.little_cloud));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                0,
                -screenH / 2,
                0,
                0,
                EaseType.EaseOutBack,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                -screenH / 2,
                0,
                screenH / 2,
                EaseType.EaseOutBack,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                0,
                0,
                -screenW,
                0,
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setPath() {
        WoWoPathView pathView = (WoWoPathView)findViewById(R.id.pathview);
        ViewGroup.LayoutParams layoutParams = pathView.getLayoutParams();
        layoutParams.height = screenH;
        layoutParams.width = screenW + 200;
        pathView.setLayoutParams(layoutParams);

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

        pathView.setPath(path);
        ViewAnimation animation = new ViewAnimation(pathView);
        animation.addPageAnimaition(new WoWoPathAnimation(
                1, 0f, 1f,
                EaseType.Linear,
                true));
        animation.addPageAnimaition(new WoWoAlphaAnimation(
                2, 0, 1,
                1,
                0,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                0,
                0,
                -screenW,
                0,
                EaseType.Linear,
                true
        ));
        wowo.addAnimation(animation);
    }

    private void setOptimized() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.optimized));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                screenW,
                0,
                0,
                0,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                screenW,
                0,
                -screenW,
                0,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                0,
                0,
                -screenW,
                0,
                EaseType.Linear,
                true
        ));
        wowo.addAnimation(animation);
    }

    private void setSun() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.sun));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                screenW,
                -screenW,
                0,
                0,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                screenW,
                -screenW,
                -screenW,
                screenW,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                2, 0, 1,
                findViewById(R.id.sun).getPivotX(),
                findViewById(R.id.sun).getPivotY(),
                0,
                0,
                360 * 4,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setFree() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.free));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                screenW,
                0,
                0,
                0,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                screenW,
                0,
                -screenW,
                0,
                EaseType.Linear,
                true
        ));
        wowo.addAnimation(animation);
    }

    private void setNightonkeCloud() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.nightonke_cloud));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 0,
                screenW,
                0,
                0,
                0,
                EaseType.EaseOutBack,
                true
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                2, 0, 1,
                screenW,
                0,
                -screenW,
                0,
                EaseType.EaseOutBack,
                true
        ));
        wowo.addAnimation(animation);
    }
}
