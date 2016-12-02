package namtran.helperutil.ActivityExample.RecyclerViewHelperExample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import UIHelper.RecyclerViewHelper.adapter.SectionedRecyclerViewAdapter;
import namtran.helperutil.Model.Movie;
import namtran.helperutil.R;

public class MovieAdapterRecycler extends SectionedRecyclerViewAdapter {

    private List<DataMovie> allData;
    private Context mContext;
    private TouchItemHolder touchItemHolder;
    private MultiChooseListener multiChooseListener;

    public interface TouchItemHolder{
        void SingleChoose(RecyclerView.ViewHolder holder, int section ,int position);
        void SingleSwipeLeft(RecyclerView.ViewHolder holder);
        void SingleSwipeRight(RecyclerView.ViewHolder holder);
        void SingleClickHeader(List<Movie> movieList);
    }

    public interface MultiChooseListener{
        void MultiChoose(View view, boolean state);
    }

    public MovieAdapterRecycler(Context context,List<DataMovie> allData) {
        super(context);
        this.allData = allData;
        mContext = context;
    }



    public void setTouchItemHolderListener(TouchItemHolder touchItemHolder){
        this.touchItemHolder = touchItemHolder;
    }

    public void setMultiChooseListener(MultiChooseListener multiChooseListener){
        this.multiChooseListener = multiChooseListener;
    }

    @Override
    public int getSectionCount() {
        return allData.size();
    }

    @Override
    public int getItemCount(int section) {
        return allData.get(section).getAllItemsInSection().size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        View v = null;
        if (header) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.section_ex5_header, parent, false);
            return new HeaderViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_helper_movie_holder, parent, false);
            return new MovieHolder(v);
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, final int section) {
        String sectionName = allData.get(section).getHeaderTitle();
        HeaderViewHolder sectionViewHolder = (HeaderViewHolder) holder;
        sectionViewHolder.tvTitle.setText(sectionName);
        sectionViewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchItemHolder != null)
                    touchItemHolder.SingleClickHeader(allData.get(section).getAllItemsInSection());
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int position, int absolutePosition) {
        List<Movie> listMovie = allData.get(section).getAllItemsInSection();

        final MovieHolder itemHolder = (MovieHolder) holder;
        String image = listMovie.get(position).getImage();
        Picasso.with(mContext).load(image).placeholder(R.drawable.empty_photo).into(itemHolder.mImageView);
    }

    public static ImageView getImage(RecyclerView.ViewHolder holder) {
        if (holder instanceof MovieHolder) {
            return ((MovieHolder) holder).mImageView;
        } else {
            return null;
        }
    }

    @Override
    public void onMultiChooseListener(View view, boolean state) {
        if (multiChooseListener != null)
            multiChooseListener.MultiChoose(view,state);
    }

    @Override
    protected void ItemViewClickListener(RecyclerView.ViewHolder holder, int position) {
        if (touchItemHolder != null)
            touchItemHolder.SingleChoose(holder,getSection(position),getPosition(position));
    }

    @Override
    protected void ItemViewSwipeLeft(RecyclerView.ViewHolder holder) {
        if (touchItemHolder != null)
            touchItemHolder.SingleSwipeLeft(holder);
    }

    @Override
    protected void ItemViewSwipeRight(RecyclerView.ViewHolder holder) {
        if (touchItemHolder != null)
            touchItemHolder.SingleSwipeRight(holder);
    }
}
