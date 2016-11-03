package namtran.helperutil.ActivityExample.SwipeBack;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import UIHelper.SwipeBackLayoutTransition.SwipeIntentUtils;
import namtran.helperutil.ActivityExample.SwipeBack.SwipeBackNormal.SwipeBackNormalActivity;
import namtran.helperutil.ActivityExample.SwipeBack.SwipeBackTransition.DetailActivity;
import namtran.helperutil.ActivityExample.SwipeBack.SwipeBackTransition.SwipeBackTransitionActivity;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

public class SwipeBackActivity extends BaseActivity {

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_swipe_back);
    }

    @Override
    protected String title() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btnSwipeBackNormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwipeBackActivity.this,SwipeBackNormalActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnSwipeBackTransition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwipeBackActivity.this, SwipeBackTransitionActivity.class);
                SwipeIntentUtils.getInstance().startActivity(SwipeBackActivity.this, intent);
            }
        });

    }
}
