package com.robosoftin.assignment.myapplication.Utils;


import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.intf.CustomDialogCallBack;


public class LocationUtility {

    private static final String TAG = LocationUtility.class.getSimpleName();

    public static boolean isGPSEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                DialogUtils.showToast(context, context.getString(R.string.something_went_wrong));
                e.printStackTrace();
            }
            Log.d(TAG, "locationMode: " + locationMode);
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            Log.d(TAG, "locationProviders: " + locationProviders);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager;
        boolean networkEnabled;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.d(TAG, "networkEnabled: " + networkEnabled);
        boolean gpsEnabled = isGPSEnabled(context);
        Log.d(TAG, "gpsEnabled: " + gpsEnabled);
        boolean isLocationEnabled = gpsEnabled || networkEnabled;
        Log.d(TAG, "isLocationEnabled: " + isLocationEnabled);
        return isLocationEnabled;
    }

    public static void launchLocationSettingsActivity(Context context) {
        Intent locSettingsLaunchIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        if (locSettingsLaunchIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(locSettingsLaunchIntent);
        }
    }

    public static void showEnableLocationDialog(final Context context, CustomDialogCallBack customDialogCallBack) {
        String settings = context.getString(R.string.action_settings).toUpperCase();
        DialogUtils.showCustomDialogWithCallBack(context, context.getString(R.string.location_disabled),
                context.getString(R.string.please_turn_on_location),
                settings, context.getString(R.string.cancel), customDialogCallBack);
    }

}

