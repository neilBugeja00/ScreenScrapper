package edu.um.cps3230;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.*;
import com.google.gson.Gson;
import com.screenscrapper.Transcript;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {

    //URLS used to scrape data and POST request
    private static final String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    private static final String postUrl = "https://api.marketalertum.com/Alert/";

    public static void main(String[] args) throws IOException, InterruptedException {

        //HttpClient used for POST
        HttpClient httpClient = HttpClient.newHttpClient();

        //=============================Scraping data=============================
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //to get rid of false errors
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        try {
            HtmlPage page = webClient.getPage(baseUrl);

            for (int i = 0; i < 5; i++) {

                //Read product name & link
                List<?> anchorName = page.getByXPath("//a[@class='product-item-link']");
                HtmlAnchor link = (HtmlAnchor) anchorName.get(i);
                String productLink = link.getHrefAttribute();
                String name = link.getVisibleText();

                //Read product price
                List<?> anchorPrice = page.getByXPath("//span[@class='special-price']");
                HtmlSpan linkPrice = (HtmlSpan) anchorPrice.get(i);
                String stringPrice = linkPrice.getVisibleText().replace("Special Price","").replace("€","").replace(".","").replace(",","").replaceAll("\\s+","");
                int price = Integer.parseInt(stringPrice);

                //Read product image
                List<?> anchorImage = page.getByXPath("//img[@class='product-image-photo hovered-img']");
                HtmlImage linkTop = (HtmlImage) anchorImage.get(i);
                String image = linkTop.getAttribute("src");

                //creating & populating transcript to be used for POST
                Transcript transcript = new Transcript();
                transcript.setAlertType(6);
                transcript.setDescription(name);
                transcript.setHeading(name);
                transcript.setUrl(productLink);
                transcript.setImageUrl(image);
                transcript.setPriceInCents(price);

                Gson gson = new Gson();
                String jsonRequest = gson.toJson(transcript);

                //Posting the JSON string
                HttpRequest postRequest = HttpRequest.newBuilder()
                        .uri(URI.create(postUrl))
                        .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                        .header("content-type","application/problem+json")
                        .build();

                HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println(postResponse.toString());
            }

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

    }
}
