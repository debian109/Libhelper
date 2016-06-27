package Animation;

import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nam Tran on 20-Jan-16.
 */
public class SlidingImage {

    int currentimageindex = 0;

    ImageView imageView;
    int[] imageResource;
    Animation animation;
    int delay;
    int period;

    public SlidingImage(ImageView imageView, int[] imageResource, Animation animation, int delay, int period) {
        this.imageView = imageView;
        this.imageResource = imageResource;
        this.animation = animation;
        this.delay = delay;
        this.period = period;
    }

    public void DisplaySlidingImage()
    {
        final Handler handler = new Handler();

        //Create runnable for posting
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AnimateSlideShow();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },delay,period);
    }

    private void AnimateSlideShow()
    {
        imageView.setImageResource(imageResource[currentimageindex%imageResource.length]);
        currentimageindex++;
        imageView.startAnimation(animation);
    }
}
