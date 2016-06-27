package Media;

import java.util.HashMap;
import java.util.Map;

import IOHelper.File.FileUtil;

/**
 * Created by Nam Tran on 17-Dec-15.
 */
public class MediaUtil {

    private static Map<String, String> FORMAT_TO_CONTENTTYPE = new HashMap<String, String>();

    static{
        //mp3
        FORMAT_TO_CONTENTTYPE.put( "mp3", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "mid", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "midi", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "asf", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "wm", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "wma", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "wmd", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "amr", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "wav", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "3gpp", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "mod", "audio" );
        FORMAT_TO_CONTENTTYPE.put( "mpc", "audio" );

        //video
        FORMAT_TO_CONTENTTYPE.put( "fla", "video" );
        FORMAT_TO_CONTENTTYPE.put( "flv", "video" );
        FORMAT_TO_CONTENTTYPE.put( "wav", "video" );
        FORMAT_TO_CONTENTTYPE.put( "wmv", "video" );
        FORMAT_TO_CONTENTTYPE.put( "avi", "video" );
        FORMAT_TO_CONTENTTYPE.put( "rm", "video" );
        FORMAT_TO_CONTENTTYPE.put( "rmvb", "video" );
        FORMAT_TO_CONTENTTYPE.put( "3gp", "video" );
        FORMAT_TO_CONTENTTYPE.put( "mp4", "video" );
        FORMAT_TO_CONTENTTYPE.put( "mov", "video" );

        //flash
        FORMAT_TO_CONTENTTYPE.put( "swf", "video" );

        FORMAT_TO_CONTENTTYPE.put( "null", "video" );

        //image
        FORMAT_TO_CONTENTTYPE.put( "jpg", "photo" );
        FORMAT_TO_CONTENTTYPE.put( "jpeg", "photo" );
        FORMAT_TO_CONTENTTYPE.put( "png", "photo" );
        FORMAT_TO_CONTENTTYPE.put( "bmp", "photo" );
        FORMAT_TO_CONTENTTYPE.put( "gif", "photo" );
    }

    /**
     * get ContentType
     * @param attFormat
     * @return
     */
    public static String getContentType( String attFormat ){
        String contentType = FORMAT_TO_CONTENTTYPE.get("null");

        if ( attFormat != null )
        {
            contentType = (String)FORMAT_TO_CONTENTTYPE.get( attFormat.toLowerCase() );
        }
        return contentType;
    }

    /**
     * get MIMEType
     * @param filePath
     * @return
     */
    public static String getMIMEType(String filePath){
        String type = "";
        String fName = FileUtil.getFileName(filePath);

        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();

        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg")
                || end.equals("wav"))
        {
            type = "audio";
        }
        else if (end.equals("3gp") || end.equals("mp4"))
        {
            type = "video";
        }
        else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp"))
        {
            type = "image";
        }
        else if(end.equals("doc") || end.equals("docx"))
        {
            type = "application/msword";
        }
        else if(end.equals("xls"))
        {
            type = "application/vnd.ms-excel";
        }
        else if(end.equals("ppt") || end.equals("pptx") || end.equals("pps") || end.equals("dps"))
        {
            type = "application/vnd.ms-powerpoint";
        }
        else
        {
            type = "*";
        }
        type += "/*";
        return type;
    }
}
