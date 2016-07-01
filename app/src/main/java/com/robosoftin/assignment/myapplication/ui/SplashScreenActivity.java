package com.robosoftin.assignment.myapplication.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.Utils.DialogUtils;
import com.robosoftin.assignment.myapplication.Utils.LocationUtility;
import com.robosoftin.assignment.myapplication.Utils.NetworkCheckUtility;
import com.robosoftin.assignment.myapplication.common.BaseActivity;
import com.robosoftin.assignment.myapplication.intf.CustomDialogCallBack;

import static com.robosoftin.assignment.myapplication.Utils.LocationUtility.launchLocationSettingsActivity;

public class SplashScreenActivity extends BaseActivity  {
    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private static final int DELAY_TO_START_HOMESCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        checkForLocationService();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private  void checkIfPlayServicesIsInstalled(Context context) {
        if (context != null) {
            if (DialogUtils.isPlayServicesInstalled(context)) {
                if (NetworkCheckUtility.isNetworkAvailable(this)) {
                    launchHomeScreenWithDelay();
                } else {
                    DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id
                            .content), getString(R.string.please_check_internet_connection));
                }
            } else {
                DialogUtils.showGooglePlayServicesErrorDialog(context);
            }
        }
    }

    private void checkForLocationService() {
        Log.d(TAG, "Inside OnClick:");
        if (!LocationUtility.isLocationEnabled(this)) {
            LocationUtility.showEnableLocationDialog(this, new CustomDialogCallBack() {
                @Override
                public void positiveCallBack(Dialog dialog) {
                    dialog.dismiss();
                    launchLocationSettingsActivity(SplashScreenActivity.this);
                    checkIfPlayServicesIsInstalled(SplashScreenActivity.this);
                }

                @Override
                public void negativeCallBack(Dialog dialog) {
                    dialog.dismiss();
                    DialogUtils.showSnackBar(getWindow().getDecorView().findViewById(android.R.id
                            .content), getString(R.string.location_services_has_to_be_enabled));
                }
            });
        } else {
            checkIfPlayServicesIsInstalled(this);
        }
    }

    private void launchHomeScreenWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeScreenIntent = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
                startActivity(homeScreenIntent);
                finish();
            }
        }, DELAY_TO_START_HOMESCREEN);
    }


}
