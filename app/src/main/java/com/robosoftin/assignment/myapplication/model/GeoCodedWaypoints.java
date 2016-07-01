package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 30/5/16.
 */
public class GeoCodedWaypoints {
    @SerializedName("geocoder_status")
    @Expose
    private String mGeoCoderStatus;
    @SerializedName("place_id")
    @Expose
    private String mPlaceId;

    public String getGeoCoderStatus() {
        return mGeoCoderStatus;
    }

    public void setGeoCoderStatus(String geoCoderStatus) {
        mGeoCoderStatus = geoCoderStatus;
    }

    public String getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(String placeId) {
        mPlaceId = placeId;
    }

}
