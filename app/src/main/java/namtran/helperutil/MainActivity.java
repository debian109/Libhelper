package namtran.helperutil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import namtran.helperutil.ActivityExample.AutoScrollView;
import namtran.helperutil.ActivityExample.ContextMenuActivity;
import namtran.helperutil.ActivityExample.DisplayimageViewActivity;
import namtran.helperutil.ActivityExample.EditTextMeterial;
import namtran.helperutil.ActivityExample.FancyButtonActivityExample.FancyButtonActivity;
import namtran.helperutil.ActivityExample.FlowingDrawerActivity;
import namtran.helperutil.ActivityExample.GetImeiPhone;
import namtran.helperutil.ActivityExample.GuillotineMenuActivity;
import namtran.helperutil.ActivityExample.ImageSelector;
import namtran.helperutil.ActivityExample.LocationDemo;
import namtran.helperutil.ActivityExample.MaterialDesign;
import namtran.helperutil.ActivityExample.NavigationTransparent;
import namtran.helperutil.ActivityExample.RecycleViewQuickScroll;
import namtran.helperutil.ActivityExample.ScrollAnimationListviewAndRecyclerView;
import namtran.helperutil.ActivityExample.SlideMenuActivity;
import namtran.helperutil.ActivityExample.SwipeListView;
import namtran.helperutil.ActivityExample.TextViewWithAnimation;
import namtran.helperutil.ActivityExample.TouchItemListVieAndGridView;
import namtran.helperutil.ActivityExample.UseRunnable;
import namtran.helperutil.BasicActivity.BaseActivity;

public class MainActivity extends BaseActivity {

    Button testDisplayimageView,testLocation,testEdittext,testImageSelector
            ,testTextViewAnimation,testSwipeListView,testGetImeiPhone,testUseRunnable
            ,testAutoScrollView,testTouchItemListVieAndGridView,testNavigationTransparent
            ,testRecycleViewQuickScroll,testScrollAnimationListviewandRecyclerView
            ,testMaterialDesign,testContextMenu,testGuillotine,testSlideMenu
            ,testFlowingDrawer,testFancyButton;


    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testDisplayimageView = (Button) findViewById(R.id.testDisplayimageView);
        testLocation = (Button) findViewById(R.id.testLocation);
        testEdittext = (Button) findViewById(R.id.testEdittext);
        testImageSelector = (Button) findViewById(R.id.testImageSelector);
        testTextViewAnimation = (Button) findViewById(R.id.testTextView);
        testSwipeListView = (Button) findViewById(R.id.testSwipeListView);
        testGetImeiPhone = (Button) findViewById(R.id.testGetImeiPhone);
        testUseRunnable = (Button) findViewById(R.id.testUseRunnable);
        testAutoScrollView = (Button) findViewById(R.id.testAutoScrollView);
        testNavigationTransparent = (Button) findViewById(R.id.testNavigationTransparent);
        testTouchItemListVieAndGridView = (Button) findViewById(R.id.testTouchItemListVieAndGridView);
        testRecycleViewQuickScroll = (Button) findViewById(R.id.testRecycleViewQuickScroll);
        testScrollAnimationListviewandRecyclerView = (Button) findViewById(R.id.testScrollAnimationListviewandRecyclerView);
        testMaterialDesign = (Button) findViewById(R.id.testMaterialDesign);
        testContextMenu = (Button) findViewById(R.id.testContextMenu);
        testGuillotine = (Button) findViewById(R.id.testGuillotine);
        testSlideMenu = (Button) findViewById(R.id.testSlideMenu);
        testFlowingDrawer = (Button) findViewById(R.id.testFlowingDrawer);
        testFancyButton = (Button) findViewById(R.id.testFancyButton);
        testDisplayimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DisplayimageViewActivity.class);
                startActivity(intent);
            }
        });
        testLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LocationDemo.class);
                startActivity(intent);
            }
        });
        testEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditTextMeterial.class);
                startActivity(intent);
            }
        });
        testImageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageSelector.class);
                startActivity(intent);
            }
        });
        testTextViewAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TextViewWithAnimation.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(MainActivity.this,TouchItemListVieAndGridView.class);
                startActivity(intent);
            }
        });
        testNavigationTransparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NavigationTransparent.class);
                startActivity(intent);
            }
        });
        testRecycleViewQuickScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecycleViewQuickScroll.class);
                startActivity(intent);
            }
        });
        testScrollAnimationListviewandRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScrollAnimationListviewAndRecyclerView.class);
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
        testFlowingDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FlowingDrawerActivity.class);
                startActivity(intent);
            }
        });
        testFancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FancyButtonActivity.class);
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
