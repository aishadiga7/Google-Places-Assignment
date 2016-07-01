package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 30/5/16.
 */
public class SouthWest {
    @SerializedName("lat")
    @Expose
    private double mLatittude;
    @SerializedName("lng")
    @Expose
    private double mLongitude;

    public double getLatittude() {
        return mLatittude;
    }

    public void setLatittude(double latittude) {
        mLatittude = latittude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }
}
