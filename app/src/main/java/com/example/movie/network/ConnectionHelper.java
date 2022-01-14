package com.example.movie.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.movie.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHelper {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL(context.getString(R.string.google_url));
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestProperty("User-Agent", "test");
                httpURLConnection.setRequestProperty("Connection", "close");
                httpURLConnection.setConnectTimeout(1000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public static Uri getMovieSearchApiUrl(String searchKey){
        return new Uri.Builder()
                .scheme("http")
                .authority("www.omdbapi.com")
                .appendQueryParameter("s", searchKey)
                .appendQueryParameter("r","json")
                .build();
    }

    public static Uri getMovieDetailByImdbIDApiUrl(String imdbId){
        return new Uri.Builder()
                .scheme("http")
                .authority("www.omdbapi.com")
                .appendQueryParameter("i", imdbId)
                .appendQueryParameter("r","json")
                .build();
    }
}
