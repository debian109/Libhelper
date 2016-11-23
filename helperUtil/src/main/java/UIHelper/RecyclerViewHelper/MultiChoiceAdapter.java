package UIHelper.RecyclerViewHelper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public abstract class MultiChoiceAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    final static String EXCEPTION_MSG_NO_INTERFACE = "An interface must be set in order to make this adapter working";

    boolean isInMultiChoiceMode = false;
    boolean isInSingleClickMode = false;

    private MultiChoiceAdapterListener mMultiChoiceListener;

    private Context mContext;

    public MultiChoiceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        final View mCurrentView = holder.itemView;

        if (mMultiChoiceListener != null) {

            mCurrentView.setOnTouchListener(new OnSwipeTouchListener(mContext){
                @Override
                public void onClick() {
                    super.onClick();
                    if (isInMultiChoiceMode || isInSingleClickMode)
                        mMultiChoiceListener.onSingleItemClickListener(mCurrentView, holder.getAdapterPosition());
                    else
                        ItemViewClickListener(holder,position);
                }

                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    ItemViewSwipeLeft();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    ItemViewSwipeRight();
                }

                @Override
                public void onLongClick() {
                    super.onLongClick();
                    mMultiChoiceListener.onSingleItemLongClickListener(mCurrentView, holder.getAdapterPosition());
                }
            });

            mMultiChoiceListener.onUpdateItemListener(mCurrentView, holder.getAdapterPosition());

        }else{
            throw new IllegalStateException(EXCEPTION_MSG_NO_INTERFACE);
        }
    }


    void setMultiChoiceListener(MultiChoiceAdapterListener multiChoiceListener) {
        this.mMultiChoiceListener = multiChoiceListener;
    }

    void performActivation(View view, boolean state) {
        if (view != null) {
            setActive(view, state);
        }
    }

    /**
     * Override this method to customize the active item
     *
     * @param view the view to customize
     * @param state true if the state is active/selected
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setActive(View view, boolean state) {
        if (state) {
            view.setAlpha(0.25f);
        } else {
            view.setAlpha(1f);
        }
    }

    /**
     * Provide the default behaviour for the item click with multi choice mode disabled
     *
     * @return the onClick action to perform when multi choice selection is off
     */
    protected abstract void ItemViewClickListener(VH holder, int position);

    /**
     * Provide the default behaviour for the item swipe left with multi choice mode disabled
     *
     * @return the onClick action to perform when multi choice selection is off
     */
    protected abstract void ItemViewSwipeLeft();

    /**
     * Provide the default behaviour for the item swipe right with multi choice mode disabled
     *
     * @return the onClick action to perform when multi choice selection is off
     */
    protected abstract void ItemViewSwipeRight();
}
