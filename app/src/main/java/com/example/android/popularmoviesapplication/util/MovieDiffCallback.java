package com.example.android.popularmoviesapplication.util;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.android.popularmoviesapplication.model.Movie;

import java.util.List;

public class MovieDiffCallback extends DiffUtil.Callback {

    private List<Movie> oldMovies, newMovies;

    public MovieDiffCallback(List<Movie> oldMovies, List<Movie> newMovies) {
        this.oldMovies = oldMovies;
        this.newMovies = newMovies;
    }

    @Override
    public int getOldListSize() {
        return oldMovies == null ? 0 : oldMovies.size();
    }

    @Override
    public int getNewListSize() {
        return newMovies == null ? 0 : newMovies.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return (int) oldMovies.get(oldItemPosition).getId() == newMovies.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

}
