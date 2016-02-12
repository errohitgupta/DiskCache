package com.dogether.dogether.datacaching.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.bumptech.glide.Glide;
import com.dogether.dogether.datacaching.ApplicationExtension;
import com.dogether.dogether.datacaching.R;
import com.dogether.dogether.datacaching.models.MovieDetails;
import com.dogether.dogether.datacaching.utils.MovieExtractor;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private EditText mInputName,mInputYear;
    private Button mSearchButton;
    private Toolbar mToolbar;
    String mMovieName, mMovieYear;
    private TextView mMovieNameText, mMovieYearText, mCameFrom;
    private ImageView mPicassoImageView, mGlideImageView;
    private RelativeLayout mMovieDetailsFrame;
    private boolean mDataFetched;
    private MovieDetails mMovieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        setUpSearchAction();
        setSupportActionBar(mToolbar);

    }

    private void initLayout(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mInputName = (EditText)findViewById(R.id.movie_name);
        mInputYear = (EditText)findViewById(R.id.movie_year);
        mSearchButton = (Button) findViewById(R.id.search_button);
        mMovieNameText = (TextView) findViewById(R.id.movie_name_text);
        mMovieYearText = (TextView) findViewById(R.id.movie_year_text);
        mCameFrom = (TextView) findViewById(R.id.came_from_text);
        mPicassoImageView = (ImageView) findViewById(R.id.picasso_image_view);
        mGlideImageView = (ImageView) findViewById(R.id.glide_image_view);
        mMovieDetailsFrame = (RelativeLayout) findViewById(R.id.movie_details_frame);
    }

    private void setUpSearchAction(){
        mInputName.addTextChangedListener(myTextWatcher);
        mInputYear.addTextChangedListener(myTextWatcher);
        mSearchButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mMovieYear!=null){
                Log.d("Response",mMovieName);
                Log.d("Response",mMovieYear);
                mDataFetched = getMovieExtractor().makeServerCall(mMovieName,mMovieYear);
                Log.d("Response","Data Fetching Status" + mDataFetched+"");
            }
            else{
                mDataFetched = getMovieExtractor().makeServerCall(mMovieName,null);
                Log.d("Response","Data Fetching Status" + mDataFetched+"");
            }

            if(mDataFetched){
                setMovieInformation(null);
            }
        }
    };

    private TextWatcher myTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mMovieName = mInputName.getText().toString();
            if(!mInputYear.getText().toString().isEmpty())
                mMovieYear = mInputYear.getText().toString();
        }
    };

    private void setMovieInformation(JSONObject object){
        String CameFrom;
        if(ApplicationExtension.checkMovieDetails() && object == null){
            mMovieDetails = ApplicationExtension.getMovieDetails();
            CameFrom = "Server";
        }
        else{
            Gson gson = new Gson();
            mMovieDetails = gson.fromJson(object.toString(),MovieDetails.class);
            CameFrom = "Disk Cache";
        }
        mMovieDetailsFrame.setVisibility(View.VISIBLE);
        mMovieNameText.setText(mMovieDetails.getTitle());
        mMovieYearText.setText(mMovieDetails.getYear());
        mCameFrom.setText(CameFrom);

        ApplicationExtension.mCustomPicasso.with(MainActivity.this)
                .load(mMovieDetails.getPoster())
                .placeholder(R.drawable.placeholder)
                .into(mPicassoImageView);
        Glide.with(MainActivity.this)
                .load(mMovieDetails.getPoster())
                .placeholder(R.drawable.placeholder)
                .into(mGlideImageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(Reservoir.contains(MovieExtractor.TAG)){
                Reservoir.getAsync(MovieExtractor.TAG,JSONObject.class, new ReservoirGetCallback<JSONObject>() {

                    @Override
                    public void onSuccess(JSONObject object) {
                        Log.d(TAG, "Reservoir Data Fetching Success");
                        setMovieInformation(object);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "Reservoir Data Fetching Failure");

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
