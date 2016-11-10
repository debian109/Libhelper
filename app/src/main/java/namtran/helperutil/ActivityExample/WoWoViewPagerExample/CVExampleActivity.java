package namtran.helperutil.ActivityExample.WoWoViewPagerExample;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import UIHelper.ViewPagerAnimation.Color.ColorChangeType;
import UIHelper.ViewPagerAnimation.Eases.EaseType;
import UIHelper.ViewPagerAnimation.ViewAnimation;
import UIHelper.ViewPagerAnimation.WoWoBackgroundColorAnimation;
import UIHelper.ViewPagerAnimation.WoWoPathAnimation;
import UIHelper.ViewPagerAnimation.WoWoPathView;
import UIHelper.ViewPagerAnimation.WoWoRotationAnimation;
import UIHelper.ViewPagerAnimation.WoWoScaleAnimation;
import UIHelper.ViewPagerAnimation.WoWoShapeColorAnimation;
import UIHelper.ViewPagerAnimation.WoWoTextViewSizeAnimation;
import UIHelper.ViewPagerAnimation.WoWoTranslationAnimation;
import UIHelper.ViewPagerAnimation.WoWoUtil;
import UIHelper.ViewPagerAnimation.WoWoViewPager;
import UIHelper.ViewPagerAnimation.WoWoViewPagerAdapter;
import namtran.helperutil.R;

public class CVExampleActivity extends AppCompatActivity {

