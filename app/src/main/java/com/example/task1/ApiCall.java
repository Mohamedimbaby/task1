package com.example.task1;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiCall implements getRest {
    MutableLiveData<List<item>> Attractions_LiveData;
    MutableLiveData<List<item>> Events_LiveData;
    MutableLiveData<List<item>> Hotspots_LiveData;

    RequestQueue queue;
   static ApiCall apiCall;
    static  ApiCall getInstance(Context context)
    {
        if (apiCall==null)
            apiCall= new ApiCall(context);
        return apiCall;
    }

    private ApiCall(Context context) {
        Attractions_LiveData= new MutableLiveData<>();
        Events_LiveData= new MutableLiveData<>();
        Hotspots_LiveData= new MutableLiveData<>();
        queue= Volley.newRequestQueue(context);
    }

    public MutableLiveData<List<item>> getAttractions_LiveData() {
        return Attractions_LiveData;
    }

    public MutableLiveData<List<item>> getEvents_LiveData() {
        return Events_LiveData;
    }

    public MutableLiveData<List<item>> getHotspots_LiveData() {
        return Hotspots_LiveData;
    }

    @Override
    public void getAttractions() {
        JsonObjectRequest newRequest = new JsonObjectRequest(Request.Method.GET, Api_Link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final ArrayList<item> items = new ArrayList<>();
                    JSONArray results = response.getJSONObject("data").getJSONArray("attractions");
                for (int i = 0; i < results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);
                        JSONArray photos = item.getJSONArray("photos");

                    String o = "";
                    if (photos.length()>0){
                            o = (String) photos.get(0);
                            for (int j = 0; j < o.length(); j++) {
                            if (o.charAt(j)=='\\')
                            {
                                o=charRemoveAt(o,j);
                            }
                        }
                        }
                    Log.i("ppp",item.getString("description"));

                    items.add(new item( item.getString("name"),item.getString("description"),o));

                }
                    Attractions_LiveData.setValue(items);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Attractions_LiveData.setValue(null);
            }
        });
        queue.add(newRequest);
    }


    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
    @Override
    public void getEvents() {
        JsonObjectRequest newRequest = new JsonObjectRequest(Request.Method.GET, Api_Link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final ArrayList<item> items = new ArrayList<>();
                    JSONArray results = response.getJSONObject("data").getJSONArray("events");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);
                        JSONArray photos = item.getJSONArray("photos");

                        String o = "";
                        if (photos.length()>0){
                            o = (String) photos.get(0);
                            for (int j = 0; j < o.length(); j++) {
                                if (o.charAt(j)=='\\')
                                {
                                    o=charRemoveAt(o,j);
                                }
                            }
                        }
                        items.add(new item( item.getString("name"),item.getString("description"),o));

                    }
                    Events_LiveData.setValue(items);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Events_LiveData.setValue(null);
            }
        });
        queue.add(newRequest);

    }

    @Override
    public void getHotspots() {
        JsonObjectRequest newRequest = new JsonObjectRequest(Request.Method.GET, Api_Link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final ArrayList<item> items = new ArrayList<>();
                    JSONArray results = response.getJSONObject("data").getJSONArray("hot_spots");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);
                        JSONArray photos = item.getJSONArray("photos");

                        String o = "";
                        if (photos.length()>0){
                            o = (String) photos.get(0);
                            for (int j = 0; j < o.length(); j++) {
                                if (o.charAt(j)=='\\')
                                {
                                    o=charRemoveAt(o,j);
                                }
                            }
                        }
                        items.add(new item( item.getString("name"),item.getString("description"),o));

                    }
                    Hotspots_LiveData.setValue(items);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Hotspots_LiveData.setValue(null);
            }
        });
        queue.add(newRequest);

    }
}
