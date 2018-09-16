package com.example.android.moviesreview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.IOException;

public class SearchFragment extends Fragment {
    Context context;
    Activity activity;
    ProgressDialog progressDialog;
    String search;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search = getArguments().getString("search_key");
        context = getContext();
        activity = getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        new GetData().execute(search);
        return null;
    }
    public class GetData extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            try {
                json = new NetworkConnection().getRespond(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            context.startActivity(new Intent(activity, SearchActivity.class).putExtra("str",str));
        }
    }
}
