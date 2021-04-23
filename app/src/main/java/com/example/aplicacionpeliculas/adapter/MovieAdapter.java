package com.example.aplicacionpeliculas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionpeliculas.R;
import com.example.aplicacionpeliculas.pojo.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<Movie> movies;
    Activity activity;

    public MovieAdapter(ArrayList<Movie> movies, Activity activity) {
        this.movies = movies;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_movie, parent,false);
        return new MovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
