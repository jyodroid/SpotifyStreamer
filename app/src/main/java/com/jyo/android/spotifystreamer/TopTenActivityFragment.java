package com.jyo.android.spotifystreamer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;


/**
 * A placeholder fragment containing a simple view.
 */
public class TopTenActivityFragment extends Fragment {

    public TopTenActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        ((ActionBarActivity) getActivity()).getSupportActionBar()
                .setSubtitle(intent.getStringExtra(getString(R.string.selected_artist_name)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top_ten, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_top_ten);

        Context context = getActivity().getBaseContext();
        TopTenAdapter topTenAdapter = new TopTenAdapter(context, new ArrayList<Track>());

        listView.setAdapter(topTenAdapter);

        SearchArtistTopTenTask searchTracks = new SearchArtistTopTenTask(topTenAdapter, context);
        Intent intent = getActivity().getIntent();
        searchTracks.execute(intent.getStringExtra(getString(R.string.selected_artist_id)), "US");

        return rootView;
    }
}
