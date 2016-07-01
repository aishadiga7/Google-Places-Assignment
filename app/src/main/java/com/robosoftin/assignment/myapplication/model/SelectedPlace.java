package com.robosoftin.assignment.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aishwarya on 28/5/16.
 */
public class SelectedPlace implements Parcelable {

    private String mName;
    private double nLatitude;
    private double mLongitude;

    public SelectedPlace(String name, double nLatitude, double longitude) {
        mName = name;
        this.nLatitude = nLatitude;
        mLongitude = longitude;
    }

    protected SelectedPlace(Parcel in) {
        mName = in.readString();
        nLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public static final Creator<SelectedPlace> CREATOR = new Creator<SelectedPlace>() {
        @Override
        public SelectedPlace createFromParcel(Parcel in) {
            return new SelectedPlace(in);
        }

        @Override
        public SelectedPlace[] newArray(int size) {
            return new SelectedPlace[size];
        }
    };

    public double getLatitude() {
        return nLatitude;
    }

    public void setLatitude(double nLatitude) {
        this.nLatitude = nLatitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
        dest.writeString(mName);
        dest.writeDouble(nLatitude);
        dest.writeDouble(mLongitude);
    }
}
