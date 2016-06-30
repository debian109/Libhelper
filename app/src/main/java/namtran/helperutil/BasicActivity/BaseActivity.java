package namtran.helperutil.BasicActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import namtran.helperutil.R;


/**
 * Created by Nam Tran on 12-Jan-16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected FrameLayout mFrameLayout;
    protected DrawerLayout mLayoutDrawer;
    protected NavigationView mNavDrawer;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager mFragmentManager;

    /**
     * Initialization fragment to activity, this fragment will be attached to frameContainer
     * @return
     */
    protected abstract Fragment initFragment();

    /**
     * Initialization View to activity, this view will be attached to frameContainer
     * @return
     */
    protected abstract View initContentView();

    /**
     * method used in order to return the menu of the Navigation Drawer and return the listener to set to the Navigation Drawer.
     * @return the resource ID of the menu to set + the OnNavigationItemSelectedLister of the Navigation Drawer to set.
     */
    protected void setNaviMenu(int menu,NavigationView.OnNavigationItemSelectedListener listener)
    {
        if (setNavigation())
        {
            mNavDrawer.inflateMenu(menu);
            mNavDrawer.setNavigationItemSelectedListener(listener);
        }
    }

    /**
     * method used in order to return the header of the Navigation Drawer.
     * @return the resource ID of the header to set.
     */

    protected void setNaviHeader(int layoutHeader)
    {
        if (setNavigation())
        {
            mNavDrawer.addHeaderView(getView(layoutHeader));
        }
    }

    /**
     * Get ToolBar
     * @return
     */
    protected Toolbar getToolBar()
    {
        return mToolbar;
    }

    protected boolean BackpressToolBar()
    {
        return false;
    }

    /**
     * close navigation drawer
     */
    public void closeNavigationDrawer()
    {
        mLayoutDrawer.closeDrawers();
    }

    public boolean setNavigation()
    {
        return false;
    }

    protected boolean justCallSuper()
    {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!justCallSuper()) {
            setContentView(R.layout.baseactivity);

            mFragmentManager = getSupportFragmentManager();

            Fragment currentFragment = initFragment();
            if (currentFragment != null)
                mFragmentManager.beginTransaction().replace(R.id.mFrameContainer, initFragment()).commit();
            else {
                View view = initContentView();
                mFrameLayout = (FrameLayout) findViewById(R.id.mFrameContainer);
                if (view == null) {
                    throw new RuntimeException("You must init fragment or contentView.");
                }

                mFrameLayout.addView(view);
            }
            initView();
            if (setNavigation())
            {
                mNavDrawer.setVisibility(View.VISIBLE);
                actionBarDrawerToggle = new ActionBarDrawerToggle(BaseActivity.this,mLayoutDrawer,mToolbar,R.string.app_name,R.string.app_name);
                mLayoutDrawer.setDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();
            }
        }
        if (isShowLifeCycleLog())
        {
            Log.d("Lifecycle", "onCreate " + getClass().toString());
        }
    }

    /**
     * method is called to set layout for view or fragment
     * @param idLayout
     * @return
     */
    protected View getView(int idLayout){
        return getLayoutInflater().inflate(idLayout, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void CustomToolBar(int icon,int logo,int colorbackground,int colorTitle,String titledefault, int themer,float elevation)
    {
        if (icon != 0)
        {
            mToolbar.setNavigationIcon(icon);
        }else if (logo != 0)
        {
            mToolbar.setLogo(logo);
        }else if (colorbackground != 0)
        {
            mToolbar.setBackgroundColor(colorbackground);
        }else if (colorTitle != 0 && titledefault != null)
        {
            mToolbar.setTitleTextColor(colorTitle);
        }
        else if (themer != 0)
        {
            mToolbar.setPopupTheme(themer);
        }else if (elevation != 0)
        {
            mToolbar.setElevation(elevation);
        }
    }

    private void initView() {
        mLayoutDrawer = (DrawerLayout) findViewById(R.id.mLayoutDrawer);
        mNavDrawer = (NavigationView) findViewById(R.id.mNavDrawer);
        mNavDrawer.setVisibility(View.GONE);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        if (BackpressToolBar())
        {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    protected void hideToolbar()
    {
        mToolbar.setVisibility(View.GONE);
    }

    protected void showToolbar()
    {
        mToolbar.setVisibility(View.VISIBLE);
    }


    /**
     * show log
     *
     * @return
     */
    protected boolean isShowLifeCycleLog()
    {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isShowLifeCycleLog())
        {
            Log.d("Lifecycle","onStart " + getClass().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isShowLifeCycleLog())
        {
            Log.d("Lifecycle","onResume " + getClass().toString());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isShowLifeCycleLog())
        {
            Log.d("Lifecycle","onPause " + getClass().toString());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isShowLifeCycleLog())
        {
            Log.d("Lifecycle","onStop " + getClass().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isShowLifeCycleLog())
        {
            Log.d("Lifecycle","onDestroy " + getClass().toString());
        }
    }
}
