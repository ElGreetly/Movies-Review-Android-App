package com.example.android.moviesreview;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class SharedPreference {
    final static String PREF_NAME = "MOVIES_REVIEW";
    final static String NAMES_KEY = "names";
    final static String URLS_KEY = "ulrs";
    public void setPref(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> namesSet = new HashSet<String>();
        Set<String> urlsSet = new HashSet<String>();
        namesSet.add("");
        urlsSet.add("");
        editor.putStringSet(NAMES_KEY, namesSet);
        editor.putStringSet(URLS_KEY, urlsSet);
        editor.apply();
    }
    public void addMovie(Context context, String name, String url){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> nameSet = new HashSet<String>();
        Set<String> urlsSet = new HashSet<String>();
        nameSet = pref.getStringSet(NAMES_KEY,null);
        urlsSet = pref.getStringSet(URLS_KEY, null);
        editor.remove(NAMES_KEY).apply();
        editor.remove(URLS_KEY).commit();
        nameSet.remove("");
        urlsSet.remove("");
        nameSet.add(name);
        urlsSet.add(url);
        editor.putStringSet(NAMES_KEY, nameSet).commit();
        editor.putStringSet(URLS_KEY, urlsSet).commit();
        Toast.makeText(context,"Item Add!", Toast.LENGTH_SHORT).show();
    }
    public void removeMovie(Context context, String name, String url){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> nameSet = new HashSet<String>();
        Set<String> urlsSet = new HashSet<String>();
        nameSet = pref.getStringSet(NAMES_KEY,null);
        urlsSet = pref.getStringSet(URLS_KEY, null);
        editor.remove(NAMES_KEY).apply();
        editor.remove(URLS_KEY).commit();
        nameSet.remove("");
        urlsSet.remove("");
        nameSet.remove(name);
        urlsSet.remove(url);
        editor.putStringSet(NAMES_KEY, nameSet).commit();
        editor.putStringSet(URLS_KEY, urlsSet).commit();
        Toast.makeText(context,"Item Removed!", Toast.LENGTH_SHORT).show();
    }
    public static String[] getMovies(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> movies = new HashSet<String>();
        movies = pref.getStringSet(NAMES_KEY, null);
        String[] moviesNames = new String[movies.size()];
        int i = 0;
        for (String str : movies){
            moviesNames[i] = str;
        i++;
        }
    return moviesNames;
    }
    public static String[] getUrls(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> urls = new HashSet<String>();
        urls = pref.getStringSet(URLS_KEY, null);
        String[] mUrls = new String[urls.size()];
        int i = 0;
        for(String str: urls){
            mUrls[i] = str;i++;}
        return mUrls;
    }
    public static String[] getMPAA(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> mpaa = new HashSet<String>();
        mpaa = pref.getStringSet(URLS_KEY, null);
        String[] mMPAA = new String[mpaa.size()];
        int i = 0;
        for(String str: mpaa){
            mMPAA[i] = str;i++;}
        return mMPAA;
    }
    public static String[] getSummary(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> summary = new HashSet<String>();
        summary = pref.getStringSet(URLS_KEY, null);
        String[] summarys = new String[summary.size()];
        int i = 0;
        for(String str: summary){
            summarys[i] = str;i++;}
        return summarys;
    }
    public static String[] getPublication(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Set<String> publication = new HashSet<String>();
        publication = pref.getStringSet(URLS_KEY, null);
        String[] mPublication = new String[publication.size()];
        int i = 0;
        for(String str: publication){
            mPublication[i] = str;i++;}
        return mPublication;
    }

}
