package com.example.movie.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;


@JsonObject
public class MovieDetail {
    @JsonField(name = "Title")
    public String title;
    @JsonField(name = "Year")
    public String year;
    @JsonField(name = "Rated")
    public String rated;
    @JsonField(name = "Released")
    public String released;
    @JsonField(name = "Runtime")
    public String runtime;
    @JsonField(name = "Genre")
    public String genre;
    @JsonField(name = "Director")
    public String director;
    @JsonField(name = "Writer")
    public String writer;
    @JsonField(name = "Actors")
    public String actors;
    @JsonField(name = "Plot")
    public String plot;
    @JsonField(name = "Language")
    public String language;
    @JsonField(name = "Country")
    public String country;
    @JsonField(name = "Awards")
    public String awards;
    @JsonField(name = "Poster")
    public String poster;
    @JsonField(name = "Metascore")
    public String metascore;
    @JsonField(name = "imdbRating")
    public String imdbRating;
    @JsonField(name = "imdbVotes")
    public String imdbVotes;
    @JsonField(name = "imdbID")
    public String imdbID;
    @JsonField(name = "Type")
    public String type;
    @JsonField(name = "Response")
    public String response;
}