package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;
import namtran.helperutil.Model.Movie;

public class MovieViewpagerFragmentPageAdapter extends FragmentStatePagerAdapter {

    public interface GetFragment {
        void getPrimaryItem(Object object);
    }

    List<Movie> movies;
    GetFragment getFragment;
    int positonReceive;

    public MovieViewpagerFragmentPageAdapter(FragmentManager fm, List<Movie> movies,int positonReceive, GetFragment getFragment) {
        super(fm);
        this.movies = movies;
        this.positonReceive = positonReceive;
        this.getFragment = getFragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (getFragment != null)
            getFragment.getPrimaryItem(object);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentImageShow.newInstance(movies.get(position),positonReceive,position);
    }

    @Override
    public int getCount() {
        return movies.size();
    }
}
