package com.aftabsikander.mvpgithub.data.network.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public class ForecastResult {

    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private ArrayList<Forecast> list;
}
