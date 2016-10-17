package IOHelper;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 */
public class URLUtil {

    /**
     * url decoding
     * @param schemeUrl url
     * @return Coding url
     */
    public static String decodeURL(String schemeUrl) {
        try {
            return URLDecoder.decode(schemeUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return schemeUrl;
    }

    /**
     * url coding
     * @param schemeUrl url
     * @return Coding url
     */
    public static String encodeURL(String schemeUrl) {
        try {
            return URLEncoder.encode(schemeUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ;
        return schemeUrl;
    }

    /**
     * Returns arguments get request url address
     * @param url url
     * @param params parameter
     * @return Get url address requests with parameters
     */
    public static String getURLWithParams(String url,Map<String, String> params){
        return url+"?"+joinParam(params);
    }

    /**
     * Connection parameters
     * @param params parameter
     * @return Connection Results
     */
    private static StringBuffer joinParam(Map<String, String> params) {
        StringBuffer result = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            String key = param.getKey();
            String value = param.getValue();
            result.append(key).append('=').append(value);
            if (iterator.hasNext()) {
                result.append('&');
            }
        }
        return result;
    }

    /**
     * Convert a string to base64
     *
     * @param s
     * @return base64 string
     */
    public static String encodeBase64(String s) {
        byte[] encodedBytes = Base64.encode(s.getBytes(), Base64.DEFAULT);
        return new String(encodedBytes);
    }

    /**
     * Convert a base64 string to string
     *
     * @param base64
     * @return base64 decoded string
     */
    public static String decodeBase64(String base64) {
        byte[] decodedBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);
        return new String(decodedBytes);
    }

    /**
     * URL encoding of query parameters of a URL
     *
     * @param parameters
     * @return encoded URL
     */
    @SuppressWarnings("deprecation")
    public static String encodeUrl(Bundle parameters) {
        if (parameters == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : parameters.keySet()) {
            if (first)
                first = false;
            else
                sb.append("&");
            sb.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(parameters.getString(key)));
        }
        return sb.toString();
    }

    /**
     * URL decoding of query parameters of a URL
     *
     * @param s URL to be decoded
     * @return Map of parameter and values
     */
    @SuppressWarnings("deprecation")
    public static Map<String, String> decodeUrl(String s) {
        Map<String, String> params = new HashMap<String, String>();
        if (s != null) {
            String array[] = s.split("&");
            for (String parameter : array) {
                String v[] = parameter.split("=");
                if (v.length > 1) {
                    params.put(URLDecoder.decode(v[0]), v.length > 1 ? URLDecoder.decode(v[1]) : null);
                }
            }
        }
        return params;
    }

    /**
     * Function to encrypt password
     *
     * @param password
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public static String encryptPassword(String password, String secretKey) {
        String base64EncryptedString = "";
        try {
            // hash secret key using MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-16le"));

            // hash password using TripleDes algorithm
            SecretKey key = new SecretKeySpec(digestOfPassword, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = password.getBytes("utf-16le");
            byte[] buf = cipher.doFinal(plainTextBytes);
            base64EncryptedString = Base64.encodeToString(buf, Base64.NO_WRAP);
        } catch (Exception e) {
            Log.e("encryptPassword", e.getMessage());
        }
        return base64EncryptedString;
    }

    /**
     * Md5 checksum value string
     *
     * @param str Target string
     * @param md5 Reference md5
     * @return Check results
     */
    public static boolean checkMD5(String str, String md5) {
        return getMD5String(str).equalsIgnoreCase(md5);
    }

    /**
     * Md5 checksum value of the file
     *
     * @param file Target file
     * @param md5  Reference md5
     * @return Check results
     */
    public static boolean checkFileMD5(File file, String md5) {
        return getFileMD5String(file).equalsIgnoreCase(md5);
    }

    /**
     * Get files md5 value string uppercase
     *
     * @param file File Object
     * @return File md5 string uppercase
     */
    public static String getFileMD5UpperString(File file) {
        return getFileMD5String(file).toUpperCase();
    }

    /**
     * File md5 string uppercase
     *
     * @param file File Object
     * @return Md5 file
     */
    public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            ret = HexUtil.bytes2Hex(md5.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * Get md5 capitalized value of the string
     *
     * @param str Be encrypted string
     * @return md5 encrypted string uppercase
     */
    public static String getMD5UpperString(String str) {
        return getMD5String(str).toUpperCase();
    }

    /**
     * Get md5 value of the string
     *
     * @param str Be encrypted string
     * @return md5 encrypted string
     */
    public static String getMD5String(String str) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HexUtil.bytes2Hex(bytes);

    }
}
