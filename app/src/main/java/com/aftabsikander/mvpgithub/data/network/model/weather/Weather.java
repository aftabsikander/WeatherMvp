package com.aftabsikander.mvpgithub.data.network.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public class Weather {

    @SerializedName("id")
    private long id;
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;
}
