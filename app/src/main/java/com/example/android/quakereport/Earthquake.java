package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String place;
    private String date;

    public Earthquake(String magnitude, String place, String date) {
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
