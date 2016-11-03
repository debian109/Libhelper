package namtran.helperutil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by Nam Tran on 12-Jan-16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected FrameLayout mFrameLayout;
    protected DrawerLayout mLayoutDrawer;
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

    protected abstract String title();

    /**
     * Get ToolBar
     * @return
     */
    protected Toolbar getToolBar()
    {
        return mToolbar;
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

    private void initView() {
        mLayoutDrawer = (DrawerLayout) findViewById(R.id.mLayoutDrawer);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(title());

        if (isShowBackButton()){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    protected boolean isShowBackButton(){
        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
