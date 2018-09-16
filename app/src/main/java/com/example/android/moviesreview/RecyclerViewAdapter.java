package com.example.android.moviesreview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Set;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    private Context context;
    private String[] moviesNames, reviewers, urls, publicationDate, summaryText, mpaaRate;
    private SharedPreference sharedPreference = new SharedPreference();
    private Set<String> namesSet;
    private Activity  activity;
    RecyclerViewAdapter(Context context, Activity activity,String[] moviesNames, String[] reviewers, String[] urls, String[] summaryText, String[] publicationDate, String[] mpaaRate) {
        this.context = context;
        this.moviesNames = moviesNames;
        this.reviewers = reviewers;
        this.urls = urls;
        this.activity = activity;
        this.publicationDate = publicationDate;
        this.summaryText = summaryText;
        this.mpaaRate = mpaaRate;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.movieName.setText(moviesNames[position]);
        holder.reviewerName.setText(reviewers[position]);
        namesSet = context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).getStringSet(SharedPreference.URLS_KEY, null);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                TextView movie, reviewer, summary, publication, mpaa;
                Button seeArticle;
                movie = (TextView) dialog.findViewById(R.id.dialog_movie_name);
                reviewer = (TextView) dialog.findViewById(R.id.dialog_reviewer_name);
                summary = (TextView) dialog.findViewById(R.id.dialog_summary);
                mpaa = (TextView) dialog.findViewById(R.id.dialog_mpaa_rating);
                publication = (TextView) dialog.findViewById(R.id.dialog_publication_date);
                seeArticle = (Button) dialog.findViewById(R.id.dialog_url);
                movie.setText(moviesNames[position]);
                reviewer.setText(reviewers[position]);
                summary.setText(summaryText[position]);
                mpaa.setText(mpaaRate[position]);
                publication.setText(publicationDate[position]);
                dialog.show();
                seeArticle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(activity, WebActivity.class).putExtra("url", urls[position]));
                    }
                });
            }
        });
        if(namesSet.contains(urls[position])) {
            holder.redImage.setVisibility(View.VISIBLE);
            holder.whiteImage.setVisibility(View.INVISIBLE);
        }
        else {
            holder.redImage.setVisibility(View.INVISIBLE);
            holder.whiteImage.setVisibility(View.VISIBLE);
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(namesSet.contains(urls[position])){
                    holder.redImage.setVisibility(View.INVISIBLE);
                    holder.whiteImage.setVisibility(View.VISIBLE);
                    sharedPreference.removeMovie(context, moviesNames[position], urls[position]);
                    namesSet = context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).getStringSet(SharedPreference.URLS_KEY, null);
                }else{
                    holder.redImage.setVisibility(View.VISIBLE);
                    holder.whiteImage.setVisibility(View.INVISIBLE);
                    sharedPreference.addMovie(context, moviesNames[position], urls[position]);
                    namesSet = context.getSharedPreferences(SharedPreference.PREF_NAME, Context.MODE_PRIVATE).getStringSet(SharedPreference.URLS_KEY, null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesNames.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView movieName;
        TextView reviewerName;
        ImageView redImage;
        ImageView whiteImage;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        Holder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movie_name);
            reviewerName = (TextView) itemView.findViewById(R.id.reviewer);
            redImage = (ImageView) itemView.findViewById(R.id.red);
            whiteImage = (ImageView) itemView.findViewById(R.id.white);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.save_layout);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.content_layout);
        }
    }
}
