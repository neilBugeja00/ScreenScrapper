package com.screenscrapper;

public class Transcript {


    public String heading;
    public String description;
    public String url;
    public String imageUrl;

    private final String postedBy = Variables.postedBy;
    public int priceInCents;
    public int alertType;

    public void populateTranscript(Extract extract){
        alertType = 6;
        description = extract.name;
        heading = extract.name;
        url = extract.productLink;
        imageUrl = extract.image;
        priceInCents = extract.price;
    }


}
