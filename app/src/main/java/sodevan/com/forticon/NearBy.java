package sodevan.com.forticon;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NearBy extends AppCompatActivity {
double LAT,LON;
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
                lm.GPS_PROVIDER,
                0, 0, listener);

        Location startPoint = new Location("LocA");
        //startPoint.setLatitude();
        //startPoint.setLongitude();

    }

    private class MyLocListnr implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if(location.getLatitude()!=0&&location.getLongitude()!=0){
            LAT = location.getLatitude();

            LON = location.getLongitude();

                Toast.makeText(NearBy.this, "lat :"+LAT+" , lon : "+LON, Toast.LENGTH_SHORT).show();}
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
}
