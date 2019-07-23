package com.example.android.popularmoviesapplication.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CustomBindingAdapter {

    private static final String imgUrl = "https://image.tmdb.org/t/p/w185/";

    @BindingAdapter("imageUrl")
    public static void setNetworkImageUrl(ImageView imageView, String url) {
        if (url != null)
            Glide.with(imageView.getContext())
                    .load(imgUrl + url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

    }

    @BindingAdapter("ratings")
    public static void setRatings(RatingBar ratingBar, Double val) {
        if (val != 0.0) {
            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.setRating(val.floatValue() / 2);
        } else
            ratingBar.setVisibility(View.INVISIBLE);

    }
}
