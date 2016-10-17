package namtran.helperutil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import namtran.helperutil.ActivityExample.AutoScrollView;
import namtran.helperutil.ActivityExample.ContextMenuActivity;
import namtran.helperutil.ActivityExample.EditTextMeterial;
import namtran.helperutil.ActivityExample.FlowingDrawerActivity;
import namtran.helperutil.ActivityExample.FrameStrimmerActivity;
import namtran.helperutil.ActivityExample.GetImeiPhone;
import namtran.helperutil.ActivityExample.GuillotineMenuActivity;
import namtran.helperutil.ActivityExample.ImageSelector;
import namtran.helperutil.ActivityExample.MaterialDesign;
import namtran.helperutil.ActivityExample.SlideMenuActivity;
import namtran.helperutil.ActivityExample.SwipeListView;
import namtran.helperutil.ActivityExample.TextViewWithAnimation;
import namtran.helperutil.ActivityExample.TouchItemListVieAndGridView;
import namtran.helperutil.ActivityExample.UseRunnable;

public class MainActivity extends BaseActivity {

    Button testEdittext,testImageSelector
            ,testTextViewAnimation,testSwipeListView,testGetImeiPhone,testUseRunnable
            ,testAutoScrollView,testTouchItemListVieAndGridView
            ,testMaterialDesign,testContextMenu,testGuillotine,testSlideMenu
            ,testFlowingDrawer,testFramStremmer;


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
        testEdittext = (Button) findViewById(R.id.testEdittext);
        testImageSelector = (Button) findViewById(R.id.testImageSelector);
        testTextViewAnimation = (Button) findViewById(R.id.testTextView);
        testSwipeListView = (Button) findViewById(R.id.testSwipeListView);
        testGetImeiPhone = (Button) findViewById(R.id.testGetImeiPhone);
        testUseRunnable = (Button) findViewById(R.id.testUseRunnable);
        testAutoScrollView = (Button) findViewById(R.id.testAutoScrollView);
        testTouchItemListVieAndGridView = (Button) findViewById(R.id.testTouchItemListVieAndGridView);
        testMaterialDesign = (Button) findViewById(R.id.testMaterialDesign);
        testContextMenu = (Button) findViewById(R.id.testContextMenu);
        testGuillotine = (Button) findViewById(R.id.testGuillotine);
        testSlideMenu = (Button) findViewById(R.id.testSlideMenu);
        testFlowingDrawer = (Button) findViewById(R.id.testFlowingDrawer);
        testFramStremmer = (Button) findViewById(R.id.testFramStremmer);

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
        testFramStremmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FrameStrimmerActivity.class);
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
