package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 28/5/16.
 */
public class AltId {
    @SerializedName("place_id")
    @Expose
    public String mPlaceId;
    @SerializedName("scope")
    @Expose
    public String mScope;
}
