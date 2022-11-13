package com.screenscrapper;

import java.io.IOException;

public class ScrapAndPostClass {

    public int postAlertType = 1;
    public void ScrapAndPost(int numberOfScrapes) {
        try {
            //=============================Connecting to web services=============================
            WebClientConfig webClientConfig = new WebClientConfig();
            webClientConfig.connect(Variables.baseUrl);

            for (int i = 0; i < numberOfScrapes; i++) {
                //Extracting data from web
                Scrapper scrapper = new Scrapper();
                scrapper.scrapFromWeb(i, webClientConfig.getPage());

                //Saving data into transcript class
                Transcript transcript = new Transcript();
                transcript.alertType = postAlertType;
                transcript.populateTranscript(scrapper);

                //Converting transcript to JSON
                Conversion conversion = new Conversion();
                conversion.convertToJson(transcript);

                //Posting JSON to website
                PostClass.postToWeb(Variables.postUrl, conversion.getJsonRequest(), webClientConfig.getHttpClient());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
