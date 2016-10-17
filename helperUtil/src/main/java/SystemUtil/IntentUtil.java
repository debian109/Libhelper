package SystemUtil;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nam Tran on 17-Dec-15.
 */
public class IntentUtil {

    /**
     * Installation Apk
     *
     * @param apkFile
     * @return
     */
    public static Intent installApk(File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * Get information list Activity Intent response in line with the conditions of
     *
     * @param context
     * @param intent
     * @return
     */
    public static List<ResolveInfo> queryIntentActivities(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    }

    /**
     *Call dialing system
     *
     * @param phoneNumber
     * @param autoCall Whether to automatically start dialing
     * @return	If you do not support the call returns null ( general tablet does not support a call )
     */
    public static Intent phoneCall(Context context, String phoneNumber, boolean autoCall) {
        Intent intent;
        if (autoCall) {// Automatic Dialing
            intent = new Intent(Intent.ACTION_CALL);
        } else {// Open the dial-up interface does not automatically dial
            intent = new Intent(Intent.ACTION_DIAL);
        }
        intent.setData(Uri.parse("tel:" + phoneNumber));
        List<ResolveInfo> list = queryIntentActivities(context, intent);
        if(list.isEmpty()){
            return null;
        }
        return intent;
    }

    /**
     * sendSMS
     *
     * @param phoneNumber
     * @return
     */
    public static Intent sendSMS(Context context, String phoneNumber) {
        Intent intent = new Intent();
        // The default action, to open the default SMS interface
        intent.setAction(Intent.ACTION_SENDTO);
        // We need to send short interest numbers
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        List<ResolveInfo> list = queryIntentActivities(context, intent);
        if(list.isEmpty()){
            return null;
        }
        return intent;
    }

    /**
     *
     Open the pages of an application on Google Play
     *
     * @param packageName Application package names
     * @return
     */
    public static Intent googlePlay(String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /**
     * Share text<br>
     * You can select multiple applications： startActivity(Intent.createChooser(intent, "请选择"));<br>
     * You can also specify an application ： intent.setPackage(packageName);
     *
     * @param text
     * @param subject Themes ( some applications ignore )
     * @return
     */
    public static Intent shareText(String text, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * share pictures
     *
     * @param uri Local Photos，Uri.fromFile(file) Or acquired by ContentResolver
     * @return
     */
    public static Intent shareImage(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * Share pictures and text
     *
     * @param uri Local images , Uri.fromFile (file) , or obtained by ContentResolver
     * @param text At this time , some applications ignore text
     * @return
     */
    public static Intent shareImageAndText(Uri uri, String text, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * Sharing multiple pictures
     *
     * @param uris
     * @return
     */
    public static Intent shareMultipleImage(ArrayList<Uri> uris) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * Call System cameras
     *
     * @return
     */
    public static Intent camera() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    /**
     *	 Call System cameras
     *
     * @param file	Taking pictures saved file
     * @return
     */
    public static Intent camera(File file) {
        Intent intent = camera();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        return intent;
    }

    /**
     * Send broadcast notification system to add files to the media library , commonly used to make photos
     * appear after pictures in the Gallery
     *
     * @param file
     * @return
     */
    public static Intent addToAlbum(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        return intent;
    }
}
