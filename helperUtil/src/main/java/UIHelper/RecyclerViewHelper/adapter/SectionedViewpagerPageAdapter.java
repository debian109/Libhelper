package UIHelper.RecyclerViewHelper.adapter;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Aidan Follestad (afollestad)
 */
public abstract class SectionedViewpagerPageAdapter extends PagerAdapter {

    private final static int VIEW_TYPE_HEADER = 0;
    private final static int VIEW_TYPE_ITEM = 1;

    private final ArrayMap<Integer, Integer> mHeaderLocationMap;

    public SectionedViewpagerPageAdapter() {
        mHeaderLocationMap = new ArrayMap<>();
    }

    public abstract int getSectionCount();

    public abstract int getItemCount(int section);

    public abstract Object instantiateItem(ViewGroup container, int section, int position,int absolutePosition);

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
    public int getCount() {
        int count = 0;
        mHeaderLocationMap.clear();
        for (int s = 0; s < getSectionCount(); s++) {
            mHeaderLocationMap.put(count, s);
            count += getItemCount(s) + 1;
        }
        return count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (isHeader(position)) {
            return null;
        }else {
            return instantiateItem(container,getSection(position)
                    ,getPosition(position),position - (getSection(position) + 1));
        }
    }
}