package com.aftabsikander.mvpgithub.data.network.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public class Forecast {

    @SerializedName("dt")
    private long dt;

    @SerializedName("temp")
    private Temperature temp;

    @SerializedName("pressure")
    private float pressure;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("speed")
    private float speed;

    @SerializedName("deg")
    private int deg;

    @SerializedName("clouds")
    private int clouds;

    @SerializedName("rain")
    private float rain;

    public Forecast() {
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }
}
