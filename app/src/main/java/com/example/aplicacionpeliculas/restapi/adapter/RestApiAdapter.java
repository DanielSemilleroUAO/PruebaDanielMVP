package com.example.aplicacionpeliculas.restapi.adapter;

import android.util.Log;

import com.example.aplicacionpeliculas.restapi.ConstantsRestApi;
import com.example.aplicacionpeliculas.restapi.Endpoints;
import com.example.aplicacionpeliculas.restapi.deserializer.MovieDeserializer;
import com.example.aplicacionpeliculas.restapi.deserializer.MoviesTrendingDeserializer;
import com.example.aplicacionpeliculas.restapi.model.MovieResponse;
import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public RestApiAdapter() {
    }

    public Endpoints RestApi(Gson gson){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        //Log.e("Headers",request.headers().toString());
                        Log.e("URL",request.toString());
                        //Log.e("Body",request.body().toString());
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(Endpoints.class);

    }

    public Gson DeserializerMovies(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MovieResponse.class, new MovieDeserializer());
        return gsonBuilder.create();
    }

    public Gson DeserialerMoviesTrending(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MoviesTrendingsResponse.class, new MoviesTrendingDeserializer());
        return gsonBuilder.create();
    }


}
