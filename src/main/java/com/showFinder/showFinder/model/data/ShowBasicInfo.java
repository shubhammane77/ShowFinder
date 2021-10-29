package com.showFinder.showFinder.model.data;


public  class ShowBasicInfo {
    private  Integer showId;
    private  String showName;
    private  Integer releaseyear;
    private String image;

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Integer releaseyear) {
        this.releaseyear = releaseyear;
    }

    public ShowBasicInfo(Integer showId, String showName, Integer releaseyear) {
        this.showId = showId;
        this.showName = showName;
        this.releaseyear = releaseyear;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
