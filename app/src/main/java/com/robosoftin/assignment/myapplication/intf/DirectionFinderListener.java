package com.robosoftin.assignment.myapplication.intf;

import com.robosoftin.assignment.myapplication.model.Routes;

import java.util.ArrayList;

/**
 * Created by aishwarya on 30/5/16.
 */
public interface DirectionFinderListener {

    void onDirectionFinderStart();

    void onDirectionFinderSuccess(ArrayList<Routes> routes);

    void onDirectionFinderFailure();

}
