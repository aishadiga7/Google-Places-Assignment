package com.robosoftin.assignment.myapplication.common;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.robosoftin.assignment.myapplication.R;

public class BaseActivity extends AppCompatActivity {


    /**
     * function that sets toolbar text
     * @param toolbar toolbar instance
     * @param title text to be displayed
     */
    public void setToolBarTitle(Toolbar toolbar, String title){
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle("");
        TextView titleView = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        titleView.setText(title);
        titleView.setTextColor(Color.WHITE);
    }

    /**
     * function which enables/disables toolbar back button
     * @param show boolean variable which tells whether to enable or not
     */
    protected void enableToolbarBackButton(boolean show) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);
            getSupportActionBar().setDisplayShowHomeEnabled(show);
            setToolBarBackNavColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * function which sets the drawable and color for the back button
     * @param color input color that has to be set
     */

    protected void setToolBarBackNavColor(int color) {
        if (getSupportActionBar() != null) {
            final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }



}
