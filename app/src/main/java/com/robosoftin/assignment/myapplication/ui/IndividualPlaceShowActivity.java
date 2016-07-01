package com.robosoftin.assignment.myapplication.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.Utils.DecodeUtility;
import com.robosoftin.assignment.myapplication.Utils.DialogUtils;
import com.robosoftin.assignment.myapplication.Utils.MapUtility;
import com.robosoftin.assignment.myapplication.Utils.NetworkCheckUtility;
import com.robosoftin.assignment.myapplication.common.BaseActivity;
import com.robosoftin.assignment.myapplication.common.Constants;
import com.robosoftin.assignment.myapplication.common.DirectionFinder;
import com.robosoftin.assignment.myapplication.common.LocationTracker;
import com.robosoftin.assignment.myapplication.intf.DirectionFinderListener;
import com.robosoftin.assignment.myapplication.model.LatLong;
import com.robosoftin.assignment.myapplication.model.Legs;
import com.robosoftin.assignment.myapplication.model.Routes;

import java.util.ArrayList;
import java.util.List;

public class IndividualPlaceShowActivity extends BaseActivity implements OnMapReadyCallback , DirectionFinderListener{

    private static final String TAG = IndividualPlaceShowActivity.class.getSimpleName();
    private GoogleMap mMap;
    private ArrayList<Marker> mOriginMarkers = new ArrayList<>();
    private ArrayList<Marker> mDestinationMarkers = new ArrayList<>();
    private ArrayList<Polyline> mPolylinePaths = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_place_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBarTitle(toolbar, getString(R.string.find_your_interests));
        enableToolbarBackButton(true);
        getDataFromIntent();
        initMap();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getDataFromIntent() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            LatLong source = data.getParcelable(Constants.SOURCE);
            LatLong dest = data.getParcelable(Constants.DEST);
            if (source != null && dest != null) {
                sendRequest(source, dest);
            }
        }
    }

    private void sendRequest(LatLong source, LatLong dest) {
        LatLong origin = new LatLong(source.getLatitude(), source.getLongitude());
        LatLong destination = new LatLong(dest.getLatitude(), dest.getLongitude());
        if (NetworkCheckUtility.isNetworkAvailable(this)) {
            new DirectionFinder(this, origin, destination).execute();
        } else {
            DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id.content),getString(R.string
                                                                        .please_check_internet_connection));
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationTracker.getLocationTracker(this).init();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDirectionFinderStart() {
        DialogUtils.showProgressDialog(this,  getString(R.string.please_wait));
        MapUtility.resetMarkers(mOriginMarkers, mDestinationMarkers, mPolylinePaths);
    }

    @Override
    public void onDirectionFinderSuccess(ArrayList<Routes> routes) {
        Log.d(TAG, "onDirectionFinderSuccess");
        DialogUtils.dismissProgressDialog();
        if (routes != null) {
            mPolylinePaths = new ArrayList<>();
            mOriginMarkers = new ArrayList<>();
            mDestinationMarkers = new ArrayList<>();
            for (Routes route : routes) {
                markDirections(route);
            }

        } else {
            DialogUtils.dismissProgressDialog();
            DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id.content),
                    getString(R.string.sorry_couldnt_find_directions));
        }
    }

    private void markDirections(Routes route) {
        List<LatLng> points = DecodeUtility.decodePolyLine(route.getOverViewPolyLine().getPoints());
        TextView distance = (TextView) findViewById(R.id.distance);
        for (Legs legs : route.getLegs()) {
            MapUtility.drawDirections(legs, mOriginMarkers, mDestinationMarkers, mPolylinePaths,
                    mMap, points);
            if (distance != null) {
                distance.setText(legs.getDistance().getText());
            }
        }
    }

    @Override
    public void onDirectionFinderFailure() {
        Log.d(TAG, "onDirectionFinderFailure");
        DialogUtils.dismissProgressDialog();
        DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id.content),
                getString(R.string.sorry_couldnt_find_directions));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TextView distance = (TextView) findViewById(R.id.distance);
        if (distance != null) {
            distance.setText("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
