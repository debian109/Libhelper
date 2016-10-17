package LoadImage.BasicLoadImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Nam Tran on 11-Dec-15.
 */
public class HelperHandle {
    // Support for working with Images in Resource
    public static class BitmapWorkerTaskForResource extends AsyncTask<Integer, Void, Bitmap> {

        /**
         * The WeakReference to the ImageView ensures that the AsyncTask does not prevent the ImageView and anything it references
         * from being garbage collected. There’s no guarantee the ImageView is still around when the task finishes, so you must also
         * check the reference in onPostExecute(). The ImageView may no longer exist, if for example, the user navigates away from
         * the activity or if a configuration change happens before the task finishes.
         */

        private final WeakReference<ImageView> imageViewReference;
        public int data;
        Context mContext;
        LruCache<String,Bitmap> mMemoryCache;

        public BitmapWorkerTaskForResource(Context context,LruCache<String,Bitmap> mMemoryCache,ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            mContext = context;
            this.mMemoryCache = mMemoryCache;
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        public BitmapWorkerTaskForResource(Context context,ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            mContext = context;
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            final Bitmap bitmap;
            if (mMemoryCache != null)
            {
                bitmap = LoadingLargeImage.decodeSampledBitmapFromResource(mContext.getResources(), data, 100, 100);
                SaveCacheOnMemory.addBitmapToMemoryCache(String.valueOf(data), mMemoryCache, bitmap);
            }
            else
            {
                bitmap = LoadingLargeImage.decodeSampledBitmapFromResource(mContext.getResources(), data, 100, 100);
            }
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                final HelperHandle.BitmapWorkerTaskForResource bitmapWorkerTask =
                        HelperHandleResource.getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
