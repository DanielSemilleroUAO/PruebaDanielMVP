package com.example.aplicacionpeliculas.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.aplicacionpeliculas.IRecyclerView;
import com.example.aplicacionpeliculas.pojo.Movie;
import com.example.aplicacionpeliculas.restapi.Endpoints;
import com.example.aplicacionpeliculas.restapi.RequestApi;
import com.example.aplicacionpeliculas.restapi.adapter.RestApiAdapter;
import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewPresenter implements IRecyclerViewPresenter{

    private IRecyclerView iRecyclerView;
    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();
    private RequestApi requestApi;

    public RecyclerViewPresenter(IRecyclerView iRecyclerView, Context context) {
        this.iRecyclerView = iRecyclerView;
        this.context = context;
        getMoviesPresenter();
    }

    @Override
    public void getMoviesPresenter() {
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
                        Toast.makeText(context,"Failed to load movies",Toast.LENGTH_LONG).show();
                        break;
                }
                showMoviesRV();
            }

            @Override
            public void onFailure(Call<MoviesTrendingsResponse> call, Throwable t) {
                showMoviesRV();
                Toast.makeText(context,"Failed to load movies",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showMoviesRV() {
        iRecyclerView.inicializateAdapterMovies(iRecyclerView.createAdapter(movies));
        iRecyclerView.generateGridLayout();
    }

    @Override
    public void refreshMovies() {
        iRecyclerView.refreshMovies();
    }
}
