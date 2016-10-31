package UIHelper.ViewPagerAnimation.Color;

import android.graphics.Color;

/**
 * This Helper is helper to create the performance of change the color with HSV type.
 * Thanks for knomik and his better color-animation.
 * For more: https://github.com/konmik/animated-color
 *
 */

public class ColorChangeHelper {

    private static final float ERROR = 0.001f;

    public float[] toHsvVector(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        float[] vector = new float[3];
        double rad = Math.PI * hsv[0] / 180;
        vector[0] = (float) Math.cos(rad) * hsv[1];
        vector[1] = (float) Math.sin(rad) * hsv[1];
        vector[2] = hsv[2];
        return vector;
    }

    public int getHSVColor(float[] vector0, float[] vector1, float delta) {
        float[] vector = new float[3];
        vector[0] = (vector1[0] - vector0[0]) * delta + vector0[0];
        vector[1] = (vector1[1] - vector0[1]) * delta + vector0[1];
        vector[2] = (vector1[2] - vector0[2]) * delta + vector0[2];

        float[] hsv = new float[3];
        hsv[1] = (float) Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
        hsv[0] = hsv[1] < ERROR ? 0 :
                (float) (Math.atan2(vector[1] / hsv[1], vector[0] / hsv[1]) * 180 / Math.PI);
        if (hsv[0] < 0)
            hsv[0] += 360f;
        hsv[2] = vector[2];

        return Color.HSVToColor(hsv);
    }

    private static ColorChangeHelper ourInstance = new ColorChangeHelper();

    public static ColorChangeHelper getInstance() {
        return ourInstance;
    }

    private ColorChangeHelper() {
    }
}
