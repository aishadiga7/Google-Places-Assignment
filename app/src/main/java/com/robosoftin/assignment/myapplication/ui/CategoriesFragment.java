package com.robosoftin.assignment.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.adapter.IndividualCategoryShowAdapter;
import com.robosoftin.assignment.myapplication.common.BaseFragment;
import com.robosoftin.assignment.myapplication.common.Constants;
import com.robosoftin.assignment.myapplication.intf.onIndividualCategoryClick;

import java.util.Arrays;
import java.util.List;


public class CategoriesFragment extends BaseFragment implements onIndividualCategoryClick {
    private static final String TAG = CategoriesFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, null);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        RecyclerView  categoriesRecyclerView = (RecyclerView) view.findViewById(R.id.cat_items);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> categoryNames = getAvailableCategoryNames();
        IndividualCategoryShowAdapter individualCategoryShowAdapter  = new IndividualCategoryShowAdapter
                                                                    (categoryNames, this);
        categoriesRecyclerView.setAdapter(individualCategoryShowAdapter);
        categoriesRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> getAvailableCategoryNames() {
        List<String> categoryNames = Arrays.asList(getActivity().getResources()
                .getStringArray(R.array.place_types));
        return categoryNames;
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
    public void onIndividualCategoryClick(String categoryName) {
        Log.d(TAG, "Category Name:" +categoryName);
        Intent individualCategoryIntent = new Intent(getActivity(),
                IndividualCategoriesPlotActivity.class);
        individualCategoryIntent.putExtra(Constants.CATEGORY, categoryName.toLowerCase());
        startActivity(individualCategoryIntent);
    }

}
