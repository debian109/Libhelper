package namtran.helperutil.ActivityExample.SwipeBack.SwipeBackTransition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import UIHelper.SwipeBackLayoutTransition.BaseSwipeBackActivityTransition;
import UIHelper.SwipeBackLayoutTransition.SwipeIntentUtils;
import namtran.helperutil.R;

public class DetailActivity extends BaseSwipeBackActivityTransition {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeback_transition_detail);
        setTitle("DetailActivity");
    }

    public void onButtonClicked(View v) {
        Intent intent = new Intent(this, ImageActivity.class);
        SwipeIntentUtils.getInstance().startActivity(this, intent);
    }
}
