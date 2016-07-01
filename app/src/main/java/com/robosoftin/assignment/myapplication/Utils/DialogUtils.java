package com.robosoftin.assignment.myapplication.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.intf.CustomDialogCallBack;

/**
 * Created by aishwarya on 28/5/16.
 */
public class DialogUtils {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static ProgressDialog mProgressDialog = null;


    /**
     * Custom Dialog to show both positive and negative button with callback
     *
     * @param context            Calling context.
     * @param title              Title of dialog.
     * @param message            Message to display in dialog.
     * @param positiveButtonText Positive button text. Can be null for hiding this button in dialog.
     * @param negativeButtonText Negative button text. Can be null for hiding this button in dialog.
     * @param callBack           Listener for handling positive and negative button click.
     */
    public static void showCustomDialogWithCallBack(Context context, String title, String message,
                                                    String positiveButtonText, String negativeButtonText,
                                                    final CustomDialogCallBack callBack) {
                final Dialog alertDialog = new Dialog(context);
        try {
            if (alertDialog == null || !alertDialog.isShowing()) {
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.custom_dialog);
                WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                alertDialog.getWindow().setAttributes(params);
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                TextView titleTv = (TextView) alertDialog.findViewById(R.id.title);
                TextView messageTv = (TextView) alertDialog.findViewById(R.id.message);
                TextView positiveBut = (TextView) alertDialog.findViewById(R.id.positive_but);
                TextView negativeBut = (TextView) alertDialog.findViewById(R.id.negative_but);
                titleTv.setText(title);
                messageTv.setText(message);
                positiveBut.setText(positiveButtonText);

                if (negativeButtonText == null) {
                    negativeBut.setVisibility(View.GONE);

                } else {
                    negativeBut.setVisibility(View.VISIBLE);
                    negativeBut.setText(negativeButtonText);
                }
                positiveBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callBack.positiveCallBack(alertDialog);
                    }
                });
                negativeBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callBack.negativeCallBack(alertDialog);
                    }
                });
                alertDialog.show();
            }
        } catch (IllegalArgumentException iae) {
            showToast(context, context.getString(R.string.something_went_wrong));
            iae.printStackTrace();

        } catch (WindowManager.BadTokenException bte) {
            showToast(context, context.getString(R.string.something_went_wrong));
            bte.printStackTrace();
        }
    }

    public static  boolean isPlayServicesInstalled(Context context) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(context);
        if(result != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    /**
     * Display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    public static void showGooglePlayServicesErrorDialog(Context context) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(context);
        if (context instanceof Activity) {
            try {
                if (googleAPI.isUserResolvableError(resultCode)) {
                    googleAPI.getErrorDialog((Activity) context, resultCode,
                            PLAY_SERVICES_RESOLUTION_REQUEST).show();
                }
            } catch (IllegalArgumentException iae) {
                showToast(context, context.getString(R.string.something_went_wrong));
                iae.printStackTrace();
            } catch (WindowManager.BadTokenException bte) {
                showToast(context, context.getString(R.string.something_went_wrong));
                bte.printStackTrace();
            }
        }
    }

    public static void showProgressDialog(Context context, String message){
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }


   public static void dismissProgressDialog() {
           if((mProgressDialog != null) && mProgressDialog.isShowing()) {
               mProgressDialog.dismiss();
               mProgressDialog = null;
           }
   }

    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


}
