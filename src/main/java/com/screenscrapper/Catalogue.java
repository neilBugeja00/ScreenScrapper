package com.screenscrapper;

import java.util.LinkedList;
import java.util.List;

public class Catalogue {

    private String name;
    private List<Item> items;

    public Catalogue(){
        items = new LinkedList<Item>();
    }

    public boolean addItem(Item item){
        items.add(item);
        return true;
    }

    public String returnItemTitle(Item item){
        return item.getTitle();
    }

    public boolean viewItems(){
        for(int i=0; i<items.size();i++){
            System.out.println("Title: "+items.get(i).getTitle());
            System.out.println("URL  :"+items.get(i).getUrl());
            System.out.println("Image:"+items.get(i).getImageUrl());
            System.out.println("Price:"+items.get(i).getPrice());
            System.out.println();
        }
        return true;
    }

    public void removeItem(Item item){
        items.remove(item);
    }




}
