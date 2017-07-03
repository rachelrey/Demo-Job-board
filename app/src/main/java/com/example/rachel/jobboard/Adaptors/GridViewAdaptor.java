package com.example.rachel.jobboard.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rachel.jobboard.Model.Business;
import com.example.rachel.jobboard.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class GridViewAdaptor extends BaseAdapter{

    private final Context context;
    private  final ArrayList<Business> businesses;

    public GridViewAdaptor(Context context, ArrayList<Business> businesses){

        this.context = context;
        this.businesses = businesses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.image_holder, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.mImage);
        final TextView nameTextView = convertView.findViewById(R.id.mText);

        String imageUrl = getItem(position).thumbnail;
        Picasso.with(context).load(imageUrl).into(imageView);

        if (businesses.get(position).isHired) {
            nameTextView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
    @Override
    public int getCount() {
        return businesses.size();
    }

    @Override
    public Business getItem(int i) {
        return businesses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}

