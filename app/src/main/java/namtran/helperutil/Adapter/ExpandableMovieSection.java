package namtran.helperutil.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import UIHelper.RecyclerViewHelper.ViewUtil.StatelessSection;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

public class ExpandableMovieSection extends StatelessSection {

    String title;
    List<Movie> list;
    Context mContext;

    public ExpandableMovieSection(Context context, String title, List<Movie> list) {
        super(R.layout.section_ex5_header, R.layout.recycler_helper_movie_holder);
        this.title = title;
        this.list = list;
        mContext = context;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MovieHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MovieHolder itemHolder = (MovieHolder) holder;

        String name = list.get(position).getName();
        String image = list.get(position).getImage();

        itemHolder.mTextView.setText(name);
        Picasso.with(mContext).load(image).placeholder(R.drawable.empty_photo).into(itemHolder.mImageView);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);

        headerHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, String.format("Clicked on more button from the header of Section %s",
                        title),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
