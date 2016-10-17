package JavaHelper;

import android.location.Location;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nam Tran on 13-Dec-15.
 */
public class Convert {

    /**
     * Convert String[] to Arraylist<String>
     * @param a
     * @return
     */
    public static ArrayList<String> convertArrayStringToArrayList (String[] a)
    {
        ArrayList<String> arrayString = new ArrayList<String>(Arrays.asList(a));
        return arrayString;
    }

    /**
     * Convert String[] to List<String>
     * @param a
     * @return
     */
    public static List<String> convertArrayStringToList (String[] a)
    {
        List<String> arrayString = new ArrayList<String>(Arrays.asList(a));
        return arrayString;
    }

    /**
     * Get string content from input stream
     * @param is
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        IOException exception = null;

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            exception = e;
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                exception = e;
                e.printStackTrace();
            }
        }

        if (null != exception)
            throw exception;

        return sb.toString();
    }

    /*
        Med float.
     */
    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    public static double distFromDouble(double latitude1, double longitude1, double latitude2, double longitude2) {
        float distance = distFrom((float)latitude1, (float)longitude1, (float)latitude2, (float)longitude2  );

        Log.d("jj", "distance: "+distance);

        return (double) distance;
    }

    static long getDistanceBetweenPoints(Location location1, Location location2){

        double lat1 = location1.getLatitude();
        double lng1 = location1.getLongitude();
        double lat2 = location2.getLatitude();
        double lng2 = location2.getLongitude();
        return (long) distFromDouble(lat1, lng1, lat2, lng2);
    }
}
