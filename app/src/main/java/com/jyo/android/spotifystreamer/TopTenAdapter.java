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

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by JohnTangarife on 11/06/15.
 */
public class TopTenAdapter extends ArrayAdapter<Track> {

    private static final String LOG_TAG = TopTenAdapter.class.getSimpleName();

    private List<Track> topTenTracks;
    private Context context;

    public TopTenAdapter(Context context, List<Track> topTenTracks){
        super(context, R.layout.list_item_top_ten, topTenTracks);
        this.context = context;
        this.topTenTracks = topTenTracks;
    }

    public void addTracks(Track track){
        this.topTenTracks.add(track);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return topTenTracks.size();
    }

    @Override
    public Track getItem(int position) {
        return topTenTracks.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.list_item_top_ten, null);
        Track track = getItem(position);

        //Definition
        TextView trackName = (TextView) item.findViewById(R.id.txt_song_name_top_10);
        TextView albumName = (TextView) item.findViewById(R.id.txt_album_name_top_10);
        ImageView imageView = (ImageView) item.findViewById(R.id.img_top_10_thumbnail);

        //Populate
        trackName.setText(track.name);
        albumName.setText(track.album.name);

        if(track.album.images != null && track.album.images.size() > 0){
            Picasso.with(context).load(track.album.images.get(0).url).into(imageView);
        }

        return item;
    }
}