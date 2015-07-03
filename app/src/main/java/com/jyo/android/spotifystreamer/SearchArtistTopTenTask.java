package com.jyo.android.spotifystreamer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Tracks;

/**
 * Created by JohnTangarife on 25/06/15.
 */
public class SearchArtistTopTenTask extends AsyncTask<String, Void, Tracks> {

    private final String LOG_TAG = SearchAdapter.class.getCanonicalName();
    private TopTenAdapter topTenAdapter;
    private Context context;

    public SearchArtistTopTenTask(TopTenAdapter topTenAdapter, Context context) {
        this.topTenAdapter = topTenAdapter;
        this.context = context;
    }

    @Override
    protected Tracks doInBackground(String... params) {

        //Use of spotify wrapper
        try {

            SpotifyApi api = new SpotifyApi();
            SpotifyService spotifyService = api.getService();
            Map<String, Object> options = new HashMap<>();

            //Top ten tracks on Selected Country
            options.put("country", params[1]);

            return spotifyService.getArtistTopTrack(params[0], options);

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Tracks tracks) {
        //Obtaining artists
        topTenAdapter.clear();

        if(0 == tracks.tracks.size()){
            CharSequence text = "No top ten Tracks for this Artist. (Sorry)";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            topTenAdapter.addAll(tracks.tracks);
        }
    }
}
