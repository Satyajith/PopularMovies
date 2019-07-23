package com.example.android.popularmoviesapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.popularmoviesapplication.model.Movie;
import com.example.android.popularmoviesapplication.model.MoviesRepository;

import java.util.List;

public class MoviesActivityViewModel extends AndroidViewModel {

    private MoviesRepository repository;

    public MoviesActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new MoviesRepository(application);
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return repository.getPopularMovieData();
    }

    public LiveData<List<Movie>> getTopRatedMovies(){
        return repository.getTopRatedMovieData();
    }
}
