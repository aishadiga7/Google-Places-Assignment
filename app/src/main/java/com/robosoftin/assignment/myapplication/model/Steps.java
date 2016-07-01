package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 30/5/16.
 */
public class Steps {
    @SerializedName("distance")
    @Expose
    private Distance mDistance;
    @SerializedName("duration")
    @Expose
    private Duration mDuration;
    @SerializedName("end_location")
    @Expose
    private EndLocation mEndLocation;
    @SerializedName("html_instructions")
    @Expose
    private String mHtmlInstructions;
    @SerializedName("polyline")
    @Expose
    private Polyline mPolyline;
    @SerializedName("start_location")
    @Expose
    private StartLocation mStartLocation;
    @SerializedName("travel_mode")
    @Expose
    private String mTravedlMode;
    @SerializedName("maneuver")
    @Expose
    private String mManeuver;

    public Distance getDistance() {
        return mDistance;
    }

    public void setDistance(Distance distance) {
        mDistance = distance;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        mDuration = duration;
    }

    public EndLocation getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        mEndLocation = endLocation;
    }

    public String getHtmlInstructions() {
        return mHtmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        mHtmlInstructions = htmlInstructions;
    }

    public Polyline getPolyline() {
        return mPolyline;
    }

    public void setPolyline(Polyline polyline) {
        mPolyline = polyline;
    }

    public StartLocation getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        mStartLocation = startLocation;
    }

    public String getTravedlMode() {
        return mTravedlMode;
    }

    public void setTravedlMode(String travedlMode) {
        mTravedlMode = travedlMode;
    }

    public String getManeuver() {
        return mManeuver;
    }

    public void setManeuver(String maneuver) {
        mManeuver = maneuver;
    }
}
