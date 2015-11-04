package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.deepakvadgama.jokesandroid.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        final Button loginButton = (Button) root.findViewById(R.id.tellJokeButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                tellJoke(v);
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public class MyJokeListener implements EndpointsAsyncTask.JokeListener {

        public void jokeReceived(String joke) {

            if (joke != null && !joke.isEmpty()) {
                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, joke);
                startActivity(intent);
            } else {
                Log.e(LOG_TAG, "Empty joke received");
            }
        }
    }

    public void tellJoke(View view){

        // Fetch joke in background and display using JokeListener
        new EndpointsAsyncTask(new MyJokeListener()).execute();
        Toast.makeText(getActivity(), "Hold on tight.. joke upcoming", Toast.LENGTH_SHORT).show();
    }
}

