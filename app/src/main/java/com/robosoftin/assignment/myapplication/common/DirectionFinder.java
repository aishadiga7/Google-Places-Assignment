package com.robosoftin.assignment.myapplication.common;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.robosoftin.assignment.myapplication.Utils.DownloadUtility;
import com.robosoftin.assignment.myapplication.intf.DirectionFinderListener;
import com.robosoftin.assignment.myapplication.model.DirectionsResult;
import com.robosoftin.assignment.myapplication.model.LatLong;
import com.robosoftin.assignment.myapplication.model.Routes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aishwarya on 31/5/16.
 */
public class DirectionFinder {

    private static final String TAG = DirectionFinder.class.getSimpleName();
    private DirectionFinderListener mListener;
    private LatLong mOrigin;
    private LatLong mDestination;

    public DirectionFinder(DirectionFinderListener listener, LatLong origin, LatLong destination) {
        this.mListener = listener;
        this.mOrigin = origin;
        this.mDestination = destination;
    }

    public void execute() {
        mListener.onDirectionFinderStart();
        new DownloadRawData().execute(createUrl());
    }

    private String createUrl() {
        String url = AppConstants.DIRECTIONS_SEARCH_API + "origin=" + mOrigin.getLatitude()
                + "," + mOrigin.getLongitude() + "&destination=" + mDestination.getLatitude() + "," +
                "" + mDestination.getLongitude() + "&key=" + AppConstants.SERVER_KEY;
        Log.d(TAG, "Directions URL:" +url);
       return url;
    }

    private class DownloadRawData extends AsyncTask<String, Void, ArrayList<Routes>> {
        @Override
        protected ArrayList<Routes> doInBackground(String... params) {
             ArrayList<Routes> route = null;
            try {
                String url = DownloadUtility.downloadUrl(params[0]);
                if (!TextUtils.isEmpty(url)) {
                    Gson gson = new Gson();
                    DirectionsResult directionResults = gson.fromJson(url, DirectionsResult.class);
                    Log.d(TAG, "DirectionsResult:" + directionResults);
                    ArrayList<Routes> routes = directionResults.getRoutes();
                    if (directionResults.getStatus().equalsIgnoreCase("Ok") && directionResults
                            .getRoutes().size() > 0) {
                        route =  routes;
                    } else {
                        route =  null;
                    }
                }
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
                route =  null;
            }
            return route;
        }

        @Override
        protected void onPostExecute(ArrayList<Routes> routes) {
            Log.d(TAG, "Inside onPostExecute:");
            if (routes != null) {
                mListener.onDirectionFinderSuccess(routes);
            } else {
                mListener.onDirectionFinderFailure();
            }

        }
    }



}
