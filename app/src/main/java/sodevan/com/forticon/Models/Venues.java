package sodevan.com.forticon.Models;

/**
 * Created by ronaksakhuja on 29/12/16.
 */

public class Venues {
    String id;
    double Lat,Long;
    String name;

    public Venues(String id, double lat, double aLong, String name) {
        this.id = id;
        Lat = lat;
        Long = aLong;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return Lat;
    }

    public double getLong() {
        return Long;
    }

    public String getName() {
        return name;
    }
}
