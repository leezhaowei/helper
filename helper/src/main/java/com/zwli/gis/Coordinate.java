package com.zwli.gis;

public class Coordinate {

    public final double lon;
    public final double lat;

    public Coordinate(double _lon, double _lat) {
        this.lon = _lon;
        this.lat = _lat;
    }

    @Override
    public String toString() {
        return "Coordinate [lon=" + lon + ", lat=" + lat + "]";
    }

}
