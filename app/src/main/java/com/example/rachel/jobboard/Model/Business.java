package com.example.rachel.jobboard.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Business {

    public String businessId;
    public String thumbnail;
    public Boolean isHired;

    public Business(JSONObject business)
    {
        try {
            this.businessId = business.getString("businessId");
            this.thumbnail = business.getString("thumbnail");
            this.isHired = business.getBoolean("isHired");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
