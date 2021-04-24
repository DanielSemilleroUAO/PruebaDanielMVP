package com.example.aplicacionpeliculas;

import com.example.aplicacionpeliculas.adapter.MovieAdapter;
import com.example.aplicacionpeliculas.pojo.Movie;

import java.util.ArrayList;

public interface IRecyclerView {

    public void generateGridLayout();
    public MovieAdapter createAdapter(ArrayList<Movie> movies);
    public void inicializateAdapterMovies(MovieAdapter movieAdapter);
    public void refreshMovies();


}
