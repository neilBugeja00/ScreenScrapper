package edu.um.cps3230;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.*;
import com.google.gson.Gson;
import com.screenscrapper.Catalogue;
import com.screenscrapper.Item;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

public class Main {

    private static final String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    private static final String postUrl = "https://api.marketalertum.com/Alert";

    public static void main(String[] args) {

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

    }
}
