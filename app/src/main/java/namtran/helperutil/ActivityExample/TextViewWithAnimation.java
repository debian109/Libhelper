package namtran.helperutil.ActivityExample;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

import Animation.TextViewAnimation.HTextView;
import Animation.TextViewAnimation.HTextViewType;
import Animation.TextViewAnimation.TypeWriter;
import Animation.ViewAnimation.AnimationHelper;
import Animation.ViewAnimation.Techniques;
import namtran.helperutil.BasicActivity.BaseActivity;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 18-Jan-16.
 */
public class TextViewWithAnimation extends BaseActivity implements ViewSwitcher.ViewFactory{
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.textviewwithanimation_layout);
    }

    int a = 0;
    int b = 0;
    TypeWriter typeWriter;
    private TextSwitcher textSwitcher;
    private HTextView hTextView;
    private int mCounter = 10;
    private ImageView img;
    String[] sentences = new String[]{/*"What is design?", "Design", "Design is not just", "what it looks like",
            "and feels like.", "Design", "is how it works.", "- Steve Jobs", "Older people", "sit down and ask,",*/
            "'What is it?'", "but the boy asks,", "'What can I do with it?'.", "- Steve Jobs", "Swift", "Objective-C"
            , "iPhone", "iPad", "Mac Mini", "MacBook Pro", "Mac Pro"};
    private int[] images={R.drawable.vietnam, R.drawable.japan, R.drawable.korea};
    Timer timerText;
    Timer timer;
    AnimationHelper.AnimationView mAnimatiobView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeWriter = (TypeWriter) findViewById(R.id.textTypeWriter);
        typeWriter.setCharacterDelay(500);
        typeWriter.animateText("Happy Birthday");

        textSwitcher = (TextSwitcher) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.img);
        textSwitcher.setFactory(this);

       /* Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);*/

        hTextView = (HTextView) findViewById(R.id.text2);
        hTextView.setTextColor(Color.BLACK);
        hTextView.setBackgroundColor(Color.WHITE);
        hTextView.setAnimateType(HTextViewType.EVAPORATE);
        timerText = new Timer();
        timer = new Timer();
        timerText.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handlertext.post(runnabletext);
            }
        }, 8000, 2000);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handlerView.post(runnableView);
            }
        }, 12000, 6000);
    }

    @Override public View makeView() {

        TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        return t;
    }

    private void AnimateSlideShow()
    {
       /* mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
        textSwitcher.setText(sentences[mCounter]);
        hTextView.animateText(sentences[mCounter]);*/
        if (a < sentences.length){
   //         textSwitcher.setText(sentences[a]);
            hTextView.animateText(sentences[a]);
        }else{
            timerText.cancel();
        }
    }

    private void DisplayAnimation(){
        if (b<10){
            img.setImageResource(images[b%images.length]);
            mAnimatiobView = AnimationHelper.with(AnimationHelper.getRandomTechniques(Techniques.getListTechniques())).duration(5000)
                    .playOn(img);
        }else {
            timer.cancel();
            mAnimatiobView.stop(true);
        }
    }

    final Handler handlertext = new Handler();
    final Runnable runnabletext = new Runnable() {
        @Override
        public void run() {
            AnimateSlideShow();
            a++;
        }
    };

    final Handler handlerView = new Handler();
    final Runnable runnableView = new Runnable() {
        @Override
        public void run() {
            DisplayAnimation();
            b++;
        }
    };
}
