package com.robosoftin.assignment.myapplication.Utils;

import android.graphics.Color;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.robosoftin.assignment.myapplication.model.Legs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aishwarya on 1/6/16.
 */
public class MapUtility {


    public static final int ZOOM_LEVEL = 16;

    /**
     * Function that is used to reset markers
     * @param originMarkers  Arraylist that constitutes origin markers
     * @param destinationMarkers Arraylist that constitutes destination markers
     * @param polylinePaths
     */

    public static void  resetMarkers(ArrayList<Marker> originMarkers, ArrayList<Marker> destinationMarkers,
                                                                        ArrayList<Polyline> polylinePaths) {
        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }
        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }
        if (polylinePaths != null) {
            for (Polyline polyline: polylinePaths) {
                polyline.remove();
            }
        }
    }
    //used to draw directions

    public static void drawDirections(Legs legs, ArrayList<Marker> originMarkers, ArrayList<Marker> destinationMarkers,
                                      ArrayList<Polyline> polylinePaths, GoogleMap map, List<LatLng> points) {
        double startLocLat = legs.getStartLocation().getLat();
        double startLocLng = legs.getStartLocation().getLng();
        double endLocLat = legs.getEndLocation().getLat();
        double endLocLng = legs.getEndLocation().getLng();
        String startAddress = legs.getStartAddress();
        String endAddress = legs.getEndAddress();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(startLocLat, startLocLng), ZOOM_LEVEL));
        originMarkers.add(map.addMarker(new MarkerOptions()
                .title(startAddress)
                .position(new LatLng(startLocLat, startLocLng))));
        destinationMarkers.add(map.addMarker(new MarkerOptions()
                .title(endAddress)
                .position(new LatLng(endLocLat, endLocLng))));
        PolylineOptions polylineOptions = new PolylineOptions().
                geodesic(true).
                color(Color.BLUE).
                width(10);
        for (int i = 0; i < points.size(); i++)
            polylineOptions.add(points.get(i));
        polylinePaths.add(map.addPolyline(polylineOptions));
    }

}
