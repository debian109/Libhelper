package namtran.helperutil.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.BaseRecyclerViewAdapter;
import adapter.ItemTouchHelper.ItemTouchHelperAdapter;
import adapter.ItemTouchHelper.ItemTouchHelperViewHolder;
import adapter.ItemTouchHelper.OnStartDragListener;
import namtran.helperutil.R;

/**
 * Created by Nam Tran on 25-Mar-16.
 */
public class TouchItemListVieAndGridViewAdapter extends
        BaseRecyclerViewAdapter<String,TouchItemListVieAndGridViewAdapter.MyHolder>
        implements ItemTouchHelperAdapter {

    private List<String> mItems = new ArrayList<>();
    private final OnStartDragListener mDragStartListener;
    /**
     * @param context
     * @param list    the datas to attach the adapter
     * @param mDragStartListener
     */
    public TouchItemListVieAndGridViewAdapter(Context context, List<String> list, OnStartDragListener mDragStartListener) {
        super(context, list);
        mItems = list;
        this.mDragStartListener = mDragStartListener;
    }

    @Override
    protected void bindDataToItemView(final MyHolder myHolder, String item) {
        myHolder.setText(R.id.text,item);
        myHolder.setOnTouchListener(R.id.handle, new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(myHolder);
                }
                return false;
            }
        });
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflateItemView(parent, R.layout.listviewandgridviewtouchi_item_layout));
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public class MyHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder implements
            ItemTouchHelperViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
