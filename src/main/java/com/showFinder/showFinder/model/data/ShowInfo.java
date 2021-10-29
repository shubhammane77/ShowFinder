package com.showFinder.showFinder.model.data;

import javax.persistence.*;

@Entity
@Table(name="ref_showdata")
public class ShowInfo {
    @Id
    @Column(name="showid")
    private  Integer showId;
    @Column(name ="showname")
    private  String showName;

    @Column(name = "rating")
    private Double rating;

    @Column(name="releaseyear")
    private Integer releaseyear;

    @Column(name="actionflag")
    private Boolean actionFlag;

    @Column(name="comedyflag")
    private Boolean comedyFlag;
    @Column(name="documentflag")
        private Boolean documentaryFlag;
    @Column(name="fantasyflag")
    private Boolean fantasyFlag;
    @Column(name="horrorflag")
    private Boolean horrorFlag;
    @Column(name="musicalflag")
    private Boolean musicalFlag;
    @Column(name="romanceflag")
    private Boolean romanceFlag;
    @Column(name="sportflag")
    private Boolean sportFlag;
    @Column(name="animationflag")
    private Boolean animationFlag;
    @Column(name="crimeflag")
    private Boolean crimeFlag;
    @Column(name="historyflag")
    private Boolean historyFlag;
    @Column(name="kidsflag")
    private Boolean kidsFlag;
    @Column(name="thrillerflag")
    private Boolean thrillerFlag;
    @Column(name="scififlag")
    private Boolean scifiFlag;
    @Column(name="warflag")
    private Boolean warFlag;
    @Column(name="realityflag")
    private Boolean realityFlag;


    @Transient
    private String image;

    public ShowInfo() {
    }

    public ShowInfo(Integer showId, String showName, Double rating, Integer releaseyear, Boolean actionFlag, Boolean comedyFlag, Boolean documentaryFlag, Boolean fantasyFlag, Boolean horrorFlag, Boolean musicalFlag, Boolean romanceFlag, Boolean sportFlag, Boolean animationFlag, Boolean crimeFlag, Boolean historyFlag, Boolean kidsFlag, Boolean thrillerFlag, Boolean scifiFlag, Boolean warFlag, Boolean realityFlag, String image) {
        this.showId = showId;
        this.showName = showName;
        this.rating = rating;
        this.releaseyear = releaseyear;
        this.actionFlag = actionFlag;
        this.comedyFlag = comedyFlag;
        this.documentaryFlag = documentaryFlag;
        this.fantasyFlag = fantasyFlag;
        this.horrorFlag = horrorFlag;
        this.musicalFlag = musicalFlag;
        this.romanceFlag = romanceFlag;
        this.sportFlag = sportFlag;
        this.animationFlag = animationFlag;
        this.crimeFlag = crimeFlag;
        this.historyFlag = historyFlag;
        this.kidsFlag = kidsFlag;
        this.thrillerFlag = thrillerFlag;
        this.scifiFlag = scifiFlag;
        this.warFlag = warFlag;
        this.realityFlag = realityFlag;
        this.image = image;
    }

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Integer releaseyear) {
        this.releaseyear = releaseyear;
    }

    public Boolean getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(Boolean actionFlag) {
        this.actionFlag = actionFlag;
    }

    public Boolean getComedyFlag() {
        return comedyFlag;
    }

    public void setComedyFlag(Boolean comedyFlag) {
        this.comedyFlag = comedyFlag;
    }

    public Boolean getDocumentaryFlag() {
        return documentaryFlag;
    }

    public void setDocumentaryFlag(Boolean documentaryFlag) {
        this.documentaryFlag = documentaryFlag;
    }

    public Boolean getFantasyFlag() {
        return fantasyFlag;
    }

    public void setFantasyFlag(Boolean fantasyFlag) {
        this.fantasyFlag = fantasyFlag;
    }

    public Boolean getHorrorFlag() {
        return horrorFlag;
    }

    public void setHorrorFlag(Boolean horrorFlag) {
        this.horrorFlag = horrorFlag;
    }

    public Boolean getMusicalFlag() {
        return musicalFlag;
    }

    public void setMusicalFlag(Boolean musicalFlag) {
        this.musicalFlag = musicalFlag;
    }

    public Boolean getRomanceFlag() {
        return romanceFlag;
    }

    public void setRomanceFlag(Boolean romanceFlag) {
        this.romanceFlag = romanceFlag;
    }

    public Boolean getSportFlag() {
        return sportFlag;
    }

    public void setSportFlag(Boolean sportFlag) {
        this.sportFlag = sportFlag;
    }

    public Boolean getAnimationFlag() {
        return animationFlag;
    }

    public void setAnimationFlag(Boolean animationFlag) {
        this.animationFlag = animationFlag;
    }

    public Boolean getCrimeFlag() {
        return crimeFlag;
    }

    public void setCrimeFlag(Boolean crimeFlag) {
        this.crimeFlag = crimeFlag;
    }

    public Boolean getHistoryFlag() {
        return historyFlag;
    }

    public void setHistoryFlag(Boolean historyFlag) {
        this.historyFlag = historyFlag;
    }

    public Boolean getKidsFlag() {
        return kidsFlag;
    }

    public void setKidsFlag(Boolean kidsFlag) {
        this.kidsFlag = kidsFlag;
    }

    public Boolean getThrillerFlag() {
        return thrillerFlag;
    }

    public void setThrillerFlag(Boolean thrillerFlag) {
        this.thrillerFlag = thrillerFlag;
    }

    public Boolean getScifiFlag() {
        return scifiFlag;
    }

    public void setScifiFlag(Boolean scifiFlag) {
        this.scifiFlag = scifiFlag;
    }

    public Boolean getWarFlag() {
        return warFlag;
    }

    public void setWarFlag(Boolean warFlag) {
        this.warFlag = warFlag;
    }

    public Boolean getRealityFlag() {
        return realityFlag;
    }

    public void setRealityFlag(Boolean realityFlag) {
        this.realityFlag = realityFlag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
