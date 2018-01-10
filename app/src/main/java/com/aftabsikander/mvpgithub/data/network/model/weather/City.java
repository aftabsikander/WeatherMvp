package com.aftabsikander.mvpgithub.data.network.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aftabsikander on 1/10/2018.
 */

public class City {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("coord")
    private Coordinates coordinates;
    @SerializedName("country")
    private String country;
    @SerializedName("population")
    private int population;

    public City() {
    }

    public City(Long id, String name, Coordinates coordinates, String country, int population) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.country = country;
        this.population = population;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
