package com.example.aplicacionpeliculas.restapi;

import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Endpoints {

    @GET(ConstantsRestApi.GET_MOVIES_TRENDING)
    Call<MoviesTrendingsResponse> GetMovies(@Path("media_type") String type_media, @Path("time_window") String time);

}
