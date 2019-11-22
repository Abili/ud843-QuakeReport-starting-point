package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.MyViewHolder> {

    private ArrayList<Earthquake> infoArrayAdapter;
    Context context;

    public EarthquakeAdapter(ArrayList<Earthquake> infoArrayAdapter, Context context) {
        this.infoArrayAdapter = infoArrayAdapter;
        this.context = context;
    }

    @NonNull
    @Override
    public EarthquakeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.MyViewHolder holder, int position) {

        holder.magnitude.setText(infoArrayAdapter.get(position).getMagnitude());
        holder.place.setText(infoArrayAdapter.get(position).getPlace());
        holder.distance.setText(infoArrayAdapter.get(position).getDiatance());

        Date dateObject = new Date(infoArrayAdapter.get(position).getTimeInMilliseconds());

        String formattedTime = formatTime(dateObject);
        String formattedDate = formatDate(dateObject);

        holder.date.setText(formattedDate);
        holder.time.setText(formattedTime);
    }


    @Override
    public int getItemCount() {
        return infoArrayAdapter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.magnitude)
        TextView magnitude;

        @BindView(R.id.place)
        TextView place;

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.distance)
        TextView distance;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}