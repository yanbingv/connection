package com.example.student.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.*;
import java.net.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Myclass().execute();



    }

    Bitmap bitmap;
    private  class Myclass extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {

             bitmap = DownloadImage(
                    "http://video.5054399.com/video/upload/1484625442.jpg");

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmap);

        }

        }



    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }



    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;

        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }







}
