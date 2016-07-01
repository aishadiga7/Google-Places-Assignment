package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aishwarya on 30/5/16.
 */
public class Legs {
    @SerializedName("distance")
    @Expose
    private Distance mDistance;
    @SerializedName("duration")
    @Expose
    private Duration mDuration;
    @SerializedName("end_address")
    @Expose
    private String mEndAddress;
    @SerializedName("end_location")
    @Expose
    private EndLocation mEndLocation;
    @SerializedName("start_address")
    @Expose
    private String mStartAddress;
    @SerializedName("start_location")
    @Expose
    private StartLocation mStartLocation;
    @SerializedName("steps")
    @Expose
    private ArrayList<Steps> mSteps;
    @SerializedName("traffic_speed_entry")
    @Expose
    private ArrayList<TrafficSpeedEntry> mTrafficSpeedEntry;
    @SerializedName("via_waypoint")
    @Expose
    private ArrayList<ViaWayPoint> mViaWayPoint;

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

    public String getEndAddress() {
        return mEndAddress;
    }

    public void setEndAddress(String endAddress) {
        mEndAddress = endAddress;
    }

    public EndLocation getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        mEndLocation = endLocation;
    }

    public String getStartAddress() {
        return mStartAddress;
    }

    public void setStartAddress(String startAddress) {
        mStartAddress = startAddress;
    }

    public StartLocation getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        mStartLocation = startLocation;
    }

    public ArrayList<Steps> getSteps() {
        return mSteps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        mSteps = steps;
    }

    public ArrayList<TrafficSpeedEntry> getTrafficSpeedEntry() {
        return mTrafficSpeedEntry;
    }

    public void setTrafficSpeedEntry(ArrayList<TrafficSpeedEntry> trafficSpeedEntry) {
        mTrafficSpeedEntry = trafficSpeedEntry;
    }

    public ArrayList<ViaWayPoint> getViaWayPoint() {
        return mViaWayPoint;
    }

    public void setViaWayPoint(ArrayList<ViaWayPoint> viaWayPoint) {
        mViaWayPoint = viaWayPoint;
    }
}
