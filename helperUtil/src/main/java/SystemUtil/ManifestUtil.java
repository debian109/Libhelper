package SystemUtil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Nam Tran on 31-Dec-15.
 */
public class ManifestUtil {

    /**
     * get applcation version current in android
     * @param context
     * @return
     */
    public static int getVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
}
