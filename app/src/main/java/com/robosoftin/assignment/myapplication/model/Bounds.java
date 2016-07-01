package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 30/5/16.
 */
public class Bounds {
    @SerializedName("northeast")
    @Expose
    private NorthEast mNorthEast;
    @SerializedName("southwest")
    @Expose
    private SouthWest mSouthWest;

    public NorthEast getNorthEast() {
        return mNorthEast;
    }

    public void setNorthEast(NorthEast northEast) {
        mNorthEast = northEast;
    }

    public SouthWest getSouthWest() {
        return mSouthWest;
    }

    public void setSouthWest(SouthWest southWest) {
        mSouthWest = southWest;
    }
}
