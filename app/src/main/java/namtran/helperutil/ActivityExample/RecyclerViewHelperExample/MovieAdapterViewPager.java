package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import UIHelper.RecyclerViewHelper.adapter.SectionedViewpagerAdapter;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

public class MovieAdapterViewPager extends SectionedViewpagerAdapter {

    List<DataMovie> dataMovies;

    public MovieAdapterViewPager(List<DataMovie> dataMovies) {
        this.dataMovies = dataMovies;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getSectionCount() {
        return dataMovies.size();
    }

    @Override
    public int getItemCount(int section) {
        return dataMovies.get(section).getAllItemsInSection().size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int section, int position, int absolutePosition) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.adapter_viewpager_display_image, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        Context context = imageView.getContext();

        container.addView(view, 0);

        List<Movie> listMovie = dataMovies.get(section).getAllItemsInSection();

        String image = listMovie.get(position).getImage();

        Picasso.with(context).load(image).placeholder(R.drawable.empty_photo).into(imageView);

        return view;
    }
}
