package edu.um.cps3230;

import com.screenscrapper.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        try {
            //=============================Connecting to web services=============================
            WebClientConfig webClientConfig = new WebClientConfig();
            webClientConfig.connect();
            webClientConfig.isReachable(Variables.marketalertumList);

            for(int i=0; i<5; i++){
                //Extracting data from web
                Scrapper scrapper = new Scrapper();
                scrapper.extractFromWeb(i, webClientConfig.getPage());

                //Saving data into transcript class
                Transcript transcript = new Transcript();
                transcript.populateTranscript(scrapper);

                //Converting transcript to JSON
                Conversion conversion = new Conversion();
                conversion.convertToJson(transcript);

                //Posting JSON to website
                PostClass.postToWeb(Variables.postUrl, conversion.getJsonRequest(), webClientConfig.getHttpClient());
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

    }
}
