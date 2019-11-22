package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String place;
    private String diatance;
    private long mTimeInMilliseconds;

    public Earthquake(String magnitude, String place, String diatance, long mTimeInMilliseconds) {
        this.magnitude = magnitude;
        this.place = place;
        this.diatance = diatance;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
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

    public void setDate(long getTimeInMilliseconds) {
        this.mTimeInMilliseconds = getTimeInMilliseconds;
    }
}
