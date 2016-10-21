package namtran.helperutil.ActivityExample.SwipeBack.SwipeBackNormal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import UIHelper.SwipeBackLayoutNormal.BaseSwipeBackActivityNormal;
import UIHelper.SwipeBackLayoutNormal.SwipeBackLayout;
import namtran.helperutil.R;

public class CommonActivity extends BaseSwipeBackActivityNormal {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeback_normal_common);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        setTitle("Common");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

}
