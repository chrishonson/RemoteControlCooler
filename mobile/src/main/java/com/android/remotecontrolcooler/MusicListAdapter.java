package com.android.remotecontrolcooler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nchristensen on 7/23/2015.
 */
public class MusicListAdapter extends BaseAdapter {
    Context context;

    protected List<Song> listCars;
    LayoutInflater inflater;

    public MusicListAdapter(Context context, List<Song> listCars) {
        this.listCars = listCars;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return listCars.size();
    }

    public Song getItem(int position) {
        return listCars.get(position);
    }

    public long getItemId(int position) {
        return listCars.get(position).getDrawableId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_list_item,
                    parent, false);

            holder.txtTitle = (TextView) convertView
                    .findViewById(R.id.txt_song_title);
            holder.txtArtist = (TextView) convertView
                    .findViewById(R.id.txt_song_artist);
            holder.txtAlbum = (TextView) convertView
                    .findViewById(R.id.txt_song_album);
            holder.imgSong = (ImageView) convertView.findViewById(R.id.img_song);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Song song = listCars.get(position);
        holder.txtTitle.setText(song.getTitle());
        holder.txtArtist.setText(song.getArtist());
        holder.txtAlbum.setText(song.getAlbum());
        holder.imgSong.setImageResource(song.getDrawableId());

        return convertView;
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtArtist;
        TextView txtAlbum;
        ImageView imgSong;
    }
}
