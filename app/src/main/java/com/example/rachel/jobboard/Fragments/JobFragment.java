package com.example.rachel.jobboard.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.rachel.jobboard.Model.Business;
import com.example.rachel.jobboard.Adaptors.GridViewAdaptor;
import com.example.rachel.jobboard.Model.Job;
import com.example.rachel.jobboard.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class JobFragment extends Fragment {
    TextView job_category_text;
    TextView job_posted_time;
    TextView job_status;
    TextView job_hired_count;
    GridView image_container;
    Button view_details;
    ImageButton closeFragment;
    View jobFragment;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        jobFragment = inflater.inflate(R.layout.fragment_job, container, false);
        job_category_text = jobFragment.findViewById(R.id.category_job);
        job_posted_time = jobFragment.findViewById(R.id.time_posted);
        job_status = jobFragment.findViewById(R.id.text_status);
        job_hired_count = jobFragment.findViewById(R.id.hired_count);
        image_container = jobFragment.findViewById(R.id.image_container);
        view_details = jobFragment.findViewById(R.id.view_details);
        closeFragment = jobFragment.findViewById(R.id.close_item);
        return jobFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            try {
                JSONObject jobJson = new JSONObject(bundle.getString("job"));
                Job job = new Job(jobJson);
                this.addJob(job);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void addJob(final Job job){

        this.job_category_text.setText(job.category);
        this.job_posted_time.setText(changeDateFormat(job.postedDate));
        this.job_status.setText(job.status);
        this.job_hired_count.setText(this.getHiredCountText(job.getBusinessCount()));
        this.setBusinessConnected(job.connectedBusinesses);
        view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = job.detailsLink;

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.google.com.au" + url));
                startActivity(i);
            }
        });

        closeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity().getSupportFragmentManager().findFragmentById(Integer.valueOf(job.jobId)) != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().
                            remove(getActivity().getSupportFragmentManager().findFragmentById(Integer.valueOf(job.jobId))).commit();
                }
            }
        });
    }

    private String getHiredCountText(int hiredCount)
    {
        if (hiredCount == 0) {
            return "Connecting you with businesses";
        }
        if(hiredCount == 1){
            return String.format("You have hired %d business", hiredCount);
        }
        else {
            return String.format("You have hired %d businesses", hiredCount);
        }
    }

    private void setBusinessConnected(ArrayList<Business> businesses)
    {
        if (businesses.size() > 0) {
            GridView imageContainer = jobFragment.findViewById(R.id.image_container);
            GridViewAdaptor adaptor = new GridViewAdaptor(getContext(), businesses);
            imageContainer.setAdapter(adaptor);
            adaptor.notifyDataSetChanged();
        }
    }

    private String changeDateFormat(String date){
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        Date mDate = new Date();
        try {
            mDate = originalFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return targetFormat.format(mDate);
    }
}
