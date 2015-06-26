package com.jyo.android.spotifystreamer;

import android.app.Fragment;
import android.content.Context;
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

    public SearchActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getActivity().getBaseContext();

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_search);

        searchAdapter = new SearchAdapter(context, new ArrayList<Artist>());

        listView.setAdapter(searchAdapter);
        final EditText editText = (EditText) rootView.findViewById(R.id.txt_artist_name);
        updateArtist(editText.getText().toString());

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_GO ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    updateArtist(v.getText().toString());
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Context context = getActivity().getBaseContext();
//                //Text to be show
//                CharSequence text = adapter.getItem(position);
//                int duration = Toast.LENGTH_LONG;
//
//                Toast toast = Toast.makeText(context,text,duration);
//                toast.show();
//
//                Intent detailActivityIntent = new Intent(context, DetailActivity.class);
//                detailActivityIntent.putExtra(Intent.EXTRA_TEXT,text);
//                startActivity(detailActivityIntent);
            }
        });
        return rootView;
    }

    private void updateArtist(String artistName){
        SearchArtistTask fetchArtistTask = new SearchArtistTask(searchAdapter);
        fetchArtistTask.execute(artistName);
    }
}
