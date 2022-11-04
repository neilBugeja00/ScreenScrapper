package com.screenscrapper;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import java.io.IOException;
import java.util.List;

public class Scrapper {
    //Variables
    String name;
    String productLink;
    String image;
    int price;



    public void scrapFromWeb(int numberReads, HtmlPage page) throws IOException {

            //Read product name & link
            List<?> anchorName = page.getByXPath("//a[@class='product-item-link']");
            HtmlAnchor link = (HtmlAnchor) anchorName.get(numberReads);
            productLink = link.getHrefAttribute();
            name = link.getVisibleText();

            //Read product price
            List<?> anchorPrice = page.getByXPath("//span[@class='special-price']");
            HtmlSpan linkPrice = (HtmlSpan) anchorPrice.get(numberReads);
            String stringPrice = linkPrice.getVisibleText().replace("Special Price", "").replace("€", "").replace(".", "").replace(",", "").replaceAll("\\s+", "");
            price = Integer.parseInt(stringPrice);

            //Read product image
            List<?> anchorImage = page.getByXPath("//img[@class='product-image-photo hovered-img']");
            HtmlImage linkTop = (HtmlImage) anchorImage.get(numberReads);
            image = linkTop.getAttribute("src");

    }
}
