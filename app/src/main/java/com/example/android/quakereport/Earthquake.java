package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String place;
    private Long mTimeInMilliseconds;

    public Earthquake(String magnitude, String place, long mTimeInMilliseconds) {
        this.magnitude = magnitude;
        this.place = place;
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

    public Long getDate() {
        return mTimeInMilliseconds;
    }

    public void setDate(long date) {
        this.mTimeInMilliseconds = date;
    }
}
