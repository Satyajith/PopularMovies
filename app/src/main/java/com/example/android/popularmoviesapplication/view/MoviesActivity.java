package com.example.android.popularmoviesapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.popularmoviesapplication.adapter.MoviesAdapter;
import com.example.android.popularmoviesapplication.viewmodel.MoviesActivityViewModel;
import com.example.android.popularmoviesapplication.R;
import com.example.android.popularmoviesapplication.databinding.ActivityMoviesBinding;
import com.example.android.popularmoviesapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private MoviesActivityViewModel viewModel;
    private ActivityMoviesBinding dataBinding;
    private List<Movie> moviesList;
    private Boolean isPopular = true;
    private RecyclerView moviesRecView;
    private MoviesAdapter adapter;
    private ProgressBar loading;
    private GridLayoutManager layoutManager;
    private MovieClickHandler clickHandler;
    public static final String MOVIE_ITEM = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        viewModel = ViewModelProviders.of(this).get(MoviesActivityViewModel.class);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        clickHandler = new MovieClickHandler(MoviesActivity.this);

        initViews();
        initToolbar();
        getPopularMovies();
    }

    void initViews() {
        moviesRecView = dataBinding.recyclerViewMovies;
        loading = dataBinding.progressBarMoviesLoading;
        adapter = new MoviesAdapter(clickHandler);
        layoutManager = new GridLayoutManager(this, 2);
    }

    void initToolbar() {
        Toolbar toolbar = dataBinding.toolbarMain;
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBackground));
    }

    void setToolbarTitle(String title){
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    void updateRecView() {
        invalidateOptionsMenu();
        adapter.setMovies(moviesList);
        showRecyclerView();
        moviesRecView.setLayoutManager(layoutManager);
        moviesRecView.setAdapter(adapter);
    }

    void showRecyclerView() {
        moviesRecView.setVisibility(View.VISIBLE);
        loading.setVisibility(View.INVISIBLE);
    }

    void hideRecyclerView() {
        moviesRecView.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);
    }


    void getPopularMovies() {
        hideRecyclerView();
        moviesList = new ArrayList<>();
        viewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                isPopular = false;
                setToolbarTitle(getString(R.string.popular));
                moviesList = movies;
                updateRecView();
            }
        });
    }

    void getTopRatedMovies() {
        hideRecyclerView();
        moviesList = new ArrayList<>();
        viewModel.getTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                isPopular = true;
                setToolbarTitle(getString(R.string.top_rated));
                moviesList = movies;
                updateRecView();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        switch (itemSelected) {
            case R.id.action_filter:
                break;
            case R.id.action_popular_movies:
                getPopularMovies();
                break;
            case R.id.action_top_rated_movies:
                getTopRatedMovies();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.getItem(0).getSubMenu();
        MenuItem popular = subMenu.getItem(0);
        MenuItem top = subMenu.getItem(1);
        popular.setVisible(isPopular);
        top.setVisible(!isPopular);
        return super.onPrepareOptionsMenu(menu);
    }

    public class MovieClickHandler {

        private Context context;

        MovieClickHandler(Context context) {
            this.context = context;
        }

        public void onMovieClicked(View view, Movie movie) {
            Intent detailIntent = new Intent(context, MovieDetailActivity.class)
                    .putExtra(MOVIE_ITEM, movie);
            context.startActivity(detailIntent);
        }
    }
}
