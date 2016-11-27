package UIHelper.RecyclerViewHelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by NamTran on 11/27/2016.
 */

public class SampleItemDecoration extends RecyclerView.ItemDecoration {

    Paint paint = new Paint();

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            paint.setColor(Color.parseColor("#8BAAC3"));
            if (parent.getChildLayoutPosition(view) == RecyclerView.NO_POSITION) {
                continue;
            }

            // Compute bounds of cell in layout
            Rect bounds = new Rect(
                    layoutManager.getDecoratedLeft(view),
                    layoutManager.getDecoratedTop(view),
                    layoutManager.getDecoratedRight(view),
                    layoutManager.getDecoratedBottom(view)
            );

            // Add space between cell backgrounds
            bounds.inset(2, 2);

            c.drawRect(bounds, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(10, 10, 10, 10); // Specify spacing between items in grid
    }
}
