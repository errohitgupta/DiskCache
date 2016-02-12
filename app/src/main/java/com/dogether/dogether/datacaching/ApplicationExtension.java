package com.dogether.dogether.datacaching;

import android.app.Application;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.dogether.dogether.datacaching.models.MovieDetails;
import com.dogether.dogether.datacaching.utils.MovieExtractor;
import com.google.gson.Gson;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

/**
 * Created by dogether on 9/2/16.
 */
public class ApplicationExtension extends Application {


    public static MovieDetails mMovieDetails;
    private MovieExtractor movieExtractor;
    public static final String TAG = ApplicationExtension.class.getSimpleName();
    public static Picasso mCustomPicasso;

    @Override
    public void onCreate() {
        super.onCreate();
        movieExtractor =  MovieExtractor.init(getApplicationContext());
        final File file = new File(Environment.getExternalStorageDirectory() ,".dogether");
        if (!file.exists()) {
            file.mkdirs();
            Log.d("Respose",file.getPath());

        }
        mCustomPicasso = new Picasso.Builder(getApplicationContext())
                .downloader(new OkHttpDownloader(file))
                .build();
    }

    public MovieExtractor getMovieExtractor(){
        return movieExtractor;
    }

    public static void setMovieDetails(MovieDetails object){
        mMovieDetails = object;
        Log.d("Response",mMovieDetails.getTitle());
        Log.d("Response",mMovieDetails.getYear());
    }

    public static MovieDetails getMovieDetails(){
        return mMovieDetails;
    }

    public static boolean checkMovieDetails(){
        boolean availability = false;
        if(mMovieDetails != null){

            availability = true;
        }
        return availability;
    }
}