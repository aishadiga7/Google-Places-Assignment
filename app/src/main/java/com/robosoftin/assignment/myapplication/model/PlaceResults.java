package com.robosoftin.assignment.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aishwarya on 28/5/16.
 */
public class PlaceResults {
    @SerializedName("html_attributions")
    @Expose
    public List<Object> mHtmlAttributions = new ArrayList<Object>();
    @SerializedName("results")
    @Expose
    private ArrayList<Result> mResults = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("error_message")
    @Expose
    private String mErrorMessage;

    public ArrayList<Result> getResults() {
        return mResults;
    }

    public void setResults(ArrayList<Result> results) {
        mResults = results;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
