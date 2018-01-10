package com.aftabsikander.mvpgithub.data.network.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public class Coordinates {

    @SerializedName("lon")
    private float lon;

    @SerializedName("lat")
    float lat;

    public Coordinates() {
    }

    public Coordinates(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
