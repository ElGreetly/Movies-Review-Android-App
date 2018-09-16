package com.example.android.moviesreview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListView extends ArrayAdapter<String> {
    String[] moviesNames;
    Context context;
    public MyListView(@NonNull Context context, String[] moviesNames) {
        super(context, 0, moviesNames);
        this.moviesNames = moviesNames;
        this.context = context;
    }
    static class Holder {
        TextView movieName;
        ImageView delete;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        Holder holder = new Holder();
        holder.movieName = (TextView) convertView.findViewById(R.id.saved_movie_name);
        holder.delete = (ImageView) convertView.findViewById(R.id.delete);
        holder.movieName.setText(moviesNames[position]);
        return convertView;
    }
}
