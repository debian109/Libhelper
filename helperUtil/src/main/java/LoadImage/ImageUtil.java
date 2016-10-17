package LoadImage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import LoadImage.BasicLoadImage.HelperHandle;
import LoadImage.BasicLoadImage.HelperHandleResource;
import LoadImage.BasicLoadImage.SaveCacheOnMemory;
import LoadImage.Util.BlurEffect;


/**
 * Created by Nam Tran on 15-Dec-15.
 */
public class ImageUtil {


    public static Bitmap bitmap;

    /**
     * Load image basic from Resource
     * @param context
     * @param resId
     * @param imageView
     * @param reqWidth
     * @param reqHeight
     * @param mMemoryCache
     */
    public static void loadBitmapResource(Context context,int resId, ImageView imageView , int reqWidth, int reqHeight , LruCache<String,Bitmap> mMemoryCache) {
        final String imageKey = String.valueOf(resId);
        if (mMemoryCache != null)
        {
            final Bitmap bitmap = SaveCacheOnMemory.getBitmapFromMemCache(imageKey, mMemoryCache);
            if (bitmap != null)
            {
                imageView.setImageBitmap(bitmap);
            }
            else
            {
                if (HelperHandleResource.cancelPotentialWork(resId, imageView)) {
                    final HelperHandle.BitmapWorkerTaskForResource task = new HelperHandle.BitmapWorkerTaskForResource(context,mMemoryCache,imageView);

                    final HelperHandleResource.AsyncDrawable asyncDrawable =
                            new HelperHandleResource.AsyncDrawable(context.getResources(),bitmap, task);
                    imageView.setImageDrawable(asyncDrawable);
                    task.execute(resId);
                }
            }
        }
        else
        {
            if (HelperHandleResource.cancelPotentialWork(resId, imageView)) {
                final HelperHandle.BitmapWorkerTaskForResource task = new HelperHandle.BitmapWorkerTaskForResource(context,mMemoryCache,imageView);

                final HelperHandleResource.AsyncDrawable asyncDrawable =
                        new HelperHandleResource.AsyncDrawable(context.getResources(), bitmap, task);
                imageView.setImageDrawable(asyncDrawable);
                task.execute(resId);
            }
        }
    }

    /**
     * Get Rounded cornered bitmap
     *
     * @param bitmap
     * @param roundPixels
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int roundPixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = roundPixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * use for getting application Icon.
     *
     * @param mContext
     * @return Icon as drawable from the application
     */
    public static Drawable getAppIcon(Context mContext) {
        Drawable icon = null;
        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        try {
            icon = pm.getApplicationIcon(packageName);
            return icon;
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * convert drawable to bitmap
     *
     * @param mContext
     * @param drawable for convert to bitmap
     * @return bitmap image
     */
    public static Bitmap drawableTobitmap(Context mContext, int drawable) {
        // TODO Auto-generated method stub
        Drawable myDrawable = mContext.getResources().getDrawable(drawable);
        return ((BitmapDrawable) myDrawable).getBitmap();
    }

    /**
     * convert bitmap to drawable
     *
     * @param mContext
     * @param bitmap   for convert to drawable
     * @return drawable image
     */
    public static Drawable bitmapToDrawable(Context mContext, Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    /**
     * apply blur effect on bitmap
     *
     * @param mContext
     * @param bitmap   on which you have to apply effect
     * @return bitmap with effect
     */
    public static Bitmap blurEffectsOnBitmap(Context mContext, Bitmap bitmap) {
        return BlurEffect.fastblur(mContext, bitmap, 12);
    }

    /**
     * apply blue effect on darawable.
     *
     * @param mContext
     * @param drawable for applying effect
     * @param radius   for blur effect 0 to 25
     * @return drawable
     */
    public static Drawable blurEffectsOnDrawable(Context mContext, int drawable, int radius) {

        if (radius == 0)
            radius = 20;
        Bitmap blurBitmap;
        Bitmap bitmap = drawableTobitmap(mContext, drawable);
        blurBitmap = BlurEffect.fastblur(mContext, bitmap, radius);
        return new BitmapDrawable(blurBitmap);
    }

    /**
     * zoom Bitmap
     * @param bitmap
     * @param w
     * @param h
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        Bitmap newbmp = null;
        if(bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidht = ((float) w / width);
            float scaleHeight = ((float) h / height);
            matrix.postScale(scaleWidht, scaleHeight);
            newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        return newbmp;
    }

    /**
     * get ImageType from File
     * @param file
     * @return
     */
    public static String getImageType(File file){
        if(file == null||!file.exists()){
            return null;
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            String type = getImageType(in);
            return type;
        } catch (IOException e) {
            return null;
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch(IOException e){
            }
        }
    }

    /**
     * detect bytes's image type by inputstream
     * @param in
     * @return
     * @see #getImageType(byte[])
     */
    public static String getImageType(InputStream in) {
        if(in == null){
            return null;
        }
        try{
            byte[] bytes = new byte[8];
            in.read(bytes);
            return getImageType(bytes);
        }catch(IOException e){
            return null;
        }
    }

    /**
     * detect bytes's image type
     * @param bytes 2~8 byte at beginning of the image file
     * @return image mimetype or null if the file is not image
     */
    public static String getImageType(byte[] bytes) {
        if (isJPEG(bytes)) {
            return "image/jpeg";
        }
        if (isGIF(bytes)) {
            return "image/gif";
        }
        if (isPNG(bytes)) {
            return "image/png";
        }
        if (isBMP(bytes)) {
            return "application/x-bmp";
        }
        return null;
    }

    private static boolean isJPEG(byte[] b) {
        if (b.length < 2) {
            return false;
        }
        return (b[0] == (byte)0xFF) && (b[1] == (byte)0xD8);
    }

    private static boolean isGIF(byte[] b) {
        if (b.length < 6) {
            return false;
        }
        return b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8'
                && (b[4] == '7' || b[4] == '9') && b[5] == 'a';
    }

    private static boolean isPNG(byte[] b) {
        if (b.length < 8) {
            return false;
        }
        return (b[0] == (byte) 137 && b[1] == (byte) 80 && b[2] == (byte) 78
                && b[3] == (byte) 71 && b[4] == (byte) 13 && b[5] == (byte) 10
                && b[6] == (byte) 26 && b[7] == (byte) 10);
    }

    private static boolean isBMP(byte[] b) {
        if (b.length < 2) {
            return false;
        }
        return (b[0] == 0x42) && (b[1] == 0x4d);
    }

}
