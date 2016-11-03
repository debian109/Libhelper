package UIHelper.RecyclerViewLib.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import UIHelper.RecyclerViewLib.Listener.AdapterListener;


public abstract class AdapterRecycler extends BaseAdapter {

    final static String EXCEPTION_MSG_NO_INTERFACE = "An interface must be set in order to make this adapter working";

    public boolean isInMultiChoiceMode = false;
    public boolean isInSingleClickMode = false;

    private AdapterListener mMultiChoiceListener;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final View mCurrentView = holder.itemView;

        if (mMultiChoiceListener != null) {

            if (isInMultiChoiceMode || isInSingleClickMode) {
                mCurrentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiChoiceListener.onSingleItemClickListener(mCurrentView, holder.getAdapterPosition());
                    }
                });
            } else {
                if (defaultItemViewClickListener(holder, position) != null) {
                    mCurrentView.setOnClickListener(defaultItemViewClickListener(holder, position));
                }
            }

            mCurrentView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mMultiChoiceListener.onSingleItemLongClickListener(mCurrentView, holder.getAdapterPosition());
                    return true;
                }
            });

            mMultiChoiceListener.onUpdateItemListener(mCurrentView, holder.getAdapterPosition());

        }else{
            throw new IllegalStateException(EXCEPTION_MSG_NO_INTERFACE);
        }
    }

    public void setMultiChoiceListener(AdapterListener multiChoiceListener) {
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
    protected View.OnClickListener defaultItemViewClickListener(ViewHolder holder, int position) {
        return null;
    }
}
