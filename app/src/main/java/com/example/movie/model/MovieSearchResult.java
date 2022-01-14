package com.example.movie.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;



@JsonObject
public class MovieSearchResult {
    @JsonField(name = "Search")
    public ArrayList<Movie> movieSearchList;
}
