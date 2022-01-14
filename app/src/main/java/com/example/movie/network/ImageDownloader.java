package com.example.movie.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;



public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    int width,height;

    public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
        this.width = -1;
        this.height = -1;
    }

    public ImageDownloader(ImageView bmImage, int width, int height) {
        this.bmImage = bmImage;
        this.width = width;
        this.height = height;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
            if(width!=-1 && height!=-1){
                mIcon = Bitmap.createScaledBitmap(mIcon, height, width, true);;
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return mIcon;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
