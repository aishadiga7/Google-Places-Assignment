package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aishwarya on 28/5/16.
 */
public class Result {
    @SerializedName("geometry")
    @Expose
    private Geometry mGeometry;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("opening_hours")
    @Expose
    public OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    public List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("scope")
    @Expose
    public String scope;
    @SerializedName("alt_ids")
    @Expose
    public List<AltId> altIds = new ArrayList<AltId>();
    @SerializedName("reference")
    @Expose
    public String reference;
    @SerializedName("types")
    @Expose
    public List<String> types = new ArrayList<String>();
    @SerializedName("vicinity")
    @Expose
    public String vicinity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Geometry getGeometry() {
        return mGeometry;
    }

    public void setGeometry(Geometry geometry) {
        mGeometry = geometry;
    }

}
