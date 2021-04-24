package com.example.aplicacionpeliculas.restapi;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.aplicacionpeliculas.pojo.Movie;
import com.example.aplicacionpeliculas.restapi.adapter.RestApiAdapter;
import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestApi {

    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();

    public RequestApi(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> GetMovies(){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMoviesTrending = restApiAdapter.DeserialerMoviesTrending();
        Endpoints endpointsApi = restApiAdapter.RestApi(gsonMoviesTrending);

        Call<MoviesTrendingsResponse> moviesTrendingsResponseCall = endpointsApi.GetMovies("movie","week");
        moviesTrendingsResponseCall.enqueue(new Callback<MoviesTrendingsResponse>() {
            @Override
            public void onResponse(Call<MoviesTrendingsResponse> call, Response<MoviesTrendingsResponse> response) {
                switch (response.code()){
                    case 200:
                        MoviesTrendingsResponse moviesTrendingsResponse = response.body();
                        movies = moviesTrendingsResponse.getMovies();
                        //CreateMovies();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<MoviesTrendingsResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to load movies",Toast.LENGTH_LONG).show();
            }
        });

        return movies;
    }

}
