package com.android.jh.yueke.view;

//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.os.AsyncTask;
//import android.text.Html;
//import android.view.View;
//
//import com.loopj.android.http.HttpGet;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//
//import cz.msebera.android.httpclient.HttpResponse;
//import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
//
///**
// * Created by Relcon on 2015/11/20.
// */
//public class URLImageParser implements Html.ImageGetter {
//    Context c;
//    View container;
//
//    InputStream inputStream = null;
//    /***
//     * Construct the URLImageParser which will execute AsyncTask and refresh the container
//     *
//     * @param t
//     * @param c
//     */
//    public URLImageParser(View t, Context c) {
//        this.c = c;
//        this.container = t;
//    }
//
//    @Override
//    public Drawable getDrawable(String source) {
//        URLDrawable urlDrawable = new URLDrawable();
//
//        // get the actual source
//        ImageGetterAsyncTask asyncTask =
//                new ImageGetterAsyncTask(urlDrawable);
//
//        asyncTask.execute(source);
//
//        // return reference to URLDrawable where I will change with actual image from
//        // the src tag
//        return urlDrawable;
//    }
//
//    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
//        URLDrawable urlDrawable;
//
//        public ImageGetterAsyncTask(URLDrawable d) {
//            this.urlDrawable = d;
//        }
//
//        @Override
//        protected Drawable doInBackground(String... params) {
//            String source = params[0];
//
//            return fetchDrawable(source);
//        }
//
//        @Override
//        protected void onPostExecute(Drawable result) {
//            // set the correct bound according to the result from HTTP call
//
//            urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth(), 0
//                    + result.getIntrinsicHeight());
//
//            // change the reference of the current drawable to the result
//            // from the HTTP call
//            urlDrawable.drawable = result;
//
//            // redraw the image by invalidating the container
//            URLImageParser.this.container.invalidate();
//        }
//
//        /***
//         * Get the Drawable from URL
//         *
//         * @param urlString
//         * @return
//         */
//        public Drawable fetchDrawable(String urlString) {
//            try {
//                InputStream is = fetch(urlString);
//                Drawable drawable = Drawable.createFromStream(is, "src");
//                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0
//                        + drawable.getIntrinsicHeight());
//                return drawable;
//            } catch (Exception e) {
//                return null;
//            }
//        }
//
//        private InputStream fetch(String urlString) throws MalformedURLException, IOException {
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet request = new HttpGet(urlString);
//            HttpResponse response = httpClient.execute(request);
//            return response.getEntity().getContent();
//
//
//
////            AsyncHttpClient client = new AsyncHttpClient();
////            client.get(c, urlString, new DataAsyncHttpResponseHandler() {
////                @Override
////                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
////                    inputStream =new ByteArrayInputStream(responseBody);
////
////                }
////
////                @Override
////                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
////
////                }
////            });
////            return inputStream;
//        }
//    }
//}

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.Base64;
import android.view.View;

import java.io.InputStream;
import java.net.URL;

public class URLImageParser implements ImageGetter {
    Context context;
    View container;

    public URLImageParser(View container, Context context) {
        this.context = context;
        this.container = container;
    }

    public Drawable getDrawable(String source) {
        if(source.matches("data:image.*base64.*")) {
            String base_64_source = source.replaceAll("data:image.*base64", "");
            byte[] data = Base64.decode(base_64_source, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable image = new BitmapDrawable(context.getResources(), bitmap);
            image.setBounds(0, 0, 0 + image.getIntrinsicWidth(), 0 + image.getIntrinsicHeight());
            return image;
        } else {
            URLDrawable urlDrawable = new URLDrawable();
            ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);
            asyncTask.execute(source);
            return urlDrawable; //return reference to URLDrawable where We will change with actual image from the src tag
        }
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth(), 0 + result.getIntrinsicHeight()); //set the correct bound according to the result from HTTP call
            urlDrawable.drawable = result; //change the reference of the current drawable to the result from the HTTP call
            URLImageParser.this.container.invalidate(); //redraw the image by invalidating the container
        }

        public Drawable fetchDrawable(String urlString) {
            try {
                InputStream is = (InputStream) new URL(urlString).getContent();
                Drawable drawable = Drawable.createFromStream(is, "src");
                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0 + drawable.getIntrinsicHeight());
                return drawable;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
