package edu.um.cps3230;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.*;
import com.screenscrapper.Item;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static final String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    //private static final String baseUrl = "https://sfbay.craigslist.org/search/sss?query=iphone%208&sort=rel";

    public static void main(String[] args) {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //to get rid of false errors
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        try {
            //HtmlPage page = webClient.getPage("https://foodnetwork.co.uk/italian-family-dinners/");
            HtmlPage page = webClient.getPage(baseUrl);

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);

            List<?> anchors = page.getByXPath("//a[@class='product-item-link']");
            for (int i = 0; i < anchors.size(); i++) {
                HtmlAnchor link = (HtmlAnchor) anchors.get(i);

                //String recipeTitle = link.getAttribute("title").replace(',', ';');
                String recipeLink = link.getHrefAttribute();
                String name = link.getVisibleText();

                System.out.println("Title: "+name);
                System.out.println("Link: "+recipeLink);

            }

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }
}