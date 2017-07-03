package com.example.rachel.jobboard;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class ParseJson {

    public JSONArray parseJSON(Context context)
    {
        JSONObject json = new JSONObject();

        try {
            json = new JSONObject(loadJSONFromAsset(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jArrayJobs = new JSONArray();

        try {
             jArrayJobs = json.getJSONArray("jobs");
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jArrayJobs;
    }

    private String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
