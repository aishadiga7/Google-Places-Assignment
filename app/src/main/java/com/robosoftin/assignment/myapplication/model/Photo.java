package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aishwarya on 28/5/16.
 */
public class Photo {
    @SerializedName("height")
    @Expose
    public Integer mHeight;
    @SerializedName("html_attributions")
    @Expose
    public List<Object> mHtmlAttributions = new ArrayList<Object>();
    @SerializedName("photo_reference")
    @Expose
    public String mPhotoReference;
    @SerializedName("width")
    @Expose
    public Integer mWidth;
}
