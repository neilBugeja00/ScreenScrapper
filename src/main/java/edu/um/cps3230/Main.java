package edu.um.cps3230;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import java.io.IOException;
import java.util.List;

public class Main {

    //private static final String baseUrl = "https://www.amazon.de/s?k=halloween+kost%C3%BCm&crid=10LT0KKEMLHC3&sprefix=halloween+kost%C3%BCm+%2Caps%2C136&ref=nb_sb_noss_2";
    private static final String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    //private static final String baseUrl = "https://www.facebook.com/marketplace/110612325626836/search?sortBy=distance_ascend&query=125&exact=false";
    //private static final String baseUrl = "https://newyork.craigslist.org/search/sss?query=iphone#search=1~gallery~0~0";

    public static void main(String[] args) {

        WebClient webClient = new WebClient();

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        try {
            //HtmlPage page = webClient.getPage("https://foodnetwork.co.uk/italian-family-dinners/");
            HtmlPage page = webClient.getPage(baseUrl);

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);

            List<HtmlAnchor> links = page.getAnchors();
            for (HtmlAnchor link : links) {
                String href = link.getHrefAttribute();
                System.out.println("Link: " + href);
            }

            List<?> anchors = page.getByXPath("//li[@class='item product product-item']");
            List<?> anchorsImage = page.getByXPath("//img[@class='product-image-photo main-img']");

            for (int i = 0; i < anchors.size(); i++) {
                HtmlAnchor link = (HtmlAnchor) anchors.get(i);
                HtmlAnchor linkImage = (HtmlAnchor) anchorsImage.get(i);
                //String titleTest = link.getAttribute("title").replace(',', ';');
                String image = link.getAttribute("src");
                String recipeLink = link.getHrefAttribute();

                //System.out.println("Title: "+titleTest);
                System.out.println("Image: "+image);

            }

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }
}