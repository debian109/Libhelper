package UIHelper.SwipeBackLayoutTransition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

public class SwipeIntentUtils {

    private static final SwipeIntentUtils INSTANCE = new SwipeIntentUtils();

    private LinkedHashMap<String, SwipeBitmapItem> mCachedBitmaps;

    private SwipeIntentUtils() {
        mCachedBitmaps = new LinkedHashMap<String, SwipeBitmapItem>(0, 0.75f, true);
    }

    public void clear() {
        for (Map.Entry<String, SwipeBitmapItem> entry : mCachedBitmaps.entrySet()) {
            entry.getValue().clear();
        }
        mCachedBitmaps.clear();
    }

    public void setIsDisplayed(String id, boolean isDisplayed) {
        SwipeBitmapItem item = mCachedBitmaps.get(id);
        if (null != item) {
            item.setIsDisplayed(isDisplayed);
        }
    }

    private SwipeBitmapItem getBitmapItem(int width, int height) {
        int size = mCachedBitmaps.size();

        if (size > 0) {
            SwipeBitmapItem reuseItem = null;
            for (Map.Entry<String, SwipeBitmapItem> entry : mCachedBitmaps.entrySet()) {
                SwipeBitmapItem item = entry.getValue();
                if (item.getReferenceCount() <= 0) {
                    reuseItem = item;
                }
            }

            if (null != reuseItem) {
                return reuseItem;
            } else {
                return crateItem(width, height);
            }
        } else {
            return crateItem(width, height);
        }
    }

    private SwipeBitmapItem crateItem(int width, int height) {
        SwipeBitmapItem item = SwipeBitmapItem.create(width, height);
        String id = "id_" + System.currentTimeMillis();
        item.setId(id);
        mCachedBitmaps.put(id, item);
        return item;
    }

    public static SwipeIntentUtils getInstance() {
        return INSTANCE;
    }

    public Bitmap getBitmap(String id) {
        return mCachedBitmaps.get(id).getBitmap();
    }

    public void startActivity(final Context context, final Intent intent) {
        final View v = ((Activity) context).findViewById(android.R.id.content);

        SwipeBitmapItem item = getBitmapItem(v.getWidth(), v.getHeight());
        final Bitmap bitmap = item.getBitmap();
        intent.putExtra("bitmap_id", item.getId());

        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.draw(new Canvas(bitmap));
                context.startActivity(intent);
            }
        }, 100);
    }
}
