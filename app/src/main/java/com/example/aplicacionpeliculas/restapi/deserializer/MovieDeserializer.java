package com.example.aplicacionpeliculas.restapi.deserializer;

import com.example.aplicacionpeliculas.restapi.model.MovieResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MovieDeserializer implements JsonDeserializer<MovieResponse> {
    @Override
    public MovieResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
