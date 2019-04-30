package com.example.mp5;

import org.json.JSONObject;

public class temp {
    List<String> albums = MusicInfo.getAlbums(new JSONObject());
    List<String> artist = MusicInfo.getRelatedArtist(new JSONObject());
    int id = MusicInfo.getArtistID(new JSONObject());
}
