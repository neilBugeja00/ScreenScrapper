package com.screenscrapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostClass {
    public static void postToWeb(String postUrl, String jsonRequest, HttpClient httpClient) throws IOException, InterruptedException {
        //Posting the JSON string
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("content-type","application/problem+json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.toString());
    }
}
