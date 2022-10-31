package com.screenscrapper;

public class Transcript {


    private String heading;
    private String description;
    private String url;
    private String imageUrl;

    private final String postedBy = Variables.postedBy;
    private int priceInCents;
    private int alertType;

    public void populateTranscript(Extract extract){
        setAlertType(6);
        setDescription(extract.name);
        setHeading(extract.name);
        setUrl(extract.productLink);
        setImageUrl(extract.image);
        setPriceInCents(extract.price);
    }



    //========================= Getters & Setters =========================

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }
    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }


}
