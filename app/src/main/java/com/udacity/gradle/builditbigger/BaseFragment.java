package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.deepakvadgama.jokesandroid.JokeActivity;


public abstract class BaseFragment extends Fragment {

    public String LOG_TAG = BaseFragment.class.getSimpleName();
    protected ProgressBar mProgressBar;

    protected class MyJokeListener implements EndpointsAsyncTask.JokeListener {

        public void jokeReceived(String joke) {

            if (mProgressBar != null) {
                mProgressBar.setVisibility(ProgressBar.GONE);
            }

            if (joke != null && !joke.isEmpty()) {
                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, joke);
                startActivity(intent);
            } else {
                Log.e(LOG_TAG, "Empty joke received");
            }
        }
    }

    public void tellJoke(View view) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }
        // Fetch joke in background and display using JokeListener
        new EndpointsAsyncTask(new MyJokeListener()).execute();
    }
}
