package com.android.remotecontrolcooler;

/**
 * Created by nchristensen on 7/23/2015.
 */
public class Song {
    private int drawableId;
    private String title;
    private String artist;
    private String album;

    public Song(int drawableId, String title, String artist, String album) {
        super();
        this.drawableId = drawableId;
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
