package com.example.android.moviesreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.io.IOException;
/*
* This @Fragment used for Getting data from internet with asyncTask
*to avoid in landescape change over executing
*/
public class SplashFragment extends Fragment {
    Context context;
    public static String json;
    final static String MAIN_KEY = "msg";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        new GetData().execute(getActivity());
        context = getContext();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class GetData extends AsyncTask<Activity,Void,String>{
        Activity activity;
        @Override
        protected String doInBackground(Activity... activities) {
            String json = null;
            try {
                json = new NetworkConnection().getRespond("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            activity = activities[0];
            return json;
        }

        @Override
        protected void onPostExecute(String str) {
            if(!context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).contains(SharedPreference.NAMES_KEY))
                new SharedPreference().setPref(context);
            json = str;
            startActivity(new Intent(activity, MainActivity.class).putExtra(MAIN_KEY,str));
            activity.finish();
            super.onPostExecute(str);
        }
    }
}
