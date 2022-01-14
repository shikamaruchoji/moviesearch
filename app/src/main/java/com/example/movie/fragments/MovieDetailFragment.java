package com.example.movie.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelinelabs.logansquare.LoganSquare;
import com.example.movie.R;
import com.example.movie.model.MovieDetail;
import com.example.movie.network.AsyncResponse;
import com.example.movie.network.ConnectionHelper;
import com.example.movie.network.FetchDataTask;
import com.example.movie.network.ImageDownloader;

import java.io.IOException;



public class MovieDetailFragment extends Fragment {

    private static MovieDetail movieDetail;
    private static final String IMDB_ID = "imdb_Id";
    private ImageView poster;
    private TextView txtTitle;
    private TextView txtDirector;
    private TextView txtYear;
    private TextView txtWriter;
    private TextView txtGenre;
    private TextView txtType;
    private TextView txtActors;
    private TextView txtPlot;
    private TextView txtLanguage;
    private TextView txtCountry;
    private TextView txtAwards;
    private TextView txtReleased;
    private TextView txtRuntime;
    private TextView txtMetaScore;
    private TextView txtImdbVotes;
    private RatingBar imdbRatingBar;

    public static MovieDetailFragment newInstance(String imdbId) {
        Bundle args = new Bundle();
        args.putString(IMDB_ID, imdbId);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String imdbId = getArguments().getString(IMDB_ID);

        FetchDataTask fetchMovieTask = new FetchDataTask(new AsyncResponse() {

            @Override
            public void processFinish(String output) {
                try {
                    movieDetail = LoganSquare.parse(output, MovieDetail.class);
                    if (movieDetail != null) {
                        new ImageDownloader(poster).execute(movieDetail.poster);
                        txtTitle.setText(movieDetail.title);
                        txtDirector.setText(movieDetail.director);
                        txtYear.setText(movieDetail.year);
                        txtWriter.setText(movieDetail.writer);
                        txtGenre.setText(movieDetail.genre);
                        txtType.setText(movieDetail.type);
                        txtActors.setText(movieDetail.actors);
                        txtPlot.setText(movieDetail.plot);
                        txtLanguage.setText(movieDetail.language);
                        txtCountry.setText(movieDetail.country);
                        txtAwards.setText(movieDetail.awards);
                        txtReleased.setText(movieDetail.released);
                        txtRuntime.setText(movieDetail.runtime);
                        txtMetaScore.setText(movieDetail.metascore);
                        txtImdbVotes.setText(movieDetail.imdbVotes);
                        imdbRatingBar.setRating(Float.parseFloat((movieDetail.imdbRating)) / 2);
                    } else {
                        Toast.makeText(getActivity(), getActivity().getString(R.string.alert_movie_detail_not_found), Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        fetchMovieTask.execute(ConnectionHelper.getMovieDetailByImdbIDApiUrl(imdbId).toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail_view, container, false);

        poster = (ImageView) view.findViewById(R.id.poster_Image);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtDirector = (TextView) view.findViewById(R.id.txtDirector);
        txtYear = (TextView) view.findViewById(R.id.txtYear);
        txtWriter = (TextView) view.findViewById(R.id.txtWriter);
        txtGenre = (TextView) view.findViewById(R.id.txtGenre);
        txtType = (TextView) view.findViewById(R.id.txtType);
        txtActors = (TextView) view.findViewById(R.id.txtActors);
        txtPlot = (TextView) view.findViewById(R.id.txtPlot);
        txtLanguage = (TextView) view.findViewById(R.id.txtLanguage);
        txtCountry = (TextView) view.findViewById(R.id.txtCountry);
        txtAwards = (TextView) view.findViewById(R.id.txtAwards);
        txtReleased = (TextView) view.findViewById(R.id.txtReleased);
        txtRuntime = (TextView) view.findViewById(R.id.txtRuntime);
        txtMetaScore = (TextView) view.findViewById(R.id.txtMetaScore);
        txtImdbVotes = (TextView) view.findViewById(R.id.txtImdbVotes);
        imdbRatingBar = (RatingBar) view.findViewById(R.id.imdbRatingBar);

        return view;
    }
}
