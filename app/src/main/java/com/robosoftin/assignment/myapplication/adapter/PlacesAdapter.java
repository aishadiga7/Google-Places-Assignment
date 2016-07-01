package com.robosoftin.assignment.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.intf.onRecyclerViewItemClickListener;
import com.robosoftin.assignment.myapplication.model.LatLong;
import com.robosoftin.assignment.myapplication.model.SelectedPlace;

import java.util.ArrayList;

/**
 * Created by aishwarya on 28/5/16.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
    private ArrayList<SelectedPlace> mSelectedPlace;
    private onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public PlacesAdapter(ArrayList<SelectedPlace> selectedPlace, onRecyclerViewItemClickListener
            listener) {
        this.mSelectedPlace = selectedPlace;
        this.mOnRecyclerViewItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new PlacesAdapter.ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPlaceName.setText(mSelectedPlace.get(position).getName());
        holder.bind(mSelectedPlace.get(position), mOnRecyclerViewItemClickListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mPlaceName;
        public View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mPlaceName  = (TextView)itemView.findViewById(R.id.place_name);
        }

        public void bind(final SelectedPlace selectedPlace, final onRecyclerViewItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    LatLong latLong = new LatLong(selectedPlace.getLatitude(), selectedPlace
                                                                            .getLongitude());
                    listener.onItemClick(latLong);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mSelectedPlace.size();
    }
}
