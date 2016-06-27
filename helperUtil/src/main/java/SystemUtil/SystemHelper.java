package SystemUtil;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import vn.namtran.basichelper.R;

/**
 * Created by Nam Tran on 11-Dec-15.
 */
public class SystemHelper {

    public static int UI_DENSITY;
    public static int UI_SIZE;
    public static int UI_YAHOO_ALLOW;

    /**
     * Pvhones name access to mobile
     * @return Phone name
     */
    public static String getMobileName(){
        return  Build.MANUFACTURER+" "+ Build.MODEL;
    }

    /**
     * Get phone type
     * @return Phone type
     */
    public static String getPhoneType(){
        String phoneType=Build.MODEL;
        return phoneType;
    }

    /**
     * Get the system version number
     * @return System version number
     */
    public static int getOSVersion(){
        return Build.VERSION.SDK_INT;
    }

    /**
     * Get the phone number, almost get less
     * @param context Context
     * @return Phone number, almost get less
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getApplicationContext().getSystemService(
                        Context.TELEPHONY_SERVICE);
        String phoneNum = mTelephonyMgr.getLine1Number();
        return TextUtils.isEmpty(phoneNum) ? "" : phoneNum;
    }

    /**
     * get IMEI devide
     * @param context
     * @return DevideId
     */
    public static String getDevideId(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * Check tepetature battery
     * @param context
     * @return
     */
    public static float BatteryTemperature(Context context)
    {
       Intent intent = context.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
       float temp = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0))/10;
       return temp;
    }

    public static boolean checkEmulator()
    {
        boolean result = Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknow")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK build for x86")
                || Build.MANUFACTURER.contains("Genymotion");
        if (result)
            return true;
        result |= Build.BRAND.startsWith("generic")
                    && Build.DEVICE.startsWith("generic");
        if (result)
            return true;
        result |= "google_sdk".equals(Build.PRODUCT);
        return result;
    }

    /**
     * Function to print screen resolution, screen inches and density of android
     * device.
     *
     * @param ctx Activity Context
     */

    @SuppressWarnings("deprecation")
    public static void getDisplayDpi(Context ctx) {

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        double screenInches = Math.sqrt(x + y);
        int screenInch = (int) Math.round(screenInches);
        int dapi = dm.densityDpi;

        Log.d("Resolution X", String.valueOf(width));
        Log.d("Resolution Y", String.valueOf(height));
        Log.d("screeninch", String.valueOf(screenInch));
        Log.d("dapi", String.valueOf(dapi));

        try {
            switch (dm.densityDpi) {

                case DisplayMetrics.DENSITY_LOW:

                    UI_DENSITY = 120;

                    if (screenInch <= 7) {
                        UI_SIZE = 4;
                        UI_YAHOO_ALLOW = 125;

                    } else {
                        UI_SIZE = 10;
                    }

                    break;
                case DisplayMetrics.DENSITY_MEDIUM:

                    UI_DENSITY = 160;

                    if (screenInch <= 7) {

                        // For devices having width 320
                        if (width == 320) {
                            UI_YAHOO_ALLOW = 105;
                            UI_SIZE = 3;
                        } else if (width == 480) {
                            UI_YAHOO_ALLOW = 200;
                            UI_SIZE = 4;
                        } else {
                            UI_YAHOO_ALLOW = 1;
                            UI_SIZE = 7;
                        }
                    } else {
                        UI_SIZE = 10;
                        UI_YAHOO_ALLOW = 1;
                    }

                    break;

                case DisplayMetrics.DENSITY_HIGH:

                    UI_DENSITY = 240;
                    UI_YAHOO_ALLOW = 375;

                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    UI_DENSITY = 320;

                    if (screenInch < 7) {
                        UI_YAHOO_ALLOW = 55;
                    } else {
                        if (width >= 720 && width < 1280) {
                            UI_SIZE = 7;
                            UI_YAHOO_ALLOW = 475;
                        } else if (width >= 1280) {
                            UI_SIZE = 10;
                            UI_YAHOO_ALLOW = 1;
                        } else {
                            UI_YAHOO_ALLOW = 1;
                        }
                    }

                    break;

                case 213:
                    UI_DENSITY = 213;
                    UI_YAHOO_ALLOW = 95;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showWifiDisabledAlert(String msg, final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ctx.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void showLocationDisabledAlert(String msg, final Activity activity) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    /**
     * Changes mobile profile to "Silent" or "Vibrate" or "Normal" mode
     *
     * @param context
     * @param mode    types of mode  - "0- Silent"
     *                - "1 - Vibrate"
     *                - "2 - Normal"
     */
    public static void chooseProfile(Context context, int mode) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (mode == 0)
            audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        else if (mode == 1)
            audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        else if (mode == 2)
            audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    /**
     * Enable or Disable BlueTooth
     * @param action
     */
    @SuppressLint("DefaultLocale")
    public static void onBlueTooth(String action) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (action.toLowerCase().equalsIgnoreCase("on")) {
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
            }
        }

        if (action.toLowerCase().equalsIgnoreCase("off")) {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();
            }
        }
    }

    /**
     * use for make local notification from application
     *
     * @param mContext
     * @param title    for the Notification
     * @param message  for the notification
     * @param mIntent  for open activity to open on touch of notification
     */
    @SuppressLint("NewApi")
    @SuppressWarnings({"static-access"})
    public static void sendLocatNotification(Context mContext, String title,
                                             String message, Intent mIntent) {
        System.out.println("called: " + title + " : " + message);
        int appIconResId = 0;
        PendingIntent pIntent = null;
        if (mIntent != null)
            pIntent = PendingIntent.getActivity(mContext, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = pm.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            appIconResId = applicationInfo.icon;
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Notification notification = new Notification.Builder(mContext)
        // .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
        // .setContentTitle(title).setContentText(message)
        // .setContentIntent(pIntent).getNotification();

        Notification notification;
        if (mIntent == null) {
            notification = new Notification.Builder(mContext)
                    .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
                    .setContentTitle(message)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setAutoCancel(true)
                    .setContentText(message)
                    .setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(), 0))
                    .getNotification();

        } else {
            notification = new Notification.Builder(mContext)
                    .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
                    .setContentTitle(message)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setContentIntent(pIntent).getNotification();
        }
        // Remove the notification on click
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        NotificationManager manager = (NotificationManager) mContext
                .getSystemService(mContext.NOTIFICATION_SERVICE);
        // manager.notify(0, notification);
        manager.notify(R.string.app_name, notification);
    }

    /**
     * use for getting device height
     *
     * @param mContext
     * @return height of your device
     */
    public static int getDeviceHeight(Context mContext) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    /**
     * use for getting device width
     *
     * @param mContext
     * @return width of your device
     */
    public static int getDeviceWidth(Context mContext) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    /**
     * get random number from your boundary
     *
     * @param number max number till you want get random.
     * @return random number
     */
    public static int getRandom(int number) {
        Random rand = new Random();
        return rand.nextInt(number);
    }

    /**
     * Use current volume in application
     *
     * @param mContext
     */
    public static void setCurrentDeviceVolume(Context mContext) {
        AudioManager audioManager = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume,
                0);
    }

    public static ArrayList<String> getNameEmailDetails(Context context) {
        ArrayList<String> emlRecs = new ArrayList<String>();
        HashSet<String> emlRecsHS = new HashSet<String>();
        ContentResolver cr = context.getContentResolver();
        String[] PROJECTION = new String[]{ContactsContract.RawContacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.CommonDataKinds.Email.DATA,
                ContactsContract.CommonDataKinds.Photo.CONTACT_ID};
        String order = "CASE WHEN "
                + ContactsContract.Contacts.DISPLAY_NAME
                + " NOT LIKE '%@%' THEN 1 ELSE 2 END, "
                + ContactsContract.Contacts.DISPLAY_NAME
                + ", "
                + ContactsContract.CommonDataKinds.Email.DATA
                + " COLLATE NOCASE";
        String filter = ContactsContract.CommonDataKinds.Email.DATA + " NOT LIKE ''";
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, filter, null, order);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    // names comes in hand sometimes
                    String name = cur.getString(1);
                    String emlAddr = cur.getString(3);

                    // keep unique only
                    if (emlRecsHS.add(emlAddr.toLowerCase())) {
                        emlRecs.add(name + ", " + emlAddr);
                    }
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return emlRecs;
    }

    /**
     * show number nitify on icon of app
     * @param context
     * @param count = number
     */
    public static void setBadge(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    /**
     * get Launcher ClassName
     * @param context
     * @return
     */
    public static String getLauncherClassName(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;
            if (pkgName.equalsIgnoreCase(context.getPackageName())) {
                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }
        return null;
    }

    /**
     * get PackageInfo
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        // packagemanager
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo;
    }

    /**
     * get AppName
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        PackageInfo packInfo = getPackageInfo(context);
        if (packInfo == null) {
            return "";
        }
        return context.getString(packInfo.applicationInfo.labelRes);
    }

    /**
     * get System Version
     *
     * @return
     */
    public static int getSystemVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    @TargetApi(11)
    public static void recreate(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        }
    }

    @TargetApi(11)
    public static void setLayerType(View view, int layerType, Paint paint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setLayerType(layerType, paint);
        }
    }

    @TargetApi(14)
    public static void setUiOptions(Window window, int uiOptions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            window.setUiOptions(uiOptions);
        }
    }

    /**
     * get Status Bar Height
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * get Action Bar Height
     *
     * @param appContext
     * @return
     */
    public static float getActionBarHeight(Context appContext) {
        TypedArray actionbarSizeTypedArray = appContext.obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        float height = actionbarSizeTypedArray.getDimension(0, 0);
        actionbarSizeTypedArray.recycle();
        return height;
    }

    /**
     * Get Activity profile label name
     * @param context
     * @param className
     * @return
     */
    public static String getActivityLabel(Context context, String className) {
        ActivityInfo aInfo = getActivityInfo(context, className);
        if (aInfo == null) {
            return null;
        } else {
            return aInfo.loadLabel(context.getPackageManager()).toString();
        }
    }

    /**
     * Get Activity Information
     * @param context
     * @param className
     * @return
     */
    public static ActivityInfo getActivityInfo(Context context, String className) {
        ComponentName cn = new ComponentName(context, className);
        try {
            return context.getPackageManager().getActivityInfo(cn, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /**
     * Get the current context where the process name
     * @param context
     * @return
     */
    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     *  Specify the context where the process determines whether to apply the main process
     * @param context
     * @return
     */
    public static boolean isAppMainProcess(Context context) {
        // Gets the current application process name
        String processName = getCurrentProcessName(context.getApplicationContext());
        String appProcessName = context.getApplicationInfo().processName;
        // If the primary process name and application name (application name from the primary process AndroidManifext.xml the
        // <appliction Android: process = "">
        // attribute definitions , default and PackageName package with the same name ) is the application of the same description
        // of the main process , not the same explanation is a separate application process (set up in AndroidManifext.xml the
        // android : process attributes Activity, Service, receiver, provider)
        return appProcessName.equals(processName);
    }
}
