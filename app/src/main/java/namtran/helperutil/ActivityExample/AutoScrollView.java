package namtran.helperutil.ActivityExample;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import UIHelper.Indicator.CircleIndicator;
import UIHelper.ScrollViewAuto.ZoomOutPageTransformer;
import namtran.helperutil.BaseActivity;
import namtran.helperutil.Model.AutoScrollViewModel;
import namtran.helperutil.Adapter.AutoScrollViewHelper;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 15-Mar-16.
 */
public class AutoScrollView extends BaseActivity {

    private AutoScrollViewHelper mPosterView;
    private RadioButton rabAnimation;
    private RadioButton rabNoAnimation;
    private RadioGroup rag;
    private List<AutoScrollViewModel> mListModel = new ArrayList<>();
    private CircleIndicator indicator;

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.autoscrollview_layout);
    }

    @Override
    protected String title() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosterView = (AutoScrollViewHelper) findViewById(R.id.viewPager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        rabAnimation = (RadioButton) findViewById(R.id.rabAnimation);
        rabNoAnimation = (RadioButton) findViewById(R.id.rabNoAnimation);
        rag = (RadioGroup) findViewById(R.id.rag);
        initData();
        //mPosterView.setDisplayImageOptions(displayImageOptions);
        mPosterView.setScaleType(ImageView.ScaleType.FIT_XY);
        mPosterView.addItems(mListModel);
        mPosterView.startAutoScroll(3 * 1000,0);
        indicator.setViewPager(mPosterView);

        mPosterView.setOnItemViewClickListener(new AutoScrollViewHelper.OnItemViewClickListener() {

            @Override
            public void onItemViewClick(View view, Object item) {
                Toast.makeText(AutoScrollView.this, "Click..." + item, Toast.LENGTH_SHORT).show();
            }
        });

        rag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rabAnimation = (RadioButton) group.findViewById(R.id.rabAnimation);
                rabNoAnimation = (RadioButton) group.findViewById(R.id.rabNoAnimation);
                switch (checkedId){
                    case R.id.rabAnimation:
                        mPosterView.setPageTransformer(true, new ZoomOutPageTransformer());
                        Toast.makeText(AutoScrollView.this,"Animation",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rabNoAnimation:
                        mPosterView.setPageTransformer(true, null);
                        Toast.makeText(AutoScrollView.this,"No Animation",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    protected void initData(){
        mListModel.add(new AutoScrollViewModel("https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s240-c/A%252520Photographer.jpg"));
        mListModel.add(new AutoScrollViewModel("https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s240-c/Antelope%252520Butte.jpg"));
        mListModel.add(new AutoScrollViewModel("https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s240-c/Another%252520Rockaway%252520Sunset.jpg"));
        mListModel.add(new AutoScrollViewModel("https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s240-c/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPosterView.stopScroll();
    }
}
