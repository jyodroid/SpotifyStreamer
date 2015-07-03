package com.jyo.android.spotifystreamer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchActivityFragment extends Fragment {

    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    private SearchAdapter searchAdapter;
    private String artistName;

    public SearchActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context context = getActivity().getBaseContext();

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_search);

        searchAdapter = new SearchAdapter(context, new ArrayList<Artist>());

        listView.setAdapter(searchAdapter);

        final EditText editText = (EditText) rootView.findViewById(R.id.txt_artist_name);
        artistName = editText.getText().toString();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                    if(null != v.getText()  && 0 != v.getText().length()){
                        artistName = v.getText().toString();
                        updateArtist(artistName, context);
                    }
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedArtistName = searchAdapter.getItem(position).name;
                String selectedArtistId = searchAdapter.getItem(position).id;
                Intent topTenActivityIntent = new Intent(context, TopTenActivity.class);
                topTenActivityIntent.putExtra(getString(R.string.selected_artist_name), selectedArtistName);
                topTenActivityIntent.putExtra(getString(R.string.selected_artist_id), selectedArtistId);
                startActivity(topTenActivityIntent);
            }
        });
        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(artistName.length() > 0){
            updateArtist(artistName, getActivity());
        }
    }

    private void updateArtist(String artistName, Context context){
        SearchArtistTask fetchArtistTask = new SearchArtistTask(searchAdapter, context);
        fetchArtistTask.execute(artistName);
    }
}
