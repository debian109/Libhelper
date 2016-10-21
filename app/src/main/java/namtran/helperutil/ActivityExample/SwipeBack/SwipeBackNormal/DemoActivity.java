package namtran.helperutil.ActivityExample.SwipeBack.SwipeBackNormal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import UIHelper.SwipeBackLayoutNormal.SwipeBackLayout;
import namtran.helperutil.R;

/**
 * Created by Eric on 15/2/27.
 */
public class DemoActivity extends Activity {
    private SwipeBackLayout swipeBackLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeback_normal_demo);
        initViews();
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
        swipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipe_layout);
        swipeBackLayout.setEnableFlingBack(false);

        swipeBackLayout.setOnPullToBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                progressBar.setProgress((int) (progressBar.getMax() * fractionAnchor));
            }
        });
    }

}
