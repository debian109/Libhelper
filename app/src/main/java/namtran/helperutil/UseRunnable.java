package namtran.helperutil;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import namtran.helperutil.BasicActivity.BaseActivity;

/**
 * Created by Nam Tran on 15-Mar-16.
 */
public class UseRunnable extends BaseActivity {
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.userunnable_layout);
    }
    LinearLayout linYellow;
    LinearLayout linBlue;
    LinearLayout linRed;

    Handler timerHandler;
    Runnable timerRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linYellow = (LinearLayout)findViewById(R.id.linYellow);
        linBlue = (LinearLayout)findViewById(R.id.linBlue);
        linRed = (LinearLayout)findViewById(R.id.linRed);
        final int[] round = {0};
        final int[] times = {0};
        timerHandler = new Handler();
        timerRunnable= new Runnable() {
            @Override
            public void run() {
                round[0]++;
                Animation(round[0]);
                timerHandler.postDelayed(this, 500); // run every 400 milisecond

                if(round[0] == 3)
                    round[0] = 0;

                times[0]++;

                if(times[0] == 5)
                {
                    finish();
                }
            }
        };
        timerRunnable.run();
    }

    public void Animation(int round)
    {
        switch (round)
        {
            case 1:
                linYellow.setBackgroundColor(Color.parseColor("#02ecc7"));  // e74c3c red
                linBlue.setBackgroundColor(Color.parseColor("#e74c3c"));    // 02ecc7 blue
                linRed.setBackgroundColor(Color.parseColor("#f1c40f"));     // f1c40f
                break;
            case 2:
                linYellow.setBackgroundColor(Color.parseColor("#e74c3c"));
                linBlue.setBackgroundColor(Color.parseColor("#f1c40f"));
                linRed.setBackgroundColor(Color.parseColor("#02ecc7"));
                break;

            case 3:
                linYellow.setBackgroundColor(Color.parseColor("#f1c40f"));
                linBlue.setBackgroundColor(Color.parseColor("#02ecc7"));
                linRed.setBackgroundColor(Color.parseColor("#e74c3c"));
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
    }
}
