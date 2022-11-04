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

    public static void deleteFromWeb() throws IOException, InterruptedException {
        //Posting the JSON string
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(Variables.deleteUrl))
                .DELETE()
                .build();

        WebClientConfig webClientConfig = new WebClientConfig();
        webClientConfig.connect(Variables.baseUrl);

        HttpResponse<String> postResponse = webClientConfig.getHttpClient().send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.toString());
    }
}
