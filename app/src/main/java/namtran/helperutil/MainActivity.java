package namtran.helperutil;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import namtran.helperutil.ActivityExample.AutoScrollView;
import namtran.helperutil.ActivityExample.ContextMenuActivity;
import namtran.helperutil.ActivityExample.FrameStrimmerActivity;
import namtran.helperutil.ActivityExample.GetImeiPhone;
import namtran.helperutil.ActivityExample.GuillotineMenuActivity;
import namtran.helperutil.ActivityExample.MaterialActivity.MaterialDesign;
import namtran.helperutil.ActivityExample.MaterialIntroActivity;
import namtran.helperutil.ActivityExample.RecyclerViewHelperExample.RecyclerViewHelperActivity;
import namtran.helperutil.ActivityExample.SlideMenuActivity;
import namtran.helperutil.ActivityExample.SwipeBack.SwipeBackActivity;
import namtran.helperutil.ActivityExample.SwipeListView;
import namtran.helperutil.ActivityExample.TouchItemListViewAndGridView;
import namtran.helperutil.ActivityExample.UseRunnable;
import namtran.helperutil.ActivityExample.ViewPagerHelperActivity;
import namtran.helperutil.ActivityExample.WoWoViewPagerExample.WoWoViewPagerActivityExample;

public class MainActivity extends BaseActivity {

    Button testSwipeListView,testGetImeiPhone,testUseRunnable,testAutoScrollView
            ,testTouchItemListVieAndGridView,testMaterialDesign,testContextMenu,testGuillotine
            ,testSlideMenu,testFramStremmer,testSwipeBackLayout,testMaterialIntro,testWoWoViewpager
            ,testViewpagerHelper,testRecyclerViewHelper;


    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_main);
    }

    @Override
    protected String title() {
        return "Example Activity";
    }

    @Override
    protected boolean isShowBackButton() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testSwipeListView = (Button) findViewById(R.id.testSwipeListView);
        testGetImeiPhone = (Button) findViewById(R.id.testGetImeiPhone);
        testUseRunnable = (Button) findViewById(R.id.testUseRunnable);
        testAutoScrollView = (Button) findViewById(R.id.testAutoScrollView);
        testTouchItemListVieAndGridView = (Button) findViewById(R.id.testTouchItemListVieAndGridView);
        testMaterialDesign = (Button) findViewById(R.id.testMaterialDesign);
        testContextMenu = (Button) findViewById(R.id.testContextMenu);
        testGuillotine = (Button) findViewById(R.id.testGuillotine);
        testSlideMenu = (Button) findViewById(R.id.testSlideMenu);
        testFramStremmer = (Button) findViewById(R.id.testFramStremmer);
        testSwipeBackLayout = (Button) findViewById(R.id.testSwipeBackLayout);
        testMaterialIntro = (Button) findViewById(R.id.testMaterialIntro);
        testWoWoViewpager = (Button) findViewById(R.id.testWoWoViewpager);
        testViewpagerHelper = (Button) findViewById(R.id.testViewpagerHelper);
        testRecyclerViewHelper = (Button) findViewById(R.id.testRecyclerViewHelper);

        testSwipeListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SwipeListView.class);
                startActivity(intent);
            }
        });
        testGetImeiPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetImeiPhone.class);
                startActivity(intent);
            }
        });
        testUseRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UseRunnable.class);
                startActivity(intent);
            }
        });
        testAutoScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AutoScrollView.class);
                startActivity(intent);
            }
        });
        testTouchItemListVieAndGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TouchItemListViewAndGridView.class);
                startActivity(intent);
            }
        });
        testMaterialDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MaterialDesign.class);
                startActivity(intent);
            }
        });
        testContextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContextMenuActivity.class);
                startActivity(intent);
            }
        });
        testGuillotine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GuillotineMenuActivity.class);
                startActivity(intent);
            }
        });
        testSlideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SlideMenuActivity.class);
                startActivity(intent);
            }
        });
        testFramStremmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FrameStrimmerActivity.class);
                startActivity(intent);
            }
        });
        testSwipeBackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SwipeBackActivity.class);
                startActivity(intent);
            }
        });
        testMaterialIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MaterialIntroActivity.class);
                startActivity(intent);
            }
        });

        testWoWoViewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WoWoViewPagerActivityExample.class);
                startActivity(intent);
            }
        });

        testViewpagerHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewPagerHelperActivity.class);
                startActivity(intent);
            }
        });

        testRecyclerViewHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecyclerViewHelperActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
