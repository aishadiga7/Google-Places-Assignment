package com.robosoftin.assignment.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 29/5/16.
 */
public class LatLong implements Parcelable {
    @SerializedName("lat")
    @Expose
    private double mLatitude;
    @SerializedName("lng")
    @Expose
    private double mLongitude;

    public LatLong() {

    }

    public LatLong(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    protected LatLong(Parcel in) {
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public static final Creator<LatLong> CREATOR = new Creator<LatLong>() {
        @Override
        public LatLong createFromParcel(Parcel in) {
            return new LatLong(in);
        }

        @Override
        public LatLong[] newArray(int size) {
            return new LatLong[size];
        }
    };

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mLatitude);
        dest.writeDouble(mLongitude);
    }
}
