package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import namtran.helperutil.Model.DataMovie;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

/**
 * Created by NamTran on 12/3/2016.
 */

public class ImageDisplayActivity extends AppCompatActivity implements MovieViewpagerFragmentPageAdapter.GetFragment,ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<Movie> dataMovies;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            dataMovies = bundle.getParcelableArrayList(RecyclerViewHelperActivity.LISTMOVIE);
            position = bundle.getInt(RecyclerViewHelperActivity.POSITIONMOVIE);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        MovieViewpagerFragmentPageAdapter movieAdapterViewPager = new MovieViewpagerFragmentPageAdapter(getSupportFragmentManager(),dataMovies,this);
        viewPager.setAdapter(movieAdapterViewPager);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (viewPager != null){
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void getPrimaryItem(Object object) {

    }
}
