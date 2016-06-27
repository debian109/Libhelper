package Map;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Nam Tran on 27-Dec-15.
 */
public class LocationHelper {

    protected static final String TAG = "NamTran Location";

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000 * 60 * 5 ;

    /**
     * TSet the minimum displacement between location updates in meters
     */
    public static final float UPDATE_DISPLACEMENT_METER = 5;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // Keys for storing activity state in the Bundle.
    protected final static String LOCATION_KEY = "location-key";

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient googleApiClient;

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    protected LocationRequest locationRequest;

    private Context context;
    private static LocationHelper mInstance;
    private LocationListener locationListener;
    private Location location;

    private GoogleApiClient.ConnectionCallbacks connectionCallback = new GoogleApiClient.ConnectionCallbacks() {
        @Override public void onConnected(Bundle bundle) {
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            startLocationUpdates();
        }

        @Override public void onConnectionSuspended(int cause) {
            // The connection to Google Play services was lost for some reason. We call connect() to
            // attempt to re-establish the connection.
            Log.i(TAG, "Connection suspended");
            googleApiClient.connect();
        }
    };

    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override public void onConnectionFailed(ConnectionResult connectionResult) {
            // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
            // onConnectionFailed.
            Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
        }
    };

    public Location getLocationCurrent()
    {
        if (googleApiClient.isConnected())
        {
            return location;
        }
        return null;
    }

    public LocationHelper(Context context) {
        this.context = context;
        // Kick off the process of building a GoogleApiClient and requesting the LocationServices
        // API.
        buildGoogleApiClient();

        // Create default location request.
        createLocationRequest(UPDATE_INTERVAL_IN_MILLISECONDS, FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS, LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY,UPDATE_DISPLACEMENT_METER);
    }

    public static synchronized LocationHelper getInstance(Context context)
    {
        if (null == mInstance)
            mInstance = new LocationHelper(context);
        return mInstance;
    }

    public void startUpdate(LocationListener locationListener) {
        this.locationListener = locationListener;
        if (googleApiClient.isConnected()) {
            startLocationUpdates();
        } else {
            googleApiClient.connect();
        }
    }

    public void stopUpdate() {
        stopLocationUpdates();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    public boolean isLocationEnabled() {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }


    /**
     * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the
     * LocationServices API.
     */
    private synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(connectionCallback)
                .addOnConnectionFailedListener(connectionFailedListener)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    public void config(long interval, long fastestInterval, int priority,float displacement) {
        createLocationRequest(interval, fastestInterval, priority, displacement);
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    private void createLocationRequest(long interval, long fastestInterval, int priority,float displacement) {
        locationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        locationRequest.setInterval(interval);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        locationRequest.setFastestInterval(fastestInterval);

        locationRequest.setPriority(priority);

        //locationRequest.setSmallestDisplacement(displacement);
    }

    /**
     * Requests location updates from the FusedLocationApi.
     */
    private void startLocationUpdates() {
        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
    }

    /**
     * Removes location updates from the FusedLocationApi.
     */
    private void stopLocationUpdates() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.

        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
        }
    }
}
