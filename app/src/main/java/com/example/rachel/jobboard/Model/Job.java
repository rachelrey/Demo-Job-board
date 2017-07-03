package com.example.rachel.jobboard.Model;

import com.example.rachel.jobboard.Model.Business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

public class Job implements Serializable{

    public String jobId;
    public String category;
    public String postedDate;
    public String status;
    public String detailsLink;
    public ArrayList<Business> connectedBusinesses = new ArrayList<Business>();

    public Job(JSONObject job){
        try {
            this.jobId = job.getString("jobId");
            this.category = job.getString("category");
            this.postedDate = job.getString("postedDate");
            this.status = job.getString("status");
            this.detailsLink = job.getString("detailsLink");
            this.createBusiness(job.getJSONArray("connectedBusinesses"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getBusinessCount()
    {
        return this.connectedBusinesses.isEmpty() ? 0 : this.connectedBusinesses.size();
    }

    private void createBusiness(JSONArray businesses)
    {
        if(businesses.length() > 0){
            for (int i=0; i < businesses.length(); i++)
            {
                try {
                    Business bus = new Business(businesses.getJSONObject(i));
                    this.connectedBusinesses.add(bus);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
