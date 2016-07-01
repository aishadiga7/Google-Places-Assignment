package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aishwarya on 30/5/16.
 */
public class Duration {
    @SerializedName("text")
    @Expose
    private String mText;
    @SerializedName("value")
    @Expose
    private int mValue;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }
}
