package com.screenscrapper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class testScrapping {
    //Variables to be used
    HttpClient httpClient;
    WebClient webClient;
    Transcript transcript;
    String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    final String postUrl = "https://api.marketalertum.com/Alert/";

    //Setting up tests
    @BeforeEach
    public void setup(){
        httpClient = HttpClient.newHttpClient();
        webClient = new WebClient(BrowserVersion.CHROME);
        transcript = new Transcript();
    }

    @AfterEach
    public void teardown(){
        httpClient = null;
        webClient = null;
        transcript = null;
    }

    @Test
    public void manualTranscriptChangeToJSON(){
        transcript.setAlertType(6);
        transcript.setDescription("name");
        transcript.setHeading("name");
        transcript.setUrl("productLink");
        transcript.setImageUrl("image");
        transcript.setPriceInCents(25000);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        Assertions.assertEquals("{\"heading\":\"name\",\"description\":\"name\",\"url\":\"productLink\",\"imageUrl\":\"image\",\"postedBy\":\"7ab74bc8-b292-4bd0-acd5-89c0ecb5c7a2\",\"priceInCents\":25000,\"alertType\":6}",jsonRequest);
    }
}
