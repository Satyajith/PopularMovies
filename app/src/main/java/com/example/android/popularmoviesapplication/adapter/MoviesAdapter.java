package com.example.android.popularmoviesapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popularmoviesapplication.R;
import com.example.android.popularmoviesapplication.databinding.MovieItemBinding;
import com.example.android.popularmoviesapplication.model.Movie;
import com.example.android.popularmoviesapplication.util.MovieDiffCallback;
import com.example.android.popularmoviesapplication.view.MoviesActivity.MovieClickHandler;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movieData = new ArrayList<>();
    private MovieClickHandler clickHandler;

    public MoviesAdapter(MovieClickHandler clickHandler) {
        this.clickHandler = clickHandler;
        setHasStableIds(true);
    }

    public void setMovies(List<Movie> movies) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MovieDiffCallback(movieData, movies), false);
        movieData = movies;
        result.dispatchUpdatesTo(MoviesAdapter.this);
    }

    @Override
    public long getItemId(int position) {
        return movieData.get(position).getId();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent, false);
        return new MovieViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieItemBinding.setMovie(movieData.get(position));
        holder.movieItemBinding.setClickHandler(clickHandler);
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieItemBinding movieItemBinding;

        MovieViewHolder(@NonNull MovieItemBinding itemBinding) {
            super(itemBinding.getRoot());
            movieItemBinding = itemBinding;
        }
    }
}
