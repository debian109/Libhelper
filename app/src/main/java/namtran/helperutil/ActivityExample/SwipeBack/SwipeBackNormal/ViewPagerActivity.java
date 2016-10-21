package namtran.helperutil.ActivityExample.SwipeBack.SwipeBackNormal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import UIHelper.SwipeBackLayoutNormal.BaseSwipeBackActivityNormal;
import UIHelper.SwipeBackLayoutNormal.SwipeBackLayout;
import namtran.helperutil.ActivityExample.SwipeBack.SwipeBackNormal.adapter.TestAdapter;
import namtran.helperutil.R;

public class ViewPagerActivity extends BaseSwipeBackActivityNormal {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeback_normal_viewpager);
        initViews();
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_viewpager);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_demo);
        TestAdapter adapter = new TestAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }

}
