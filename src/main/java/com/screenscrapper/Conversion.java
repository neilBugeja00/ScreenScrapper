package com.screenscrapper;

import com.google.gson.Gson;

public class Conversion {
    String jsonRequest;

    public String convertToJson(Transcript transcript){
        Gson gson = new Gson();
        jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);
        return jsonRequest;
    }

    public String getJsonRequest() {
        return jsonRequest;
    }
}
