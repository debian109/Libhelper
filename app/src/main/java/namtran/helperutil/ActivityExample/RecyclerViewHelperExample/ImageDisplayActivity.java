package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.annotation.TargetApi;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

import namtran.helperutil.Model.DataMovie;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

/**
 * Created by NamTran on 12/3/2016.
 */

public class ImageDisplayActivity extends AppCompatActivity implements MovieViewpagerFragmentPageAdapter.GetFragment,ViewPager.OnPageChangeListener {

    public static final String POSITIONRECEIVEIMAGE = "position_receive_image";
    public static final String POSITIONSENDIMAGE = "position_send_image";

    private ViewPager viewPager;
    private List<Movie> dataMovies;
    private int mPositionReceive;
    private FragmentImageShow mCurrentFragment;
    private boolean mIsReturning;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            setEnterSharedElementCallback(new SharedElementCallback() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    if (mIsReturning){
                        ImageView sharedElement = mCurrentFragment.getAlbumImage();
                        if (sharedElement == null) {
                            // If shared element is null, then it has been scrolled off screen and
                            // no longer visible. In this case we cancel the shared element transition by
                            // removing the shared element from the shared elements map.
                            names.clear();
                            sharedElements.clear();
                        } else if (mPositionReceive != viewPager.getCurrentItem()) {
                            // If the user has swiped to a different ViewPager page, then we need to
                            // remove the old shared element and replace it with the new shared element
                            // that should be transitioned instead.
                            names.clear();
                            names.add(sharedElement.getTransitionName());
                            sharedElements.clear();
                            sharedElements.put(sharedElement.getTransitionName(), sharedElement);
                        }
                    }
                }
            });
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            dataMovies = bundle.getParcelableArrayList(RecyclerViewHelperActivity.LISTMOVIE);
            mPositionReceive = bundle.getInt(RecyclerViewHelperActivity.POSITIONMOVIESEND);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        MovieViewpagerFragmentPageAdapter movieAdapterViewPager = new MovieViewpagerFragmentPageAdapter(getSupportFragmentManager(),dataMovies,mPositionReceive,this);
        viewPager.setAdapter(movieAdapterViewPager);
        viewPager.setCurrentItem(mPositionReceive);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void finishAfterTransition() {
        mIsReturning = true;
        Intent data = new Intent();
        data.putExtra(POSITIONRECEIVEIMAGE, mPositionReceive);
        data.putExtra(POSITIONSENDIMAGE, viewPager.getCurrentItem());
        setResult(RESULT_OK, data);
        super.finishAfterTransition();
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
        mCurrentFragment = (FragmentImageShow)object;
    }
}
