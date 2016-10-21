package UIHelper.SwipeBackLayoutTransition;

import android.graphics.Bitmap;

public class SwipeBitmapItem {

    private String id;

    private int mDisplayRefCount;

    private int mWidth, mHeight;

    private boolean mHasBeenDisplayed;

    private Bitmap mBitmap;

    private SwipeBitmapItem(int width, int height) {
        mWidth = width;
        mHeight = height;

        /**
         * screen capture by ARGB_8888 consume 2 times memory than RGB_565,
         * but image quality is good than RGB_565, if you want efficiency more
         * than effect, please change this to RGB_565
         */
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    }

    public static SwipeBitmapItem create(int width, int height) {
        return new SwipeBitmapItem(width, height);
    }

    public int getReferenceCount() {
        return mDisplayRefCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setIsDisplayed(boolean isDisplayed) {
        synchronized (this) {
            if (isDisplayed) {
                mDisplayRefCount++;
                mHasBeenDisplayed = true;
            } else {
                mDisplayRefCount--;
            }
        }
    }

    public void clear() {
        if (hasValidBitmap()) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    private synchronized boolean hasValidBitmap() {
        return mBitmap != null && !mBitmap.isRecycled();
    }
}
