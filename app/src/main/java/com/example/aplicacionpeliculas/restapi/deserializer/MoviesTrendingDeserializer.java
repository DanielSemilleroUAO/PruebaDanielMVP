package com.example.aplicacionpeliculas.restapi.deserializer;

import com.example.aplicacionpeliculas.pojo.Movie;
import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoviesTrendingDeserializer implements JsonDeserializer<MoviesTrendingsResponse> {
    @Override
    public MoviesTrendingsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MoviesTrendingsResponse moviesTrendingsResponse = gson.fromJson(json, MoviesTrendingsResponse.class);
        JsonObject jsonObjectData = json.getAsJsonObject();
        //results
        if(jsonObjectData.has(JsonKeys.results)){
            moviesTrendingsResponse.setMovies(deserializerMovies(jsonObjectData.get(JsonKeys.results).getAsJsonArray()));
        }
        //Total pages
        if(jsonObjectData.has(JsonKeys.total_pages)){
            moviesTrendingsResponse.setTotal_pages(jsonObjectData.get(JsonKeys.total_pages).getAsInt());
        }
        return moviesTrendingsResponse;
    }

    private ArrayList<Movie> deserializerMovies(JsonArray datamoviesArray){
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < datamoviesArray.size(); i++) {
            Movie movieCurrent = new Movie();
            JsonObject jsonObjectDataMovie = datamoviesArray.get(i).getAsJsonObject();
            //poster_path
            if(jsonObjectDataMovie.has(JsonKeys.poster_path)){
                movieCurrent.setPoster_path(jsonObjectDataMovie.get(JsonKeys.poster_path).getAsString());
            }
            //title
            if(jsonObjectDataMovie.has(JsonKeys.title)){
                movieCurrent.setTitle(jsonObjectDataMovie.get(JsonKeys.title).getAsString());
            }
            //summary
            if(jsonObjectDataMovie.has(JsonKeys.overview)){
                movieCurrent.setOverview(jsonObjectDataMovie.get(JsonKeys.overview).getAsString());
            }
            //backdrop_path
            if(jsonObjectDataMovie.has(JsonKeys.backdrop_path)){
                movieCurrent.setBackdrop_path(jsonObjectDataMovie.get(JsonKeys.backdrop_path).getAsString());
            }

            //

            movies.add(movieCurrent);
        }
        return movies;
    }

}
