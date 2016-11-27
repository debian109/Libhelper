package UIHelper.RecyclerViewHelper.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import UIHelper.RecyclerViewHelper.Listener.MultiChoiceAdapterListener;
import UIHelper.RecyclerViewHelper.Listener.OnSwipeTouchListener;


public abstract class MultiChoiceAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    final static String EXCEPTION_MSG_NO_INTERFACE = "An interface must be set in order to make this adapter working";

    public boolean isInMultiChoiceMode = false;
    public boolean isInSingleClickMode = false;

    private MultiChoiceAdapterListener mMultiChoiceListener;

    private Context mContext;

    public MultiChoiceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        final View mCurrentView = holder.itemView;

        if (mMultiChoiceListener != null) {

            //http://stackoverflow.com/questions/19538747/how-to-use-both-ontouch-and-onclick-for-an-imagebutton
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
                    ItemViewSwipeLeft(holder);
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    ItemViewSwipeRight(holder);
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


    public void setMultiChoiceListener(MultiChoiceAdapterListener multiChoiceListener) {
        this.mMultiChoiceListener = multiChoiceListener;
    }

    public void performActivation(View view, boolean state) {
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
    protected abstract void ItemViewSwipeLeft(RecyclerView.ViewHolder holder);

    /**
     * Provide the default behaviour for the item swipe right with multi choice mode disabled
     *
     * @return the onClick action to perform when multi choice selection is off
     */
    protected abstract void ItemViewSwipeRight(RecyclerView.ViewHolder holder);
}
