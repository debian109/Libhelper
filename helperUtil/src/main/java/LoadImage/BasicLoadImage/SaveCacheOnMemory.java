package LoadImage.BasicLoadImage;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Nam Tran on 12-Dec-15.
 * A memory cache offers fast access to bitmaps at the cost of taking up valuable application memory. The LruCache class
 * (also available in the Support Library for use back to API Level 4) is particularly well suited to the task of caching
 * bitmaps, keeping recently referenced objects in a strong referenced LinkedHashMap and evicting the least recently used member
 * before the cache exceeds its designated size.
     * Note: In the past, a popular memory cache implementation was a SoftReference or WeakReference bitmap cache, however this is
     * not recommended. Starting from Android 2.3 (API Level 9) the garbage collector is more aggressive with collecting soft/weak
     * references which makes them fairly ineffective. In addition, prior to Android 3.0 (API Level 11), the backing data of a bitmap
     * was stored in native memory which is not released in a predictable manner, potentially causing an application to briefly exceed
     * its memory limits and crash.
 */
public class SaveCacheOnMemory {

    /**
     *  Get max available VM memory, exceeding this amount will throw anOutOfMemory exception. Stored in kilobytes as
     *  LruCache takes anint in its constructor.
     */
    public static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    // Use 1/a of the available memory for this memory cache.
    public static final int cacheSize(int a)
    {
        int cacheSize = maxMemory/a;
        return cacheSize;
    }

    /**
     *  if bitmap is saved in cache . Just take it out and show
     * @param key
     * @param mMemoryCache
     * @return
     */
    public static Bitmap getBitmapFromMemCache (String key, LruCache<String,Bitmap> mMemoryCache)
    {
        return mMemoryCache.get(key);
    }

    /**
     * if in cache don't have bitmap will save it in cache
     * @param key
     * @param mMemoryCache
     * @param bitmap
     */
    public static void addBitmapToMemoryCache(String key,LruCache<String,Bitmap> mMemoryCache, Bitmap bitmap) {
        if (SaveCacheOnMemory.getBitmapFromMemCache(key, mMemoryCache) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
}
