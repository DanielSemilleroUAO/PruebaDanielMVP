package com.example.aplicacionpeliculas.pojo;

import java.util.ArrayList;

public class User {

    private ArrayList<Movie> movies = new ArrayList<>();

    public User() {
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
