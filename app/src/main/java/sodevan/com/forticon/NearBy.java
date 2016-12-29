package sodevan.com.forticon;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sodevan.com.forticon.Models.Venues;

public class NearBy extends AppCompatActivity {
double LAT,LON;
    int f=0;
    Location startPoint = new Location("LocA");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Venues");
    ArrayList<String> vens=new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
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

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                vens );
        ListView lv;
        lv= (ListView) findViewById(R.id.lv);
        lv.setAdapter(arrayAdapter);




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
        Log.d("current", "lat = "+LAT+"current long :"+LON);
        Location endPoint = new Location("LocA");
        endPoint.setLatitude(28.6978765);
        endPoint.setLongitude(77.1420569);
        Toast.makeText(this, "Location set", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "dist is : "+startPoint.distanceTo(endPoint), Toast.LENGTH_SHORT).show();
        findnearby(startPoint);
    }

    private void findnearby(final Location startPoint) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vens.clear();
                for (DataSnapshot dsp : dataSnapshot.getChildren()){
                    String name=dsp.child("name").getValue().toString();
                    String id=dsp.child("id").getValue().toString();
                    double latit= Double.parseDouble(dsp.child("lat").getValue().toString()) ;
                    double longi= Double.parseDouble(dsp.child("long").getValue().toString());
                    Location endPoint =new Location("LocA");
                    endPoint.setLongitude(longi);
                    endPoint.setLatitude(latit);
                    double dist=startPoint.distanceTo(endPoint);
                    Log.d("loc :",String.valueOf(dist));
                    if (dist<1000){
                        vens.add(name);
                    }
                    //Venues venues= new Venues(id,latit,longi,name);
                    //vens.add(venues);
                    Log.d("name",dsp.child("name").getValue().toString());

                }
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
