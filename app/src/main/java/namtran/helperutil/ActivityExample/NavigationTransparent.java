package namtran.helperutil.ActivityExample;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import UIHelper.MaterialMenu.MaterialMenuDrawable;
import UIHelper.MaterialMenu.MaterialMenuView;
import butterknife.Bind;
import butterknife.ButterKnife;
import namtran.helperutil.Adapter.NavigationListRecycleViewAdapter;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 29-Mar-16.
 */
public class NavigationTransparent extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.LayoutDrawer)
    DrawerLayout LayoutDrawer;
    @Bind(R.id.Left_drawer)
    LinearLayout Left_drawer;
    @Bind(R.id.LayoutFrame)
    FrameLayout LayoutFrame;
    @Bind(R.id.user)
    ImageView user;
    @Bind(R.id.rcvListItem)
    RecyclerView rcvListItem;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.material_menu_icon)
    MaterialMenuView material_menu_icon;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    List<String> list = new ArrayList<>();
    boolean direction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationtransparent_layout);
        ButterKnife.bind(NavigationTransparent.this);
        initDrawer();
        initListView();
        material_menu_icon.setOnClickListener(this);
    }

    private void initDrawer(){
        setSupportActionBar(toolbar);
        setMaxWidthNavigation(NavigationTransparent.this, Left_drawer);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(NavigationTransparent.this,LayoutDrawer,R.string.Open,R.string.Close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float moveFactor = slideOffset * 150;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                {
                    setNaviAnimation(slideOffset);
                    LayoutFrame.setTranslationX(moveFactor);
                } else {
                    setNaviAnimation(slideOffset);
                    LayoutFrame.setTranslationX(moveFactor);
                    LayoutDrawer.bringChildToFront(drawerView);
                    LayoutDrawer.requestLayout();
                }
            }

            @Override
            public void onDrawerOpened(android.view.View drawerView) {
                direction = true;
            }

            @Override
            public void onDrawerClosed(android.view.View drawerView) {
                direction = false;
            }
        };
        LayoutDrawer.addDrawerListener(mActionBarDrawerToggle);
    }

    private void setNaviAnimation(float slideOffset){
        material_menu_icon.setTransformationOffset(
                MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                direction ? 2 - slideOffset : slideOffset
        );
    }

    private void initListView(){
        list.add("Nam");
        list.add("Sang");
        list.add("Chấn");
        list.add("Minh");
        list.add("Thảo");
        list.add("Đạt");
        list.add("Luân");
        list.add("Thuận");
        list.add("Nhi");
        list.add("Yến");
        list.add("Việt");
        rcvListItem.setLayoutManager(new LinearLayoutManager(NavigationTransparent.this));
        rcvListItem.setHasFixedSize(true);
        NavigationListRecycleViewAdapter mNavigationListRecycleViewAdapter =
                new NavigationListRecycleViewAdapter(NavigationTransparent.this,list);
        rcvListItem.setAdapter(mNavigationListRecycleViewAdapter);
    }

    public static void setMaxWidthNavigation(Context context, LinearLayout linearLayout){
        int width = context.getResources().getDisplayMetrics().widthPixels;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) linearLayout.getLayoutParams();
        params.width = width;
        linearLayout.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.material_menu_icon){
            LayoutDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
