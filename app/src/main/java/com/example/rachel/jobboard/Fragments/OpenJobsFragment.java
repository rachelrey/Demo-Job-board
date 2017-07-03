package com.example.rachel.jobboard.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.rachel.jobboard.ParseJson;
import com.example.rachel.jobboard.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenJobsFragment extends Fragment{

    View openJobsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        openJobsFragment = inflater.inflate(R.layout.open_jobs_fragment, container, false);

        LinearLayout viewContainer = openJobsFragment.findViewById(R.id.tab_open_jobs);
        this.loadContent(viewContainer);

        return openJobsFragment;
    }

    private void loadContent(LinearLayout mainView)
    {
        JSONArray jobs = this.getJobs();

        for (int i = 0; i < jobs.length(); i++){
            try {
                this.addJobFrame(jobs.getJSONObject(i), mainView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addJobFrame(JSONObject job, LinearLayout mainView)
    {

        try {
            int contentId = Integer.valueOf(job.getString("jobId"));
            FrameLayout frame = new FrameLayout(getContext());
            frame.setId(contentId);
            mainView.addView(frame, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

            JobFragment jobFragment = new JobFragment();

            Bundle bundle = new Bundle();
            bundle.putString("job", job.toString());
            jobFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction().add(contentId, jobFragment).addToBackStack(null).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray getJobs()
    {
        ParseJson parseJsonClass = new ParseJson();
        return parseJsonClass.parseJSON(getContext());
    }
}
