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
            HtmlPage page = webClient.getPage(baseUrl);

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);


            List<?> anchors = page.getByXPath("//div[@class='product-item-info']");

            for (int i = 0; i < 63; i++) {

                //Read product name & link
                List<?> anchorName = page.getByXPath("//a[@class='product-item-link']");
                HtmlAnchor link = (HtmlAnchor) anchorName.get(i);
                String productLink = link.getHrefAttribute();
                String name = link.getVisibleText();

                //Read product price
                List<?> anchorPrice = page.getByXPath("//span[@class='special-price']");
                HtmlSpan linkPrice = (HtmlSpan) anchorPrice.get(i);
                String price = linkPrice.getVisibleText().replace("Special Price","");

                //Read product image
                List<?> anchorImage = page.getByXPath("//img[@class='product-image-photo hovered-img']");
                HtmlImage linkTop = (HtmlImage) anchorImage.get(i);
                String image = linkTop.getAttribute("src");

                int counter = i+1;
                System.out.println("Number: "+ counter);
                System.out.println("Title : "+name);
                System.out.println("Link  : "+productLink);
                System.out.println("Price : "+price);
                System.out.println("Image : "+image);
                System.out.println("");


            }

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }
}