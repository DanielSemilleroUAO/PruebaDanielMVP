package com.example.aplicacionpeliculas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionpeliculas.adapter.MovieAdapter;
import com.example.aplicacionpeliculas.pojo.Movie;
import com.example.aplicacionpeliculas.pojo.User;
import com.example.aplicacionpeliculas.restapi.Endpoints;
import com.example.aplicacionpeliculas.restapi.adapter.RestApiAdapter;
import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMovies;
    private LinearLayout llMovies;
    private TextView tvTitleAppBar;
    private ProgressBar progressBar;
    private ArrayList<Movie> movies = new ArrayList<>();
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        llMovies = (LinearLayout) findViewById(R.id.llMovies);
        tvTitleAppBar = (TextView) findViewById(R.id.tvtoolbar_titulo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        movies = user.getMovies();
        if(movies.isEmpty()){
            GetMovies();
        }else {
            CreateMovies();
        }

    }

    private void CreateMovies(){
        llMovies.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        GridLayoutManager llm = new GridLayoutManager(MainActivity.this,3);
        llm.setOrientation(RecyclerView.VERTICAL);
        rvMovies.setLayoutManager(llm);
        MovieAdapter movieAdapter = new MovieAdapter(movies,MainActivity.this);
        rvMovies.setAdapter(movieAdapter);
    }

    private void GetMovies(){
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
                        CreateMovies();
                        break;
                    default:
                        Toast.makeText(getBaseContext(),"Error to load movies",Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<MoviesTrendingsResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Failed to load movies",Toast.LENGTH_LONG).show();
            }
        });
    }


}