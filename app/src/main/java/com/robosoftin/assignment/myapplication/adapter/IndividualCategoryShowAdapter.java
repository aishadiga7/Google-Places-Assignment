package com.robosoftin.assignment.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.intf.onIndividualCategoryClick;

import java.util.List;

/**
 * Created by aishwarya on 3/6/16.
 */
public class IndividualCategoryShowAdapter extends RecyclerView
        .Adapter<IndividualCategoryShowAdapter.ViewHolder> {

    private static final String TAG = IndividualCategoryShowAdapter.class.getSimpleName();
    private List<String> mCategoryLists;
    private onIndividualCategoryClick mOnIndividualCategoryClick;

    public IndividualCategoryShowAdapter(List<String> categoryLists,
                                         onIndividualCategoryClick listener) {
        this.mCategoryLists = categoryLists;
        this.mOnIndividualCategoryClick = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_with_card_view, parent, false);
        return new IndividualCategoryShowAdapter.ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPlaceName.setText(mCategoryLists.get(position));
        holder.bind(mCategoryLists.get(position), mOnIndividualCategoryClick);
    }

    @Override
    public int getItemCount() {
        return mCategoryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mPlaceName;
        public View mItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mPlaceName  = (TextView)itemView.findViewById(R.id.place_name);
        }

        public void bind(final String categoryName, final onIndividualCategoryClick
                                                                listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Log.d(TAG, "Cat clicked:" +categoryName);
                    listener.onIndividualCategoryClick(categoryName);
                }
            });
        }

    }
}
