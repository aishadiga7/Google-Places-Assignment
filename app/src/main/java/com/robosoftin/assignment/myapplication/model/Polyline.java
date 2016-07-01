package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 30/5/16.
 */
public class Polyline {
    @SerializedName("points")
    @Expose
    private String mPoints;

    public String getPoints() {
        return mPoints;
    }

    public void setPoints(String points) {
        mPoints = points;
    }
}
