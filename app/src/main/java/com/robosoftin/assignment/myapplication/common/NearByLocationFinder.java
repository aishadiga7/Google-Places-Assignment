package com.robosoftin.assignment.myapplication.common;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.robosoftin.assignment.myapplication.Utils.DownloadUtility;
import com.robosoftin.assignment.myapplication.intf.LocationFinderListener;
import com.robosoftin.assignment.myapplication.model.PlaceResults;
import com.robosoftin.assignment.myapplication.model.Result;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aishwarya on 1/6/16.
 */
public class NearByLocationFinder {

    private static final String TAG = NearByLocationFinder.class.getSimpleName();
    private LocationFinderListener mListener;


    public NearByLocationFinder(LocationFinderListener listener) {
        this.mListener = listener;
    }

    public void execute(Location currentLocation, String selectedCategory) {
        mListener.onLocationFindStart();
        new DownloadRawData().execute(constructUrl(currentLocation, selectedCategory));
    }


    private String constructUrl(Location currentLocation, String selectedCategory) {
        String url =  AppConstants.TEXT_SEARCH_API + "location=" + currentLocation.getLatitude() +
                "," +
                "" + currentLocation.getLongitude() + "&radius=500" + "&key=" + AppConstants.SERVER_KEY + "&type=" + selectedCategory;
        Log.d(TAG, "Location Url:" +url);
        return url;
    }


    private class DownloadRawData extends AsyncTask<String, Void, ArrayList<Result>> {
        @Override
        protected ArrayList<Result> doInBackground(String... params) {
            try {
                String url = DownloadUtility.downloadUrl(params[0]);
                Gson gson = new Gson();
                PlaceResults placeResults = gson.fromJson(url, PlaceResults.class);
                Log.d(TAG, "placeResults:" + placeResults);
                ArrayList<Result> results = placeResults.getResults();
                if (placeResults.getStatus().equalsIgnoreCase("Ok") && placeResults
                        .getResults().size() > 0) {
                    Log.d(TAG, "Place greater than 0");
                    return results;

                } else {
                    return null;
                }

            } catch (IOException e) {
                return null;
            }catch (JsonSyntaxException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Result> placeResult) {
            Log.d(TAG, "Inside onPostExecute:");
            if (placeResult != null) {
                mListener.onLocationFindSuccess(placeResult);
            } else {
                mListener.onLocationFindFailure();
            }
        }
    }

}
