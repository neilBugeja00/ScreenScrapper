package com.screenscrapper;

import java.math.BigDecimal;

public class Item {


    private String title;
    private BigDecimal price;
    private String url;
    private String imageUrl;

    public Item(){

    }

    public Item(String title, BigDecimal price, String url, String imageUrl){
        this.title = title;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;

    }

    //Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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


}
