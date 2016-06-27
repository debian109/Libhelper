package UIHelper.Validation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;

/**
 * Created by Nam Tran on 31-Dec-15.
 */
public class EdittextMeterial {

    public static boolean isLight(int color) {
        return Math.sqrt(
                Color.red(color) * Color.red(color) * .241 +
                        Color.green(color) * Color.green(color) * .691 +
                        Color.blue(color) * Color.blue(color) * .068) > 130;
    }

    public static int dp2px(Context context, float dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return Math.round(px);
    }

}
