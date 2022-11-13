package com.screenscrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testScrapping {

    @Test
    public void postJsonToWeb(){
        //Setup
        PostClass postClass = new PostClass();

    }
    @Test
    public void correctConversionTranscriptToJSON(){
        //Setup
        Transcript mockTranscript = new Transcript();


        mockTranscript.heading="Heading";
        mockTranscript.description="Desc";
        mockTranscript.url="www.";
        mockTranscript.imageUrl="imageURL";
        mockTranscript.priceInCents=69999;
        mockTranscript.alertType=6;

        //Exercise
        Conversion conversion = new Conversion();
        conversion.convertToJson(mockTranscript);

        //Verify
        Assertions.assertEquals("{\"heading\":\""+mockTranscript.getHeading()+
                "\",\"description\":\""+mockTranscript.getDescription()+
                "\",\"url\":\""+mockTranscript.getUrl()+
                "\",\"imageUrl\":\""+mockTranscript.getImageUrl()+
                "\",\"postedBy\":\"7ab74bc8-b292-4bd0-acd5-89c0ecb5c7a2\"," +
                        "\"priceInCents\":"+mockTranscript.getPriceInCents()+"" +
                        ",\"alertType\":6}"
                ,conversion.getJsonRequest());

    }
}
