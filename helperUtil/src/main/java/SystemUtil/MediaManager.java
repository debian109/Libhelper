package SystemUtil;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nam Tran on 15-Jan-16.
 */
public class MediaManager {
    Context mContext;

    public MediaManager(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<File> getAllFileImageFromGalleries() {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        ArrayList<File> listFilesImage = new ArrayList<File>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        cursor = mContext.getContentResolver().query(uri, projection, null,
                null, null);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(absolutePathOfImage);
        }
        cursor.close();
        if (!listOfAllImages.isEmpty()) {
            for (int i = 0; i < listOfAllImages.size(); i++) {
                Log.d("IMAGESTRINGPATH", listOfAllImages.get(i));
                File file = new File(listOfAllImages.get(i));
                listFilesImage.add(file);
            }
        }
        return listFilesImage;
    }

    public ArrayList<File> getAllFileMusicFromGalleries() {
        ArrayList<String> musicPath = new ArrayList<String>();
        ArrayList<File> musicFile = new ArrayList<File>();
        ContentResolver cr = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;
        if (cur != null) {
            count = cur.getCount();
            if (count > 0) {
                while (cur.moveToNext()) {
                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    musicPath.add(data);
                }
            }
        }
        cur.close();
        if (!musicPath.isEmpty()) {
            for (int i = 0; i < musicPath.size(); i++) {
                Log.d("IMAGESTRINGPATH", musicPath.get(i));
                File file = new File(musicPath.get(i));
                musicFile.add(file);
            }
        }
        return musicFile;
    }

    public ArrayList<File> getAllFileVideoFromGalleries() {
        ArrayList<String> videoPath = new ArrayList<String>();
        ArrayList<File> videoFile = new ArrayList<File>();
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] proj = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE};
        Cursor cursor = contentResolver.query(uri, proj, null, null, null);
        int count = 0;
        if (cursor != null) {
            count = cursor.getCount();
            if (count > 0) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                    videoPath.add(data);
                }
            }
        }
        cursor.close();
        if (!videoPath.isEmpty()) {
            for (int i = 0; i < videoPath.size(); i++) {
                Log.d("IMAGESTRINGPATH", videoPath.get(i));
                File file = new File(videoPath.get(i));
                videoFile.add(file);
            }
        }
        return videoFile;
    }
}
