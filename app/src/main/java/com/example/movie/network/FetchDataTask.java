package com.example.movie.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FetchDataTask extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    public FetchDataTask(AsyncResponse asyncResponse){
        delegate = asyncResponse;
    }

    @Override
    protected String doInBackground(String... params){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                return null;
            }
            return buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e){
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String s){
        delegate.processFinish(s);
    }
}


