package com.example.android.popularmoviesapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmoviesapplication.R;
import com.example.android.popularmoviesapplication.databinding.ActivityMovieDetailBinding;
import com.example.android.popularmoviesapplication.model.Movie;

import static com.example.android.popularmoviesapplication.view.MoviesActivity.MOVIE_ITEM;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityMovieDetailBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        initToolbar();

        Intent movieIntent = getIntent();
        if (movieIntent.getExtras() != null) {
            if (movieIntent.hasExtra(MOVIE_ITEM)) {
                movie = movieIntent.getParcelableExtra(MOVIE_ITEM);
            }
        }
        dataBinding.setMovie(movie);
    }

    void initToolbar() {
        Toolbar toolbar = dataBinding.toolbarDetail;
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBackground));
        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_up);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.movie_detail);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
