package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aishwarya on 30/5/16.
 */
public class DirectionsResult {
    @SerializedName("geocoded_waypoints")
    @Expose
    private ArrayList<GeoCodedWaypoints> mGeoCodedWaypoints;
    @SerializedName("routes")
    @Expose
    private ArrayList<Routes> mRoutes;
    @SerializedName("status")
    @Expose
    private String mStatus;

    public ArrayList<GeoCodedWaypoints> getGeoCodedWaypoints() {
        return mGeoCodedWaypoints;
    }

    public void setGeoCodedWaypoints(ArrayList<GeoCodedWaypoints> geoCodedWaypoints) {
        mGeoCodedWaypoints = geoCodedWaypoints;
    }

    public ArrayList<Routes> getRoutes() {
        return mRoutes;
    }

    public void setRoutes(ArrayList<Routes> routes) {
        mRoutes = routes;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
