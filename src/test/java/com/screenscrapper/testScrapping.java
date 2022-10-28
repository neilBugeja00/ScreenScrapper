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

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class testScrapping {
    //Variables to be used
    HttpClient httpClient;
    HtmlPage page;
    WebClient webClient;
    Transcript transcript;
    String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    final String postUrl = "https://api.marketalertum.com/Alert/";

    //Setting up tests
    @BeforeEach
    public void setup() throws IOException {
        httpClient = HttpClient.newHttpClient();
        webClient = new WebClient(BrowserVersion.CHROME);

        //to get rid of false errors
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        transcript = new Transcript();
        page = webClient.getPage(baseUrl);

    }

    @AfterEach
    public void teardown(){
        httpClient = null;
        webClient = null;
        transcript = null;
    }

    @Test
    public void manualTranscriptChangeToJSON(){
        manualTranscript(transcript);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        Assertions.assertEquals("{\"heading\":\"name\",\"description\":\"name\",\"url\":\"productLink\",\"imageUrl\":\"image\",\"postedBy\":\"7ab74bc8-b292-4bd0-acd5-89c0ecb5c7a2\",\"priceInCents\":25000,\"alertType\":6}",jsonRequest);
    }

    @Test
    public void successfulManualTransciptPost() throws IOException, InterruptedException {
        manualTranscript(transcript);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        //Posting the JSON string
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("content-type","application/problem+json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals("(POST https://api.marketalertum.com/Alert/) 201",postResponse.toString());
    }

    @Test
    public void scrapedTranscriptChangeToJSON(){
        scrapedTranscript(transcript);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        Assertions.assertEquals("{\"heading\":\""+transcript.getHeading()+"\",\"description\":\""+transcript.getDescription()+"\",\"url\":\""+transcript.getUrl()+"\",\"imageUrl\":\""+transcript.getImageUrl()+"\",\"postedBy\":\"7ab74bc8-b292-4bd0-acd5-89c0ecb5c7a2\",\"priceInCents\":"+transcript.getPriceInCents()+",\"alertType\":6}",jsonRequest);

    }

    @Test
    public void successfulScrapedTransciptPost() throws IOException, InterruptedException {
        scrapedTranscript(transcript);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        //Posting the JSON string
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("content-type","application/problem+json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals("(POST https://api.marketalertum.com/Alert/) 201",postResponse.toString());
    }

    //============================================= METHODS =============================================
    public void manualTranscript(Transcript transcript){
        transcript.setAlertType(6);
        transcript.setDescription("name");
        transcript.setHeading("name");
        transcript.setUrl("productLink");
        transcript.setImageUrl("image");
        transcript.setPriceInCents(25000);
    }

    public void scrapedTranscript(Transcript transcript){
        List<?> anchorName = page.getByXPath("//a[@class='product-item-link']");
        HtmlAnchor link = (HtmlAnchor) anchorName.get(0);
        String productLink = link.getHrefAttribute();
        String name = link.getVisibleText().replace("\"","").replaceAll("\\s+","");

        //Read product price
        List<?> anchorPrice = page.getByXPath("//span[@class='special-price']");
        HtmlSpan linkPrice = (HtmlSpan) anchorPrice.get(0);
        String stringPrice = linkPrice.getVisibleText().replace("Special Price","").replace("€","").replace(".","").replace(",","").replaceAll("\\s+","");
        int price = Integer.parseInt(stringPrice);

        //Read product image
        List<?> anchorImage = page.getByXPath("//img[@class='product-image-photo hovered-img']");
        HtmlImage linkTop = (HtmlImage) anchorImage.get(0);
        String image = linkTop.getAttribute("src");

        //creating & populating transcript to be used for POST
        transcript.setAlertType(6);
        transcript.setDescription(name);
        transcript.setHeading(name);
        transcript.setUrl(productLink);
        transcript.setImageUrl(image);
        transcript.setPriceInCents(price);
    }

}
