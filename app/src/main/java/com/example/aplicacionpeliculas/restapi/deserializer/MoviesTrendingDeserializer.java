package com.example.aplicacionpeliculas.restapi.deserializer;

import com.example.aplicacionpeliculas.restapi.model.MoviesTrendingsResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MoviesTrendingDeserializer implements JsonDeserializer<MoviesTrendingsResponse> {
    @Override
    public MoviesTrendingsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
