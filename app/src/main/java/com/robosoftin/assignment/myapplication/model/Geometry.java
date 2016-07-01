package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 28/5/16.
 */
public class Geometry {
    @SerializedName("location")
    @Expose
    private LatLong mLocation;

    public LatLong getLocation() {
        return mLocation;
    }

    public void setLocation(LatLong location) {
        mLocation = location;
    }
}
