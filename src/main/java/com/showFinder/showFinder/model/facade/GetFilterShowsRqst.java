package com.showFinder.showFinder.model.facade;

import java.util.List;

public class GetFilterShowsRqst {
    private int pageNo;
     private String releaseYearRange;
     private String name;
     private double ratings;
     private List<String> genre;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getReleaseYearRange() {
        return releaseYearRange;
    }

    public void setReleaseYearRange(String releaseYearRange) {
        this.releaseYearRange = releaseYearRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
