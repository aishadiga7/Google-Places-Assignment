package com.robosoftin.assignment.myapplication.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.Utils.DialogUtils;
import com.robosoftin.assignment.myapplication.Utils.NetworkCheckUtility;
import com.robosoftin.assignment.myapplication.adapter.PlacesAdapter;
import com.robosoftin.assignment.myapplication.common.BaseActivity;
import com.robosoftin.assignment.myapplication.common.Constants;
import com.robosoftin.assignment.myapplication.common.LocationTracker;
import com.robosoftin.assignment.myapplication.common.NearByLocationFinder;
import com.robosoftin.assignment.myapplication.common.Notification;
import com.robosoftin.assignment.myapplication.common.NotificationManager;
import com.robosoftin.assignment.myapplication.intf.LocationFinderListener;
import com.robosoftin.assignment.myapplication.intf.onRecyclerViewItemClickListener;
import com.robosoftin.assignment.myapplication.model.LatLong;
import com.robosoftin.assignment.myapplication.model.Result;
import com.robosoftin.assignment.myapplication.model.SelectedPlace;

import java.util.ArrayList;

public class IndividualCategoriesPlotActivity extends BaseActivity implements NotificationManager
        .Observer, OnMapReadyCallback, onRecyclerViewItemClickListener, LocationFinderListener {

    private static final String TAG = IndividualCategoriesPlotActivity.class.getSimpleName();
    public static final int CAMERA_ZOOM_EXTENT = 16;
    private static final int REQUEST_LOCATION = 100;
    private String mSelectedCategory;
    private Location mCurrentLocation;
    ArrayList<SelectedPlace> mSelectedCategoryListLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBarTitle(toolbar, getString(R.string.find_your_interests));
        enableToolbarBackButton(true);
        getCategoryTypeFromIntent();
    }

    private void getCategoryTypeFromIntent() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            mSelectedCategory = data.getString(Constants.CATEGORY);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocationTracker.getLocationTracker(this).init();
        NotificationManager.getInstance().addObserver(Notification.NOTIFY_LOCATION_CHANGED,
                IndividualCategoriesPlotActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationTracker.getLocationTracker(this).init();
    }

    @Override
    public void update(Notification notificationName, Bundle data) {
        if (notificationName == Notification.NOTIFY_LOCATION_CHANGED) {
            if (data != null) {
                mCurrentLocation = data.getParcelable(Constants.EXTRA_LOCATION_DATA);
                if (NetworkCheckUtility.isNetworkAvailable(this)) {
                    new NearByLocationFinder(this).execute(mCurrentLocation, mSelectedCategory);
                } else {
                    DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id
                            .content), getString(R.string.please_check_internet_connection));
                }
            } else {
                DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id
                        .content), getString(R.string.location_cannot_be_fetched));
            }
        }
    }

    @Override
    protected void onDestroy() {
        NotificationManager.getInstance().removeObserver(Notification.NOTIFY_LOCATION_CHANGED, this);
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map;
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        for (SelectedPlace selectedCategory : mSelectedCategoryListLatLng) {
            map.addMarker(new MarkerOptions().position(
                    new LatLng(selectedCategory.getLatitude(), selectedCategory.getLongitude()))
                    .title(selectedCategory.getName()));
            LatLng location = new LatLng(selectedCategory.getLatitude(), selectedCategory.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(location)      // Sets the center of the map to location user
                                .zoom(CAMERA_ZOOM_EXTENT)                   // Sets the zoom
                                .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onItemClick(LatLong selectedPlace) {
        Intent individualPlaceShowIntent = new Intent(IndividualCategoriesPlotActivity.this,
                IndividualPlaceShowActivity.class);
        individualPlaceShowIntent.putExtra(Constants.SOURCE, selectedPlace);
        individualPlaceShowIntent.putExtra(Constants.DEST, new LatLong(mCurrentLocation
                                                    .getLatitude(), mCurrentLocation.getLongitude()));
        startActivity(individualPlaceShowIntent);
    }

    @Override
    public void onLocationFindStart() {
        DialogUtils.showProgressDialog(this, getString(R.string.loading));
    }

    @Override
    public void onLocationFindSuccess(ArrayList<Result> locationResult) {
        DialogUtils.dismissProgressDialog();
        Log.d(TAG, "onLocationFindSuccess()");
        if (locationResult != null) {
            ArrayList<SelectedPlace> selectedPlaceList = new ArrayList<>();
            for (Result result : locationResult) {
                selectedPlaceList.add(new SelectedPlace(result.getName(), result
                        .getGeometry().getLocation().getLatitude(), result
                        .getGeometry().getLocation().getLongitude()));
            }
            mSelectedCategoryListLatLng = selectedPlaceList;
            initRecyclerView();
            initMap();
        } else {
            DialogUtils.dismissProgressDialog();
            DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id.content),
                    getString(R.string.no_places_found));
        }
    }

    @Override
    public void onLocationFindFailure() {
        Log.d(TAG, "onLocationFindFailure()");
        DialogUtils.dismissProgressDialog();
        DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id.content),
                getString(R.string.sorry_couldnt_find_place));
    }

    private void initRecyclerView() {
        RecyclerView individualCategoryRecyclerView = (RecyclerView) findViewById(R.id
                                                                .individual_cat_items);
        if (individualCategoryRecyclerView != null) {
            individualCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            PlacesAdapter placesAdapter = new PlacesAdapter(mSelectedCategoryListLatLng, this);
            individualCategoryRecyclerView.setAdapter(placesAdapter);
            individualCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    private void initMap() {
        Log.d(TAG, "Map initiated:");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id
                        .content), getString(R.string.permission_granted));
            } else {
                DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id
                        .content), getString(R.string.permission_not_granted));
            }
        }
    }
}
