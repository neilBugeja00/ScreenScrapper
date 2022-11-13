package com.screenscrapper;

public class Transcript {


    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPriceInCents() {
        return priceInCents;
    }


    public String heading;
    public String description;
    public String url;
    public String imageUrl;

    final String postedBy = Variables.postedBy;
    public int priceInCents;
    public int alertType=0;

    public void populateTranscript(Scrapper scrapper){
        description = scrapper.name;
        heading = scrapper.name;
        url = scrapper.productLink;
        imageUrl = scrapper.image;
        priceInCents = scrapper.price;
    }


}
