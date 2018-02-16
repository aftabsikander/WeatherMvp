package com.aftabsikander.mvpgithub.data.network.model.api;

/**
 * Created by aftabsikander on 2/8/2018.
 */

public class APIError {

    private int statusCode;
    private String message;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