    private WoWoViewPager wowo;
    private WoWoViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wowo_viewpager_cvexample);

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        adapter = new WoWoViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentsNumber(3);
        adapter.setColorRes(android.R.color.transparent);
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

        setBase();
        setLogo();
        setName();
        setCV();
        setForAndroidDeveloper();
        setUniversityIcon();
        setUniversityText();
        setMailIcon();
        setMailText();

        setProjects();
        setBraeco();
        setBraecoMore();
        setCoCoin();
        setCoCoinMore();
        setLeeCo();
        setLeeCoMore();

        setCircle();
        setPath();
        setBlogAndGithub();
    }

    private void setBase() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.base));
        animation.addPageAnimaition(new WoWoBackgroundColorAnimation(
                0, 0, 1,
                ContextCompat.getColor(this, R.color.light_blue),
                ContextCompat.getColor(this, R.color.my_pink),
                ColorChangeType.RGB,
                EaseType.Linear,
                true
        ));
        wowo.addAnimation(animation);
    }

    private void setLogo() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.cv_logo));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.cv_logo).getTranslationX(),
                findViewById(R.id.cv_logo).getTranslationY(),
                -screenW / 2 + 150,
                -screenH / 2 + 200,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoScaleAnimation(
                0, 0, 1,
                0.5f,
                0.5f,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setName() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.name));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.name).getTranslationX(),
                findViewById(R.id.name).getTranslationY(),
                - screenW / 2 + 150 + WoWoUtil.dp2px(105, this) + 20,
                - screenH / 2 + 200 - WoWoUtil.dp2px(70, this),
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTextViewSizeAnimation(
                0, 0, 1,
                30f,
                22f,
                EaseType.Linear,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setCV() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.cv));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 0,
                -20,
                findViewById(R.id.cv).getPivotY(),
                0,
                0,
                -15,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 1,
                -20,
                findViewById(R.id.cv).getPivotY(),
                0,
                0,
                -150,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.cv).getTranslationX(),
                findViewById(R.id.cv).getTranslationY(),
                -screenW / 3,
                findViewById(R.id.cv).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setForAndroidDeveloper() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.for_android_developer));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 0,
                screenW + 80,
                findViewById(R.id.for_android_developer).getPivotY(),
                0,
                0,
                10,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoRotationAnimation(
                0, 0, 1,
                screenW + 80,
                findViewById(R.id.for_android_developer).getPivotY(),
                0,
                0,
                150,
                EaseType.EaseInBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.for_android_developer).getTranslationX(),
                findViewById(R.id.for_android_developer).getTranslationY(),
                -screenW / 3,
                findViewById(R.id.for_android_developer).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setUniversityIcon() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.university_icon));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.university_icon).getTranslationX(),
                findViewById(R.id.university_icon).getTranslationY(),
                - screenW,
                0,
                EaseType.EaseInCubic,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setUniversityText() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.university_text));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.university_text).getTranslationX(),
                findViewById(R.id.university_text).getTranslationY(),
                screenW,
                0,
                EaseType.EaseInCubic,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setMailIcon() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.mail_icon));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.mail_icon).getTranslationX(),
                findViewById(R.id.mail_icon).getTranslationY(),
                - screenW,
                0,
                EaseType.EaseInCubic,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setMailText() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.mail_text));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.mail_text).getTranslationX(),
                findViewById(R.id.mail_text).getTranslationY(),
                screenW,
                0,
                EaseType.EaseInCubic,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setProjects() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.projects));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                screenW,
                findViewById(R.id.projects).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.projects).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setBraeco() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.braeco));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1f,
                screenW,
                findViewById(R.id.braeco).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.braeco).getTranslationY(),
                screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setBraecoMore() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.braeco_more));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0.2f, 1,
                screenW,
                findViewById(R.id.braeco_more).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.braeco_more).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setCoCoin() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.cocoin));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1f,
                screenW,
                findViewById(R.id.cocoin).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.cocoin).getTranslationY(),
                screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setCoCoinMore() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.cocoin_more));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0.2f, 1,
                screenW,
                findViewById(R.id.cocoin_more).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.cocoin_more).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setLeeCo() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.leeco));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1f,
                screenW,
                findViewById(R.id.leeco).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.leeco).getTranslationY(),
                screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setLeeCoMore() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.leeco_more));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0.2f, 1,
                screenW,
                findViewById(R.id.leeco_more).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                0,
                findViewById(R.id.leeco_more).getTranslationY(),
                -screenW,
                0,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setCircle() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.circle));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.cv_logo).getTranslationX(),
                findViewById(R.id.cv_logo).getTranslationY(),
                -screenW / 2 + 150,
                -screenH / 2 + 200,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoShapeColorAnimation(
                1, 0, 1,
                ContextCompat.getColor(this, R.color.my_pink),
                ContextCompat.getColor(this, R.color.light_blue),
                ColorChangeType.RGB,
                EaseType.Linear,
                true
        ));
        animation.addPageAnimaition(new WoWoScaleAnimation(
                1, 0, 1,
                circleR * 2 / findViewById(R.id.circle).getWidth(),
                circleR * 2 / findViewById(R.id.circle).getHeight(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }

    private void setPath() {
        WoWoPathView pathView = (WoWoPathView)findViewById(R.id.pathview);
        ViewGroup.LayoutParams layoutParams = pathView.getLayoutParams();
        layoutParams.height = screenH;
        layoutParams.width = screenW;
        pathView.setLayoutParams(layoutParams);

        int xoff = 0;
        int yoff = screenH - 576 - 100;
        float xScale = 1f;
        float yScale = 1;

        Path path = new Path();
        path.moveTo(xScale * (screenW + xoff + 50), 167 + yoff);
        path.cubicTo(
                xScale * (654 + xoff), yScale * (492 + yoff),
                xScale * (336 + xoff), yScale * (583 + yoff),
                xScale * (-150 + xoff), yScale * (576 + yoff));

        pathView.setPath(path);
        ViewAnimation animation = new ViewAnimation(pathView);
        animation.addPageAnimaition(new WoWoPathAnimation(
                1, 0f, 1f,
                EaseType.Linear,
                true));
        wowo.addAnimation(animation);
    }

    private void setBlogAndGithub() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.blog_and_github));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.blog_and_github).getTranslationX(),
                screenH,
                0,
                0,
                EaseType.EaseOutBack,
                false
        ));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                1, 0, 1,
                findViewById(R.id.blog_and_github).getTranslationX(),
                screenH,
                0,
                -screenH,
                EaseType.EaseOutBack,
                false
        ));
        wowo.addAnimation(animation);
    }

}
