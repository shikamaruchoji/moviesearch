package com.example.movie.adapters;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.fragments.MovieDetailFragment;
import com.example.movie.model.Movie;
import com.example.movie.network.ImageDownloader;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private ArrayList<Movie> movieList;
    Fragment movieDetailFragment = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, director;
        public ImageView thumbNail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtTitle);
            year = (TextView) view.findViewById(R.id.txtYear);
            director = (TextView) view.findViewById(R.id.txtDirector);
            thumbNail = (ImageView) view.findViewById(R.id.thumbNail);
        }
    }

    public MovieAdapter(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Movie movie = movieList.get(position);

        holder.title.setText(movie.title);
        holder.year.setText(movie.year);

        new ImageDownloader(holder.thumbNail).execute(movie.poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                movieDetailFragment = MovieDetailFragment.newInstance(movie.imdbID);
                activity.getFragmentManager().beginTransaction().replace(R.id.activity_main, movieDetailFragment).addToBackStack(null).commit();
            }
        });
    }

    public void addMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}