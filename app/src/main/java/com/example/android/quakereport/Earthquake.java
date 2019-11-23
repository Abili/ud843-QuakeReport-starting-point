package com.example.android.quakereport;

public class Earthquake {
    private double magnitude;
    private String place;
    private String diatance;
    private long mTimeInMilliseconds;
    private String url;

    public Earthquake(double magnitude, String place, String diatance, long mTimeInMilliseconds, String url) {
        this.magnitude = magnitude;
        this.place = place;
        this.diatance = diatance;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getDiatance() {
        return diatance;
    }

    public void setDiatance(String diatance) {
        this.diatance = diatance;
    }


    public void setmTimeInMilliseconds(long mTimeInMilliseconds) {
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }



}
