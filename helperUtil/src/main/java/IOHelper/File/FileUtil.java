package IOHelper.File;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import UIHelper.TextAndEditText;

/**
 * Created by Nam Tran on 10-Dec-15.
 */
public class FileUtil {

    public static final File ROOTFILE = Environment.getExternalStorageDirectory();

    /**
     * get FileName
     * @param filePath
     * @return
     */
    public static String getFileName( String filePath )
    {
        if( TextAndEditText.isEmpty(filePath) )	return "";
        return filePath.substring( filePath.lastIndexOf( File.separator )+1 );
    }

    /**
     * get FileName No Format . Exp : .mp3
     * @param filePath
     * @return
     */
    public static String getFileNameNoFormat( String filePath){
        if(TextAndEditText.isEmpty(filePath)){
            return "";
        }
        int point = filePath.lastIndexOf('.');
        return filePath.substring(filePath.lastIndexOf(File.separator)+1,point);
    }

    /**
     * get FileFormat
     * @param fileName
     * @return
     */
    public static String getFileFormat( String fileName )
    {
        if( TextAndEditText.isEmpty(fileName) )	return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring( point+1 );
    }

    /**
     * get size of file
     * @param filePath
     * @return
     */
    public static long getFileSize( String filePath )
    {
        long size = 0;

        File file = new File( filePath );
        if(file!=null && file.exists())
        {
            size = file.length();
        }
        return size;
    }

    /**
     * get FileSize is String
     * @param size file
     * @return
     */
    public static String getFileSize(long size)
    {
        if (size <= 0)	return "0";
        java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
        float temp = (float)size / 1024;
        if (temp >= 1024)
        {
            return df.format(temp / 1024) + "MB";
        }
        else
        {
            return df.format(temp) + "KB";
        }
    }

