package edu.um.cps3230;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class Main {

    //private static final String baseUrl = "https://www.amazon.de/s?k=halloween+kost%C3%BCm&crid=10LT0KKEMLHC3&sprefix=halloween+kost%C3%BCm+%2Caps%2C136&ref=nb_sb_noss_2";
    //private static final String baseUrl = "https://www.facebook.com/marketplace/110612325626836/search?sortBy=distance_ascend&query=125&exact=false";
    private static final String baseUrl = "https://newyork.craigslist.org/search/sss?query=iphone#search=1~gallery~0~0";

    public static void main(String[] args) {

        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);

        try{
            HtmlPage page = client.getPage(baseUrl);
            List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//li[@class='cl-search-result cl-search-view-mode-gallery']");
            if(HtmlElement.isEmpty()){
                System.out.println("No items found");
            }else{
                for(int i=0; i<5; i++){
                    Html
                }
            }
            System.out.println(page.asXml());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}