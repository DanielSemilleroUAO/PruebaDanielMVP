package com.example.aplicacionpeliculas.restapi;

public class ConstantsRestApi {

    //https://api.themoviedb.org/3
    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=8671ce3aabcac1ba8a70cfc9cc851c03
    //https://api.themoviedb.org/3/trending/{media_type}}/{time_window}?api_key=8671ce3aabcac1ba8a70cfc9cc851c03


    public static final String NAME_API = "?api_key=";
    public static final String API_KEY = "8671ce3aabcac1ba8a70cfc9cc851c03";
    public static final String API_KEY_COMPLETED = NAME_API+API_KEY;
    public static final String ROOT_URL = "https://api.themoviedb.org/3/";
    public static final String PLATFORM = "";
    //ENDPOINT MOVIES
    public static final String GET_MOVIE_DETAIL = "Customers/account-by-email"+API_KEY_COMPLETED;
    public static final String GET_MOVIES_TRENDING = "trending/{media_type}/{time_window}"+API_KEY_COMPLETED;

}
