package edu.um.cps3230;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.*;
import com.google.gson.Gson;
import com.screenscrapper.Catalogue;
import com.screenscrapper.Item;
import com.screenscrapper.Transcript;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {

    private static final String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    private static final String postUrl = "https://api.marketalertum.com/Alert/";

    public static void main(String[] args) throws IOException, InterruptedException {


        //=============================Scraping data=============================
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //to get rid of false errors
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        Catalogue catalogue = new Catalogue();

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
                String stringPrice = linkPrice.getVisibleText().replace("Special Price","").replace("€","").replace(",","").replaceAll("\\s+","");
                BigDecimal price = new BigDecimal(stringPrice);

                //Read product image
                List<?> anchorImage = page.getByXPath("//img[@class='product-image-photo hovered-img']");
                HtmlImage linkTop = (HtmlImage) anchorImage.get(i);
                String image = linkTop.getAttribute("src");


                //creating & populating item

                Item item = new Item();

                item.setTitle(name);
                item.setUrl(productLink);
                item.setPrice(price);
                item.setImageUrl(image);

                catalogue.addItem(item);

            }

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

        catalogue.viewItems();

        //Creating the JSON string
        Transcript transcript = new Transcript();
        transcript.setAlertType(6);
        transcript.setDescription("Description");
        transcript.setHeading("Heading");
        transcript.setUrl("Url");
        transcript.setImageUrl("Image Url");
        transcript.setPriceInCents("2000");

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);



        //Posting the JSON string
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("content-type","application/problem+json")
                .build();


        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());

    }
}
