package com.example.aplicacionpeliculas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionpeliculas.adapter.MovieAdapter;
import com.example.aplicacionpeliculas.pojo.Movie;
import com.example.aplicacionpeliculas.pojo.User;
import com.example.aplicacionpeliculas.presenter.IRecyclerViewPresenter;
import com.example.aplicacionpeliculas.presenter.RecyclerViewPresenter;
import com.example.aplicacionpeliculas.restapi.Endpoints;
import com.example.aplicacionpeliculas.restapi.adapter.RestApiAdapter;
import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements IRecyclerView{

    private RecyclerView rvMovies;
    private LinearLayout llMovies;
    private TextView tvTitleAppBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private CardView cvNoMovies;
    //private ArrayList<Movie> movies = new ArrayList<>();
    private User user = new User();
    private IRecyclerViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        llMovies = (LinearLayout) findViewById(R.id.llMovies);
        tvTitleAppBar = (TextView) findViewById(R.id.tvtoolbar_titulo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        cvNoMovies = (CardView) findViewById(R.id.cvNoMovies);

        presenter = new RecyclerViewPresenter(this,getBaseContext());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMoviesPresenter();
                rvMovies.setVisibility(View.VISIBLE);
                cvNoMovies.setVisibility(View.GONE);
                swipeRefreshLayout.setNestedScrollingEnabled(false);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void generateGridLayout() {
        llMovies.setVisibility(View.VISIBLE);
        GridLayoutManager llm = new GridLayoutManager(MainActivity.this,3);
        llm.setOrientation(RecyclerView.VERTICAL);
        rvMovies.setLayoutManager(llm);
    }

    @Override
    public MovieAdapter createAdapter(ArrayList<Movie> movies) {
        if(movies.isEmpty()){
            progressBar.setVisibility(View.GONE);
            llMovies.setVisibility(View.VISIBLE);
            rvMovies.setVisibility(View.GONE);
            cvNoMovies.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
            llMovies.setVisibility(View.VISIBLE);
            rvMovies.setVisibility(View.VISIBLE);
            cvNoMovies.setVisibility(View.GONE);
        }
        MovieAdapter movieAdapter = new MovieAdapter(movies,MainActivity.this);
        return movieAdapter;
    }

    @Override
    public void inicializateAdapterMovies(MovieAdapter movieAdapter) {
        rvMovies.setAdapter(movieAdapter);
    }

    @Override
    public void refreshMovies() {
    }


}