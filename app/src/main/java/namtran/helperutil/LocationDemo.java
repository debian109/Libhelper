package namtran.helperutil;

import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

import Map.LocationHelper;
import SharedPreferences.Preferences;
import SystemUtil.SystemHelper;
import namtran.helperutil.BasicActivity.BaseActivity;

public class LocationDemo extends BaseActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    LocationHelper locationHelper;
    TextView mlocation,preference;

    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.location_layout);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlocation = (TextView) findViewById(R.id.location);
        preference = (TextView) findViewById(R.id.preference);
        locationHelper = LocationHelper.getInstance(this);
        }

    @Override
    protected void onResume() {
            if (!locationHelper.isLocationEnabled()) {
                SystemHelper.showLocationDisabledAlert("Check Location",this);
            } else {
                locationHelper.startUpdate(new LocationListener() {
                    @Override public void onLocationChanged(Location location) {
                        Preferences.setDoublePrefrences(LocationDemo.this, "lat", location.getLatitude(), "Location");
                        Preferences.setDoublePrefrences(LocationDemo.this,"lng",location.getLongitude(),"Location");
                        makeUseOfNewLocation(location);
                    }
                });
            }
        super.onResume();
    }

    @Override protected void onStop() {
        locationHelper.stopUpdate();
        super.onStop();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }

    private void makeUseOfNewLocation(final Location location) {
        if (location != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    mlocation.setText("Found location updated : " +  location.getLatitude() + "/" + location.getLongitude());
                    Log.d("Location","Found location updated : " +  location.getLatitude() + "/" + location.getLongitude());
                }
            });
        }
    }
}
