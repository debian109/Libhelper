package namtran.helperutil.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.squareup.picasso.Picasso;

import UIHelper.ScrollViewAuto.AutoScrollableView;
import namtran.helperutil.Model.AutoScrollViewModel;
import namtran.helperutil.R;


public class AutoScrollViewHelper extends AutoScrollableView<AutoScrollViewModel> {

    private LayoutInflater mLayoutInflater;
    private ScaleType mScaleType = null;
    private Context mContext;
    public AutoScrollViewHelper(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public AutoScrollViewHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init(){
        mLayoutInflater = LayoutInflater.from(getContext());
    }
    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        final View imageLayout = mLayoutInflater.inflate(R.layout.poster_list_item, view, false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        if (mScaleType != null) {
            imageView.setScaleType(mScaleType);
        }
        Picasso.with(mContext).load(getItem(position).getImage()).placeholder(R.drawable.empty_photo).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mOnItemViewClickListener != null) {
                    mOnItemViewClickListener.onItemViewClick(v, getItem(position));
                }
            }
        });
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    private OnItemViewClickListener mOnItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener listener) {
        this.mOnItemViewClickListener = listener;
    }

    public interface OnItemViewClickListener {

        void onItemViewClick(View view, Object object);

    }
}
