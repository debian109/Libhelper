package SystemUtil;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Nam Tran on 28-Dec-15.
 */
public class ServiceUtil {

    /**
     *
     * @param context
     * @param serviceClass
     * @return
     */
    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
