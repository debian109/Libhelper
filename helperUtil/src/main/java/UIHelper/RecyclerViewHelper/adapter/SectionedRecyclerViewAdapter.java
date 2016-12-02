package UIHelper.RecyclerViewHelper.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Aidan Follestad (afollestad)
 */
public abstract class SectionedRecyclerViewAdapter extends MultiChoiceAdapter {

    private final static int VIEW_TYPE_HEADER = 0;
    private final static int VIEW_TYPE_ITEM = 1;

    private final ArrayMap<Integer, Integer> mHeaderLocationMap;

    public SectionedRecyclerViewAdapter(Context context) {
        super(context);
        mHeaderLocationMap = new ArrayMap<>();
    }

    public abstract int getSectionCount();

    public abstract int getItemCount(int section);

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header);

    public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section);

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int position, int absolutePosition);

    public abstract void onMultiChooseListener(View view, boolean state);

    public final boolean isHeader(int position) {
        return mHeaderLocationMap.get(position) != null;
    }

    // returns section along with offsetted position
    protected int[] getSectionIndexAndRelativePosition(int itemPosition) {
        synchronized (mHeaderLocationMap) {
            Integer lastSectionIndex = -1;
            for (final Integer sectionIndex : mHeaderLocationMap.keySet()) {
                if (itemPosition > sectionIndex) {
                    lastSectionIndex = sectionIndex;
                } else {
                    break;
                }
                lastSectionIndex = sectionIndex;
            }
            return new int[]{mHeaderLocationMap.get(lastSectionIndex), itemPosition - lastSectionIndex - 1};
        }
    }

    protected int getSection(int position){
        return getSectionIndexAndRelativePosition(position)[0];
    }

    protected int getPosition(int position){
        return getSectionIndexAndRelativePosition(position)[1];
    }

    @Override
    public final int getItemCount() {
        int count = 0;
        mHeaderLocationMap.clear();
        for (int s = 0; s < getSectionCount(); s++) {
            mHeaderLocationMap.put(count, s);
            count += getItemCount(s) + 1;
        }
        return count;
    }

    /**
     * @hide
     * @deprecated
     */
    @Override
    @Deprecated
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent, viewType == VIEW_TYPE_HEADER);
    }

    /**
     * @hide
     * @deprecated
     */
    @Deprecated
    @Override
    public final int getItemViewType(int position) {
        if (isHeader(position)) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    /**
     * @hide
     * @deprecated
     */
    @Override
    @Deprecated
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        if (isHeader(position)) {
            onBindHeaderViewHolder(holder, mHeaderLocationMap.get(position));
        } else {
            onBindViewHolder(holder, getSection(position),
                    // offset section view positions
                    getPosition(position),
                    position - (getSection(position) + 1));
        }
    }

    @Override
    public void setActive(View view, boolean state) {
        onMultiChooseListener(view,state);
    }
}