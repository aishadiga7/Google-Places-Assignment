package com.robosoftin.assignment.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.Utils.DialogUtils;
import com.robosoftin.assignment.myapplication.common.BaseFragment;
import com.robosoftin.assignment.myapplication.common.Constants;
import com.robosoftin.assignment.myapplication.model.LatLong;

/**
 * Created by aishwarya on 28/5/16.
 */
public class DirectionsFragment extends BaseFragment implements  View.OnClickListener {

    private static final String TAG = DirectionsFragment.class.getSimpleName();
    private LatLong mSource = new LatLong();
    private LatLong mDest = new LatLong();
    private TextView mDistance;
    private boolean mIsSourceSelected = false;
    private boolean mIsDestSelected = false;
    private PlaceAutocompleteFragment mSourceLoc;
    private PlaceAutocompleteFragment mDestLoc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_directions, null);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        mSourceLoc = (PlaceAutocompleteFragment) getActivity()
                                                .getFragmentManager().findFragmentById(R.id.place_autocomplete_source);
        mDestLoc = (PlaceAutocompleteFragment)getActivity().getFragmentManager()
                                                 .findFragmentById(R.id.place_autocomplete_dest);
        Button showMap = (Button) view.findViewById(R.id.show_map);
        showMap.setOnClickListener(this);
        mDistance = (TextView) view.findViewById(R.id.distance);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder().setTypeFilter
                                                            (AutocompleteFilter.TYPE_FILTER_ADDRESS).build();
        Log.d(TAG, "Source:" +mSourceLoc);
        Log.d(TAG, "Dest:" +mDestLoc);
        setFilters(mSourceLoc, mDestLoc, typeFilter);
        setPlaceSelectedListenerForSource(mSourceLoc);
        setPlaceSelectedListenerForDest(mDestLoc);
    }

    private void setPlaceSelectedListenerForSource(PlaceAutocompleteFragment sourceLoc) {
        sourceLoc.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mIsSourceSelected = true;
                mSource.setLatitude(place.getLatLng().latitude);
                mSource.setLongitude(place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {
                mIsSourceSelected = false;
                Log.d(TAG, "onError" + status);
                DialogUtils.showSnackBar(getActivity().getWindow().getDecorView().findViewById(android.R.id
                        .content), status.getStatusMessage());
            }
        });
    }

    private void setPlaceSelectedListenerForDest(PlaceAutocompleteFragment destLoc) {
        destLoc.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mIsDestSelected = true;
                Log.d(TAG, "Dest Place:" + place.getName());
                Log.d(TAG, "Dest LatLong:" + place.getLatLng().toString());
                mDest.setLatitude(place.getLatLng().latitude);
                mDest.setLongitude(place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {
                mIsDestSelected = false;
                Log.d(TAG, "onError" + status);
                DialogUtils.showSnackBar(getActivity().getWindow().getDecorView().findViewById(android.R.id
                        .content), status.getStatusMessage());
            }
        });
    }

    private void setFilters(PlaceAutocompleteFragment sourceLoc, PlaceAutocompleteFragment destLoc, AutocompleteFilter typeFilter) {
        sourceLoc.setFilter(typeFilter);
        destLoc.setFilter(typeFilter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_map:
                    if (mIsDestSelected && mIsSourceSelected) {
                        mSourceLoc.setText("");
                        mDestLoc.setText("");
                        Intent individualPlaceShowIntent = new Intent(getActivity(), IndividualPlaceShowActivity.class);
                        individualPlaceShowIntent.putExtra(Constants.SOURCE, mSource);
                        individualPlaceShowIntent.putExtra(Constants.DEST, mDest);
                        startActivity(individualPlaceShowIntent);
                } else {
                    DialogUtils.showSnackBar(getActivity().getWindow().getDecorView().findViewById(android.R
                            .id.content), getActivity().getString(R.string
                            .please_select_the_required_locations));
                }
                break;
        }
    }
}
