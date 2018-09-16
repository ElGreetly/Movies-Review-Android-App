package com.example.android.moviesreview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Set;

public class SavedFragment extends Fragment {
    View v;
    Context context;
    String[] moviesNames, urls;
    MyListView myListView;
    ListView listView;
    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        moviesNames = SharedPreference.getMovies(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.saved_fragment, container, false);
        listView = (ListView) v.findViewById(R.id.saved_list_view);
        if(moviesNames.length == 1 && moviesNames[0].equals(""))
            listView.setVisibility(View.INVISIBLE);
        else
            listView.setVisibility(View.VISIBLE);
        myListView = new MyListView(context, moviesNames);
        listView.setAdapter(myListView);
        context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).registerOnSharedPreferenceChangeListener(sharedPreferences = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if(s == SharedPreference.NAMES_KEY && sharedPreferences.contains(SharedPreference.NAMES_KEY)){
                moviesNames = SharedPreference.getMovies(getContext());
                    ListView listView = (ListView) v.findViewById(R.id.saved_list_view);
                    myListView = new MyListView(context, moviesNames);
                    if(moviesNames.length == 1 && moviesNames[0].equals(""))
                        listView.setVisibility(View.INVISIBLE);
                    else
                        listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(myListView);
                }

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                moviesNames = SharedPreference.getMovies(getContext());
                urls = SharedPreference.getUrls(getContext());
                new SharedPreference().removeMovie(context, moviesNames[i], urls[i]);
            }
        });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).unregisterOnSharedPreferenceChangeListener(sharedPreferences);
    }
    String[] getDataFromPref(){
        SharedPreferences prefs = context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE);
        Set<String> moviesSet = prefs.getStringSet(SharedPreference.NAMES_KEY, null);
        String[] movies = new String[moviesSet.size()];
        int i =0;
        for(String str:moviesSet){
            movies[i]=str;
            i++;
        }
        return movies;
    }
}

