package com.jyo.android.spotifystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by JohnTangarife on 11/06/15.
 */
public class SearchAdapter extends ArrayAdapter<Artist> {

    private static final String LOG_TAG = SearchAdapter.class.getSimpleName();

    private List<Artist> artistResults;
    private Context context;

    public SearchAdapter(Context context, List<Artist> artistsResults){
        super(context, R.layout.list_item_serach, artistsResults);
        this.context = context;
        this.artistResults = artistsResults;
    }

    @Override
    public int getCount() {
        return artistResults.size();
    }

    @Override
    public Artist getItem(int position) {
        return artistResults.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.list_item_serach, null);
        Artist artist = getItem(position);

        TextView textView = (TextView) item.findViewById(R.id.txt_album_name);
        ImageView imageView = (ImageView) item.findViewById(R.id.img_search_thumbnail);
        textView.setText(artist.name);

        if(artist.images != null && artist.images.size() > 0){
            Picasso.with(context).load(artist.images.get(0).url).into(imageView);
        }

        return item;
    }
}