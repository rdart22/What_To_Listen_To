package com.example.mp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private String artistName;
    private int artistID;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        final Button albumButton = findViewById(R.id.album_button);
        albumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artistName = ((EditText) findViewById(R.id.artist_name)).getText().toString();
                Tasks.artistSearchRequest(artistName);
                JSONObject request = Tasks.myResponse;
                artistID = MusicInfo.getArtistID(request);
                String idString = "" + artistID;
                Log.d("ID: ", idString);
                Tasks.albumRequest(artistID);
                processTextAlbum(Tasks.albumResponse);
            }
        });
        final Button relatedArtistButton = findViewById(R.id.related_button);
        relatedArtistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artistName = ((EditText) findViewById(R.id.artist_name)).getText().toString();
                Tasks.artistSearchRequest(artistName);
                JSONObject request = Tasks.myResponse;
                artistID = MusicInfo.getArtistID(request);
                String idString = "" + artistID;
                Log.d("ID: ", idString);
                Tasks.relatedRequest(artistID);
                processTextRelatedArtist(Tasks.relatedResponse);
            }
        });
    }
    public void processTextAlbum(final JSONObject jsonResult) {
        TextView output = findViewById(R.id.output_text);
        if (MusicInfo.getAlbums(jsonResult) == null) {
            output.setText("Sorry that artist does not exist");
            return;
        }
        int randomAlbum = new Random().nextInt(MusicInfo.getAlbums(jsonResult).size());
        String outputString = MusicInfo.getAlbums(jsonResult).get(randomAlbum);
        output.setText(outputString);
    }
    public void processTextRelatedArtist (final JSONObject jsonResult) {
        TextView output = findViewById(R.id.output_text);
        if (MusicInfo.getRelatedArtists(jsonResult) == null) {
            output.setText("Sorry that artist does not exist");
            return;
        }
        int randomArtist = new Random().nextInt(MusicInfo.getRelatedArtists(jsonResult).size());
        String outputString = MusicInfo.getRelatedArtists(jsonResult).get(randomArtist);
        output.setText(outputString);
    }
}
