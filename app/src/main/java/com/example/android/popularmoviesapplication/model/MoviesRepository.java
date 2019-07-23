package com.example.android.popularmoviesapplication.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android.popularmoviesapplication.R;
import com.example.android.popularmoviesapplication.service.GetMoviesDataService;
import com.example.android.popularmoviesapplication.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoviesRepository {

    private List<Movie> movieList = new ArrayList<>();
    private MutableLiveData<List<Movie>> movieLiveData = new MutableLiveData<>();
    private Application application;

    public MoviesRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getPopularMovieData() {
        GetMoviesDataService service = RetrofitInstance.getMoviesDataService();
        Call<MovieResponse> responseCall = service.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse result = response.body();
                    if (result != null && result.getMovies() != null) {
                        movieList = result.getMovies();
                        movieLiveData.setValue(movieList);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call,@NonNull Throwable t) {
            }
        });
        return movieLiveData;
    }

    public MutableLiveData<List<Movie>> getTopRatedMovieData(){
        GetMoviesDataService service = RetrofitInstance.getMoviesDataService();
        Call<MovieResponse> responseCall = service.getTopRatedMovies(application.getApplicationContext().getString(R.string.api_key));
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse result = response.body();
                    if (result != null && result.getMovies() != null) {
                        movieList = result.getMovies();
                        movieLiveData.setValue(movieList);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call,@NonNull Throwable t) {
            }
        });
        return movieLiveData;
    }
}
