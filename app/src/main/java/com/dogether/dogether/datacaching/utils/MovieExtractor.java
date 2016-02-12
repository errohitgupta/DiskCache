package com.dogether.dogether.datacaching.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.dogether.dogether.datacaching.ApplicationExtension;
import com.dogether.dogether.datacaching.models.MovieDetails;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by dogether on 10/2/16.
 */
public class MovieExtractor {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private String BASE_URL="http://www.omdbapi.com/?t=";
    private String YEAR="&y=";
    private String END_URL ="&plot=full&r=json";
    private boolean dataFetched=false;
    public static final String TAG = MovieExtractor.class.getSimpleName();

    public static MovieExtractor init(Context context) {
        return new MovieExtractor(context);
    }

    public MovieExtractor (Context context){
        mContext = context;
        try {
            Reservoir.init(mContext, 2048); //in bytes
        } catch (Exception e) {
            //failure
        }
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public boolean makeServerCall(String movieName,String movieYear){
        String year;
        if(movieYear!=null){
            year=YEAR+movieYear;
            Log.d("Response",year);
        }
        else{
            year = YEAR;
            Log.d("Response",year);
        }
        String jsonRequestURL = BASE_URL+movieName+year+END_URL;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, jsonRequestURL, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        MovieDetails movieDetails;
                        dataFetched = true;
                        Log.d("Response: ", response.toString());
                        Gson gson = new Gson();
                        movieDetails = gson.fromJson(response.toString(),MovieDetails.class);
                        ApplicationExtension.setMovieDetails(movieDetails);
                        Reservoir.putAsync(TAG, response, new ReservoirPutCallback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Reservoir", "Put Success");
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.d("Reservoir", "Put Failed");
                            }
                        });
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("Response: ", error.toString());
                    }
                });
        addToRequestQueue(jsObjRequest);
        Log.d("Response",dataFetched+"");
        return dataFetched;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        mRequestQueue.add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        mRequestQueue.add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
/*
    @Override
    public void onSuccess() {
        Log.d("Reservoir", "Put Success");

    }

    @Override
    public void onFailure(Exception e) {
        Log.d("Reservoir", "Put Failed");
    }*/

}
