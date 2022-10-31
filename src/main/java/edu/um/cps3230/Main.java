package edu.um.cps3230;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import com.screenscrapper.Extract;
import com.screenscrapper.PostClass;
import com.screenscrapper.Transcript;

import java.io.IOException;
import java.net.http.HttpClient;

public class Main {

    //URLS used to scrape data and POST request
    private static final String baseUrl = "https://www.scanmalta.com/shop/catalog/category/view/s/laptops-2/id/705/";
    private static final String postUrl = "https://api.marketalertum.com/Alert/";

    public static void main(String[] args) throws InterruptedException {

        try {
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

            HtmlPage page = webClient.getPage(baseUrl);

            for(int i=0; i<5; i++){
                //Extracting data from web
                Extract extract = new Extract();
                extract.extractFromWeb(i, page);

                //Saving data into transcript class
                Transcript transcript = new Transcript();
                transcript.populateTranscript(extract);

                //Converting transcript to JSON
                Gson gson = new Gson();
                String jsonRequest = gson.toJson(transcript);
                System.out.println(jsonRequest);

                //Posting JSON to website
                PostClass.postToWeb(postUrl, jsonRequest, httpClient);
            }
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();


        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

    }
}
