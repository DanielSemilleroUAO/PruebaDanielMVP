package com.example.aplicacionpeliculas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionpeliculas.DetailMovieActivity;
import com.example.aplicacionpeliculas.R;
import com.example.aplicacionpeliculas.pojo.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_movie, parent,false);
        return new MovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movieCurrent = movies.get(position);

        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.imgMovie.getWidth();
                double aspectRatio = (double) source.getHeight() / source.getWidth();
                int targetHeight = (int) (targetWidth*aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source,source.getWidth(),source.getHeight(),false);
                if(holder.imgMovie.getWidth()>0 && holder.imgMovie.getHeight()>0){
                    result = Bitmap.createScaledBitmap(source,targetWidth,targetHeight,false);
                }
                if(result != source){
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        try {
            Picasso.with(activity)
                    .load("https://image.tmdb.org/t/p/original"+movieCurrent.getPoster_path())
                    .placeholder(R.mipmap.ic_no_imagen)
                    .transform(transformation)
                    .into(holder.imgMovie);
        }catch (Exception e){

        }


        holder.imgMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,String.valueOf(position),Toast.LENGTH_LONG).show();
                Intent intentDetail= new Intent(activity, DetailMovieActivity.class);
                intentDetail.putExtra("title",movieCurrent.getTitle());
                intentDetail.putExtra("summary",movieCurrent.getOverview());
                intentDetail.putExtra("backdrop_path",movieCurrent.getBackdrop_path());
                activity.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgMovie;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = (ImageView) itemView.findViewById(R.id.imgMovie);
        }
    }


}
