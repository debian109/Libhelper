package UIHelper.PullToZoom.manager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

public class ExpandStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    public ExpandStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    // The array of dimensions, [0] is wide, and [1] is high
    private int[] measuredDimension = new int[2];

    // Used to compare the peer / column that item sin wide / high
    private int[] dimension;

    @Override

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        // Wide mode + size
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        // High mode + size
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        // The initial value of the width and height of its own
        int width = 0;
        int height = 0;
        // The number of items
        int count = getItemCount();
        // The number of columns for item
        int span = getSpanCount();
        // Create an array based on the number of rows or columns
        dimension = new int[span];

        for (int i = 0; i < count; i++) {
            measureScrapChild(recycler, i, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), measuredDimension);

            // If it is a vertical list, calculate the item's height, otherwise calculate the width
            //Log.d("LISTENER", "position " + i + " height = " + measuredDimension[1]);
            if (getOrientation() == VERTICAL) {
                dimension[findMinIndex(dimension)] += measuredDimension[1];
            } else {
                dimension[findMinIndex(dimension)] += measuredDimension[0];
            }
        }
        if (getOrientation() == VERTICAL) {
            height = findMax(dimension);
        } else {
            width = findMax(dimension);
        }

        switch (widthMode) {
            // When the control width is match_parent, the width is the width of the parent control
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case View.MeasureSpec.AT_MOST:
                break;
            case View.MeasureSpec.UNSPECIFIED:
                break;
        }
        switch (heightMode) {
            // When the height of the control is match_parent, the height is the height of the parent control
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case View.MeasureSpec.AT_MOST:
                break;
            case View.MeasureSpec.UNSPECIFIED:
                break;
        }
        // Set the measurement size
        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension) {

        // Traverse all items one by one
        if (position < getItemCount()) {
            try {
                View view = recycler.getViewForPosition(position);//Fix Dynamically adds TimesOutOfBoundsException

                if (view != null) {
                    RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
                    int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, getPaddingLeft() + getPaddingRight(), lp.width);
                    int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), lp.height);
                    // Sub-view to measure, and then getMeasuredWidth () can be obtained by measuring the width of the high similar
                    view.measure(childWidthSpec, childHeightSpec);
                    // Put the width and height of the item in the array
                    measuredDimension[0] = view.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    measuredDimension[1] = view.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                    recycler.recycleView(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Gets the subscript of the smallest element in the array
     */
    private int findMinIndex(int[] array) {
        int index = 0;
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }

}
