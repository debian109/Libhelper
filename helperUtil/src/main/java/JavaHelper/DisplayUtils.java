package JavaHelper;
import android.content.Context;
import android.content.res.Resources;

/**
 * Unit Conversion
 */
public final class DisplayUtils {

    private static final float  DENSITY = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2Px(int dp) {
        return Math.round(dp * DENSITY);
    }


    public static int px2dp(Context ctx, float pxValue) {
        final float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    public static int dp2px(Context ctx, float dpValue) {
        final float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public static int sp2px(Context ctx, float spValue) {
        final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaledDensity + 0.5f);
    }

    public static int px2sp(Context ctx, float pxValue) {
        final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scaledDensity + 0.5f);
    }

    public static int dp2sp(Context ctx, float dpValue) {
        final float density = ctx.getResources().getDisplayMetrics().scaledDensity;
        final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * density / scaledDensity + 0.5f);
    }

    public static int sp2dp(Context ctx, float spValue) {
        final float density = ctx.getResources().getDisplayMetrics().scaledDensity;
        final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaledDensity / density + 0.5f);
    }
}
