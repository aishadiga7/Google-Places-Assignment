package com.robosoftin.assignment.myapplication.intf;

import com.robosoftin.assignment.myapplication.model.Result;

import java.util.ArrayList;

/**
 * Created by aishwarya on 1/6/16.
 */
public interface LocationFinderListener {

    void onLocationFindStart();

    void onLocationFindSuccess(ArrayList<Result> locationResult);

    void onLocationFindFailure();

}
