package com.example.eurovisionapp;

public class Song {

    private int year;
    private String countryCode;
    private String country;
    private String artist;
    private String song;

    public Song() {
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public int getYear() {
        return year;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }
}
