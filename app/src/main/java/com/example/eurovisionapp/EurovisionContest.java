package com.example.eurovisionapp;

import java.sql.Blob;

public class EurovisionContest {

    private byte[] image;
    private int year;
    private String city;

    public EurovisionContest(){}

    public EurovisionContest(byte[] image, int year, String city) {
        this.image = image;
        this.year = year;
        this.city = city;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
