package com.jyo.android.spotifystreamer;

import android.os.AsyncTask;
import android.util.Log;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * Created by JohnTangarife on 25/06/15.
 */
public class SearchArtistTask extends AsyncTask<String, Void, ArtistsPager> {

    private final String LOG_TAG = SearchAdapter.class.getCanonicalName();
    private SearchAdapter searchAdapter;

    public SearchArtistTask(SearchAdapter searchAdapter) {
        this.searchAdapter = searchAdapter;
    }

    @Override
    protected ArtistsPager doInBackground(String... params) {

        //Use of spotify wrapper
        try {

            SpotifyApi api = new SpotifyApi();
            SpotifyService spotifyService = api.getService();

            return spotifyService.searchArtists(params[0]);

        }catch (Exception e){
            Log.e(LOG_TAG, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArtistsPager result) {
        //Obtaining artists
        searchAdapter.clear();
        searchAdapter.addAll(result.artists.items);
    }
}
