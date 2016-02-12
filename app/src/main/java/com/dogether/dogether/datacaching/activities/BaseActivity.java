package com.dogether.dogether.datacaching.activities;

import android.support.v7.app.AppCompatActivity;

import com.dogether.dogether.datacaching.ApplicationExtension;
import com.dogether.dogether.datacaching.utils.MovieExtractor;

/**
 * Created by dogether on 10/2/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected MovieExtractor getMovieExtractor() {
        return ((ApplicationExtension)getApplication()).getMovieExtractor();
    }
}
