package com.example.mp5;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class Tasks {
    public static final String baseURL = "https://api.musixmatch.com/ws/1.1/";
    public static final String API_Key = "apikey=7aa563d660b5c8874dab10d27174f3c4";
    public static JSONObject myResponse;
    public static JSONObject albumResponse;
    public static JSONObject relatedResponse;
    public static RequestQueue requestQueue;
    public static void artistSearchRequest(String artist) {
        String URL = baseURL + "artist.search?" + API_Key + "&q_artist=" + artist;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d("response: ", response.toString());
                myResponse = response;
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "VolleyError");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public static void albumRequest(int artistID) {
        String URL = baseURL + "artist.albums.get?" + API_Key + "&artist_id=" + artistID;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        albumResponse = response;
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "VolleyError");
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    public static void relatedRequest(int artistID) {
        String artistIDString = "" + 1039;
        String URL = baseURL + "artist.related.get?" + API_Key + "&artist_id=" + artistIDString;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        relatedResponse = response;
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "VolleyError");
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
