package sodevan.com.forticon;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class NearBy extends AppCompatActivity {
double LAT,LON;
    int f=0;
    TextView tv2;
    Location startPoint = new Location("LocA");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);
        LocationManager lm =
                (LocationManager)
                        getSystemService(
                                LOCATION_SERVICE);

        LocationListener listener =
                new MyLocListnr();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0, 0, listener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);






        tv2= (TextView) findViewById(R.id.textView2);
    }

    private class MyLocListnr implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

                LAT = location.getLatitude();

            LON = location.getLongitude();
                setStartPoint(LAT,LON);

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    private void setStartPoint(double LAT,double LON) {

        startPoint.setLatitude(LAT);
        startPoint.setLongitude(LON);
        Location endPoint = new Location("LocA");
        endPoint.setLatitude(28.6975717);
        endPoint.setLongitude(77.1387377);
        Toast.makeText(this, "Location set", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "dist is : "+startPoint.distanceTo(endPoint), Toast.LENGTH_SHORT).show();
    }
}
