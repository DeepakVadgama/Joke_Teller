package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.deepakvadgama.jokes.gce.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public static String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;
    private final MainActivityFragment.JokeListener jokeListener;

    public EndpointsAsyncTask(MainActivityFragment.JokeListener jokeListener) {
        this.jokeListener = jokeListener;
    }

    @Override
    protected String doInBackground(Void... params) {


        Log.i(LOG_TAG, "Building GCE service fetcher");
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(false); // Change this later
                        }
                    });

            myApiService = builder.build();
        }

        try {
            Log.i(LOG_TAG, "Calling GCE service fetcher");
            String joke = myApiService.getJoke().execute().getData();
            Log.i(LOG_TAG, "Joke fetched: " + joke);
            return joke;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error trying to fetch joke from server.", e);
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if (jokeListener != null) {
            jokeListener.jokeReceived(joke);
        } else {
            Log.i(LOG_TAG, "Joke received but listener is null");
        }
    }
}