package namtran.helperutil.ActivityExample.SwipeBack.SwipeBackTransition;

import android.os.Bundle;

import UIHelper.SwipeBackLayoutTransition.BaseSwipeBackActivityTransition;
import namtran.helperutil.R;

public class ImageActivity extends BaseSwipeBackActivityTransition {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeback_transition_image);
        setTitle("ImageActivity");
    }
}