    /**
     * format FileSize
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * get DirSize
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file);
            }
        }
        return dirSize;
    }

    /**
     * create Directory
     * @param directoryName
     * @return
     */
    public static boolean createDirectory(String directoryName) {
        boolean status;
        if (!directoryName.equals("")) {
            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + directoryName);
            status = newPath.mkdir();
            status = true;
        } else
            status = false;
        return status;
    }


    /**
     *  Check file from String filename is exist or not
     * @param context
     * @param filename
     * @return true if this file exists, false otherwise
     */
    public static boolean CheckFileExistance(Context context,String filename)
    {
        // Returns the absolute path on the filesystem where a file created with openFileOutput(String, int) is stored.
        File file = context.getFileStreamPath(filename);
        return file.exists();
    }

    /**
     * Check file have been delete from String path or not
     * Deletes this file. Directories must be empty before they will be deleted.
     * @param path
     * @return true if this file was deleted, false otherwise.
     */
    public static boolean CheckDeleteFile(String path)
    {
        File file = new File(path);
        if (file.exists())
        {
            return file.delete();
        }
        return false;
    }

    /**
     *  Check file folder has been deleted from File
     * @param dir
     * @return
     */
    public static boolean CheckDeleteDir(File dir)
    {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                boolean success = CheckDeleteDir(new File(dir,children[i]));
                if (!success)
                {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * Read file with String pathFile
     * @param pathFile
     * @return String textFile
     */
    public static String ReadTextFile(String pathFile)
    {
        StringBuffer result = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(pathFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i;
            char[] buffer = new char[512];
                while ((i = bufferedReader.read(buffer)) != -1)
                {
                    result.append(new String(buffer,0,i));
                }
                bufferedReader.close();
                fileReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        return result.toString();
    }

    /**
     * @param pathFile
     * @param text
     * @return true if Write File success , false if othewise
     */
    public static boolean WriteStringFile(String pathFile,String text)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathFile,true);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            text = text +"\n";
            bufferedOutputStream.write(text.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Copy File to internal memory
     * @param context
     * @param inputStream
     * @param name
     */
    public static void CopyFileToInternal(Context context, InputStream inputStream, String name)
    {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(name,Context.MODE_PRIVATE);
            byte[] buffer = new byte[512];
            int i;
            while ((i = inputStream.read(buffer))!= -1)
            {
                fileOutputStream.write(buffer,0,i);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  return all file with extension filter from Directory
     * @param dir
     * @param extensions
     * @return
     */
    public static ArrayList<File> getAllFileExtensionFilterFromDir(File dir,String[] extensions)
    {
        ArrayList<File> files = new ArrayList<File>();
        ExtensionsNameFilter extensionsNameFilter = new ExtensionsNameFilter(extensions);
        File[] file = dir.listFiles();
        for (File fileChild : file)
        {
            if (fileChild.isDirectory())
            {
                files.addAll(getAllFileExtensionFilterFromDir(fileChild,extensions));
            }
            else
                if (extensionsNameFilter.accept(fileChild,fileChild.getName()))
                {
                    files.add(fileChild);
                }
        }
        return files;
    }

    /**
     * use to create folder in your directory location in which you have to create folder name of the folder
     *
     * @param path
     * @param folderName
     */
    public static boolean createFolder( String path,
                                       String folderName) {
        File SDCardRoot = new File(path, folderName);
        if (!SDCardRoot.exists()) {
            return SDCardRoot.mkdir();
        }
        return false;
    }

    /**
     * get no of file counts from your directory of file extensions like .jpg, .png, .mp3, .mp4
     * @param extention
     * @param directoryPath location of the directory
     * @return give counter integer
     */
    public static int getFileCounts(String[] extention,
                                    String directoryPath) {
        int Sdcardcount = 0;
        ExtensionsNameFilter extensionsNameFilter = new ExtensionsNameFilter(extention);
        File fileCount = new File(directoryPath);
        if (fileCount.exists()) {
            File[] list = fileCount.listFiles();
            for (File f : list) {
               if (extensionsNameFilter.accept(new File(directoryPath),f.getName()))
                {
                    Sdcardcount++;
                }
            }
        }
        return Sdcardcount;
    }

    /**
     * use image from URL.
     *
     * @param imgurl     of the image.
     * @param mImageView in which you have to set image
     */
    public static void downloadImageFromURL(final String imgurl,
                                            final ImageView mImageView) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Bitmap bitmap = BitmapFactory
                            .decodeStream((InputStream) new URL(imgurl)
                                    .getContent());
                    mImageView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bitmap != null) {

                                mImageView.setImageBitmap(bitmap);
                            }

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * check Save Location Exists
     * @return
     */
    public static boolean checkSaveLocationExists() {
        String sDCardStatus = Environment.getExternalStorageState();
        boolean status;
        if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
            status = true;
        } else
            status = false;
        return status;
    }

    /**
     * get External Cache Dir
     * @param context
     * @return File
     */
    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    /**
     * save Image in file
     * @param context
     * @param fileName
     * @param bitmap
     * @param quality
     * @throws IOException
     */
    public static void saveImage(Context context, String fileName, Bitmap bitmap, int quality) throws IOException
    {
        if(bitmap==null || fileName==null || context==null)	return;

        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        byte[] bytes = stream.toByteArray();
        fos.write(bytes);
        fos.close();
    }

    /**
     * save Image to SD
     * @param filePath
     * @param bitmap
     * @param quality
     * @throws IOException
     */
    public static void saveImageToSD(String filePath, Bitmap bitmap, int quality) throws IOException
    {
        if(bitmap != null) {
            FileOutputStream fos = new FileOutputStream(filePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            byte[] bytes = stream.toByteArray();
            fos.write(bytes);
            fos.close();
        }
    }

    // -----------------------
    public static boolean isSDCardAvailable(Context mContext) {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * sent All File Extension Over Socket
     * @param files (all files example file mp3 , file image , file .... follow extension)
     * @param dataOutputStream
     */
    public static void sentAllFileExtensionOverSocket(ArrayList<File> files,DataOutputStream dataOutputStream)
    {
        try {
            dataOutputStream.writeInt(files.size());
            byte[] buffer = new byte[1024];
            int len = 0;
            for (int i = 0;i<files.size();i++)
            {
                Log.d("File",files.get(i).getName() + " / " + files.get(i).length());
                dataOutputStream.writeUTF(files.get(i).getName() + "@@" + files.get(i).length());
                dataOutputStream.flush();
                Log.d("File","Write file " + files.get(i).getName());
            }
            Log.d("File","Write name OK");

            for (int i = 0;i<files.size();i++)
            {
                Log.d("File","Begin write file over socket");
                FileInputStream fileInputStream = new FileInputStream(files.get(i));
                while ((len = fileInputStream.read(buffer)) != -1)
                {
                    Log.d("File","Readfile");
                    dataOutputStream.write(buffer, 0, len);
                    Log.d("File", "Readfile OK");
                }
                Log.d("File", "OK 1");
            }
            Log.d("File","Complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveAllFileExtensionOverSocket(File dirContain,DataInputStream dataInputStream)
    {
        if (!dirContain.exists())
        {
            dirContain.mkdirs();
        }
        try {
            int SizeAllFile = dataInputStream.readInt();
            Log.d("File", "Filesize" + SizeAllFile);
            ArrayList<String> listNameAndLenghtFile = new ArrayList<>();
            int n=0;
            byte[] buffer = new byte[1024];
            for (int i=0;i<SizeAllFile;i++)
            {
                String file = dataInputStream.readUTF();
                Log.d("File", "file" + file);
                listNameAndLenghtFile.add(file);
            }
            Log.d("File", "OK");
            if (SizeAllFile == listNameAndLenghtFile.size())
            {
                for (int i=0;i<SizeAllFile;i++)
                {
                    String file = listNameAndLenghtFile.get(i);
                    Log.d("File", "File " + file);
                    String[] fileNameAndLenht = file.split("@@");
                    String fileName = fileNameAndLenht[0];
                    Log.d("File", "File name" + fileName);
                    long fileLenght = Long.parseLong(fileNameAndLenht[1]);
                    Log.d("File", "File name" + fileName + " lenght " + fileLenght);
                    FileOutputStream fileOutputStream = new FileOutputStream(dirContain);
                    while (fileLenght>0 && (n = dataInputStream.read(buffer,0, (int) Math.min(buffer.length,fileLenght))) != -1)
                    {
                        Log.d("File", "File " + n + " byte");
                        fileOutputStream.write(buffer, 0, n);
                        fileLenght -= n;
                        Log.d("File", "File lenght" + fileLenght);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Log.d("File", "OK");
                }
                Log.d("File", "OK OK");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    ---------------------------------------------- Check Memory of divice ------------------------------------------------
     */

    private static final StatFs internal = new StatFs(Environment.getDataDirectory().getPath());
    private static final StatFs external = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

    /**
     * get Total Internal Storage
     * @return
     */
    public static long getTotalInternalStorage()
    {
        long blockSize;
        long total;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = internal.getBlockSizeLong();
                total = internal.getBlockCountLong() * blockSize;
        } else {
            blockSize = internal.getBlockSize();
            total = internal.getBlockCount() * blockSize;
        }
        return total;
    }

    /**
     * get Total Internal Storage
     * @return
     */
    public static long getAvailableInternalStorage()
    {
        long blockSize;
        long available;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = internal.getBlockSizeLong();
            available = internal.getAvailableBlocksLong() * blockSize;
        } else {
            blockSize = internal.getBlockSize();
            available = internal.getAvailableBlocks() * blockSize;
        }
        return available;
    }

    /**
     * get Total Internal Storage
     * @return
     */
    public static long getFreeSizeInternalStorage()
    {
        long blockSize;
        long free;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = internal.getBlockSizeLong();
            free = internal.getFreeBlocksLong() * blockSize;
        } else {
            blockSize = internal.getBlockSize();
            free = internal.getFreeBlocks() * blockSize;
        }
        return free;
    }

    /**
     * get Total External Storage
     * @return
     */
    public static long getTotalExternalStorage()
    {
        long blockSize;
        long total;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = external.getBlockSizeLong();
            total = external.getBlockCountLong() * blockSize;
        } else {
            blockSize = external.getBlockSize();
            total = external.getBlockCount() * blockSize;
        }
        return total;
    }

    /**
     * get Total Internal Storage
     * @return
     */
    public static long getAvailableExternalStorage()
    {
        long blockSize;
        long available;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = external.getBlockSizeLong();
            available = external.getAvailableBlocksLong() * blockSize;
        } else {
            blockSize = external.getBlockSize();
            available = external.getAvailableBlocks() * blockSize;
        }
        return available;
    }

    /**
     * get Total Internal Storage
     * @return
     */
    public static long getFreeSizeExternalStorage()
    {
        long blockSize;
        long free;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = external.getBlockSizeLong();
            free = external.getFreeBlocksLong() * blockSize;
        } else {
            blockSize = external.getBlockSize();
            free = external.getFreeBlocks() * blockSize;
        }
        return free;
    }

    /*
    -------------------------------------------------------------------------------------------------------------------
     */
}
