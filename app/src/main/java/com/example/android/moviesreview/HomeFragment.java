package com.example.android.moviesreview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    String[] moviesNames, reviewers, urls, summary, publication, mpaa;
    View v;
    RecyclerView recyclerView;
    String json;
    Context context;
    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        json = SplashFragment.json;
        JsonData jsonData = new JsonData();
        moviesNames = jsonData.getNames(json);
        reviewers = jsonData.getReviewers(json);
        urls = jsonData.getUrls(json);
        summary = jsonData.getSummaries(json);
        publication = jsonData.getPublication(json);
        mpaa = jsonData.getMPAAs(json);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), getActivity(),moviesNames, reviewers, urls, summary, publication, mpaa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).registerOnSharedPreferenceChangeListener(sharedPreferences = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }

}
