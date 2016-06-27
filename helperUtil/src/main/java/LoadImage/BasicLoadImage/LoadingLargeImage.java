package LoadImage.BasicLoadImage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Nam Tran on 11-Dec-15.
 *          Load a Scaled Down Version into Memory
 * Now that the image dimensions are known, they can be used to decide if the full image should be
 loaded into memory or if a subsampled version should be loaded instead. Here are some factors to consider:
 *   - Estimated memory usage of loading the full image in memory.
 *   - Amount of memory you are willing to commit to loading this image given any other memory requirements of your application.
 *   - Dimensions of the target ImageView or UI component that the image is to be loaded into.
 *   - Screen size and density of the current device.
 */
public class LoadingLargeImage {

    /** To tell the decoder to subsample the image, loading a smaller version into memory, set inSampleSize to
     true in your BitmapFactory.Options object. For example, an image with resolution 2048x1536 that is decoded
     with an inSampleSize of 4 produces a bitmap of approximately 512x384. Loading this into memory uses 0.75MB
     rather than 12MB for the full image (assuming a bitmap configuration of ARGB_8888). Here’s a method to calculate
     a sample size value that is a power of two based on a target width and height:
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options , int reqWidth, int reqHeight)
    {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int isSimpleSize = 1;
        if (height > reqHeight || width > reqWidth)
        {
            final int halfHeight = height/2;
            final int halfWidth = width/2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight/isSimpleSize) > reqHeight && (halfWidth/isSimpleSize) > reqWidth)
            {
                isSimpleSize *= 2;
            }
        }
        return isSimpleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int resID, int reqWidth, int reqHeight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;


        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        return BitmapFactory.decodeResource(resources,resID,options);
    }

    public static Bitmap decodeSampledBitmapFromFile(String pathFile, int reqWidth, int reqHeight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        return BitmapFactory.decodeFile(pathFile,options);
    }


}
