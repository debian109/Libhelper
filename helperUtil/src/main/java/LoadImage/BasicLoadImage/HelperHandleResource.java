package LoadImage.BasicLoadImage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Nam Tran on 11-Dec-15.
 */
public class HelperHandleResource {

    /**
     * Create a dedicated Drawable subclass to store a reference back to the worker task. In this case, a BitmapDrawable
     * is used so that a placeholder image can be displayed in the ImageView while the task completes:
     */
    public static class AsyncDrawable extends BitmapDrawable
    {
        private final WeakReference<HelperHandle.BitmapWorkerTaskForResource> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             HelperHandle.BitmapWorkerTaskForResource bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<HelperHandle.BitmapWorkerTaskForResource>(bitmapWorkerTask);
        }

        public HelperHandle.BitmapWorkerTaskForResource getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    /**
     * The cancelPotentialWork method referenced in the code sample above checks if another running task is already associated
     * with the ImageView. If so, it attempts to cancel the previous task by calling cancel(). In a small number of cases, the
     * new task data matches the existing task and nothing further needs to happen.
     * @param data
     * @param imageView
     * @return
     */
    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final HelperHandle.BitmapWorkerTaskForResource bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    /**
     * A helper method, getBitmapWorkerTask(), is used above to retrieve the task associated with a particular
     * @param imageView
     * @return
     */
    public static HelperHandle.BitmapWorkerTaskForResource getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }


}
