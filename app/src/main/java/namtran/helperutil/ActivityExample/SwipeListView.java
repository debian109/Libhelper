package namtran.helperutil.ActivityExample;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import UIHelper.SwipeMenuListView.SwipeMenu;
import UIHelper.SwipeMenuListView.SwipeMenuCreator;
import UIHelper.SwipeMenuListView.SwipeMenuItem;
import UIHelper.SwipeMenuListView.SwipeMenuListView;
import namtran.helperutil.Adapter.AdapterBasic2;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 27-Jan-16.
 */
public class SwipeListView extends BaseActivity {
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.swipelistview_layout);
    }

    @Override
    protected String title() {
        return this.getClass().getSimpleName();
    }

    AdapterBasic2 adapter;
    SwipeMenuListView swipelistview;
    List<String> values = new ArrayList<>();
    int vitri = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swipelistview = (SwipeMenuListView) findViewById(R.id.swipelistview);
        values.add("Android List View");
        values.add("Adapter implementation");
        values.add("Simple List View In Android");
        values.add("Create List View Android");
        values.add("List View Source Code");
        values.add("List View Array Adapter");
        values.add( "Android Example");
        values.add( "Android Example List View");
        adapter = new AdapterBasic2(this,
                R.layout.adapterbasic_layout1, values);
        swipelistview.setAdapter(adapter);
        final SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item = new SwipeMenuItem(SwipeListView.this);
                item.setBackground(new ColorDrawable(Color.rgb(243, 112, 18)));
                item.setWidth(dp2px(90));
                menu.addMenuItem(item);
                SwipeMenuItem item1 = new SwipeMenuItem(SwipeListView.this);
                item1.setBackground(new ColorDrawable(Color.rgb(77, 118, 72)));
                item1.setWidth(dp2px(90));
                menu.addMenuItem(item1);
            }
        };
        swipelistview.setMenuCreator(swipeMenuCreator);
        swipelistview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index)
                {
                    case 0 :
                    {
                        vitri = swipelistview.getFirstVisiblePosition();
                        values.remove(position);
                        adapter.notifyDataSetChanged();
                        swipelistview.setAdapter(adapter);
                        if (swipelistview != null)
                        {
                            if (swipelistview.getCount()>vitri)
                            {
                                swipelistview.setSelectionFromTop(vitri,0);
                            }
                            else {
                                swipelistview.setSelectionFromTop(0,0);
                            }
                        }
                        Toast.makeText(SwipeListView.this,"Done",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 1 :
                    {
                        Toast.makeText(SwipeListView.this,"Fail",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                return false;
            }
        });
    }
    private int dp2px(int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
