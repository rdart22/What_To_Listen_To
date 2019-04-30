package com.example.mp5;

import android.util.Log;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;
//
public class MusicInfo {
    public static List<String> getAlbums(final JSONObject json) {
        if (json == null) {
            return null;
        }
        List<String> albumList = new ArrayList<>();
        try {
            JSONArray resultArray = json.getJSONObject("body").getJSONArray("album_list");
            for (int i = 0; i < resultArray.length(); i++) {
                if (resultArray.getJSONObject(i) == null) {
                    continue;
                }
                albumList.add(resultArray.getJSONObject(i).getString("album_name"));
            }
        } catch (JSONException error) {
            Log.d("error", "music info error");
        }
        return albumList;
    }
    public static List<String> getRelatedArtists(final JSONObject json) {
        if (json == null) {
            return null;
        }
        List<String> relatedArtists = new ArrayList<>();
        try {
            JSONArray resultArray = json.getJSONObject("body").getJSONArray("artist_list");
            for (int i = 0; i < resultArray.length(); i++) {
                if (resultArray.getJSONObject(i) == null) {
                    continue;
                }
                relatedArtists.add(resultArray.getJSONObject(i).getString("artist_name"));
            }
        } catch (JSONException error) {
            Log.d("Error", "music info error");
        }
        return relatedArtists;
    }
    public static int getArtistID(final JSONObject json) {
        if (json == null) {
            return 0;
        }
        int ID = 0;
        try {
           ID = json.getJSONObject("body").getJSONArray("artist_list").getJSONObject(0).getInt("artist_id");
        } catch (JSONException error) {
            Log.d("error", "artist ID error");
        }
        return ID;
    }
}
