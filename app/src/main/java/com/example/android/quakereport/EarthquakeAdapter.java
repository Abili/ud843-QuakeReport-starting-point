package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

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
    public void onBindViewHolder(@NonNull EarthquakeAdapter.MyViewHolder holder, final int position) {

        String formattedMagnitude = formatMagnitude(infoArrayAdapter.get(position).getMagnitude());
        holder.magnitude.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) holder.magnitude.getBackground();


        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(infoArrayAdapter.get(position).getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        holder.place.setText(infoArrayAdapter.get(position).getPlace());
        //holder.distance.setText(infoArrayAdapter.get(position).getDiatance());

        Date dateObject = new Date(infoArrayAdapter.get(position).getTimeInMilliseconds());

        String formattedTime = formatTime(dateObject);
        String formattedDate = formatDate(dateObject);

        holder.date.setText(formattedDate);
        holder.time.setText(formattedTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = infoArrayAdapter.get(position).getUrl();
                Intent intentUrl = new Intent(Intent.ACTION_VIEW);
                intentUrl.setData(Uri.parse(url));
                context.startActivity(intentUrl);
            }
        });
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

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId);
    }

}