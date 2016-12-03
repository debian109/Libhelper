package UIHelper.RecyclerViewHelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

/**
 * @author Aidan Follestad (afollestad)
 */
public abstract class SectionedViewpagerFragmentPageAdapter extends FragmentStatePagerAdapter {

    private final ArrayMap<Integer, Integer> mHeaderLocationMap;

    public SectionedViewpagerFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        mHeaderLocationMap = new ArrayMap<>();
    }

    public abstract int getSectionCount();

    public abstract int getItemCount(int section);

    public abstract Fragment instantiateItem(int section, int position,int absolutePosition);

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
    public Fragment getItem(int position) {
        if (isHeader(position)) {
            return null;
        }else {
            return instantiateItem(getSection(position)
                    ,getPosition(position),position - (getSection(position) + 1));
        }
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
}