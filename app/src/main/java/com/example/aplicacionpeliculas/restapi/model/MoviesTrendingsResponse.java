package com.example.aplicacionpeliculas.restapi.model;

import com.example.aplicacionpeliculas.pojo.Movie;

import java.util.ArrayList;

public class MoviesTrendingsResponse {

    private ArrayList<Movie> movies = new ArrayList<>();
    private int total_pages = 0;

    public MoviesTrendingsResponse() {
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
