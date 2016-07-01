package com.robosoftin.assignment.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aishwarya on 30/5/16.
 */
public class Routes implements Parcelable {
    @SerializedName("bounds")
    @Expose
    private Bounds mBounds;
    @SerializedName("copyrights")
    @Expose
    private String mCopyrights;
    @SerializedName("legs")
    @Expose
    private ArrayList<Legs> mLegs;
    @SerializedName("overview_polyline")
    @Expose
    private OverViewPolyLine mOverViewPolyLine;
    @SerializedName("summary")
    @Expose
    private String mSummary;
    @SerializedName("warnings")
    @Expose
    private ArrayList<Warnings> mWarnings;
    @SerializedName("waypoint_order")
    @Expose
    private ArrayList<WayPointOrder> mWayPointOrder;

    protected Routes(Parcel in) {
        mCopyrights = in.readString();
        mSummary = in.readString();
    }

    public static final Creator<Routes> CREATOR = new Creator<Routes>() {
        @Override
        public Routes createFromParcel(Parcel in) {
            return new Routes(in);
        }

        @Override
        public Routes[] newArray(int size) {
            return new Routes[size];
        }
    };

    public Bounds getBounds() {
        return mBounds;
    }

    public void setBounds(Bounds bounds) {
        mBounds = bounds;
    }

    public String getCopyrights() {
        return mCopyrights;
    }

    public void setCopyrights(String copyrights) {
        mCopyrights = copyrights;
    }

    public ArrayList<Legs> getLegs() {
        return mLegs;
    }

    public void setLegs(ArrayList<Legs> legs) {
        mLegs = legs;
    }

    public OverViewPolyLine getOverViewPolyLine() {
        return mOverViewPolyLine;
    }

    public void setOverViewPolyLine(OverViewPolyLine overViewPolyLine) {
        mOverViewPolyLine = overViewPolyLine;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public ArrayList<Warnings> getWarnings() {
        return mWarnings;
    }

    public void setWarnings(ArrayList<Warnings> warnings) {
        mWarnings = warnings;
    }

    public ArrayList<WayPointOrder> getWayPointOrder() {
        return mWayPointOrder;
    }

    public void setWayPointOrder(ArrayList<WayPointOrder> wayPointOrder) {
        mWayPointOrder = wayPointOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCopyrights);
        dest.writeString(mSummary);
    }
}
