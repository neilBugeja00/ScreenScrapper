package com.screenscrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testScrapping {

    @BeforeEach
    public void setup(){
    }

    @AfterEach
    public void teardown(){
    }

    @Test
    public void transcriptConversionToJson(){
        //Setup

        //Converting transcript to JSON
        Conversion conversion = new Conversion();
        conversion.convertToJson(transcript);

        //Assertion
        Assertions.assertEquals("{\"heading\":\""+transcript.getHeading()+"\",\"description\":\""+transcript.getDescription()+"\",\"url\":\""+transcript.getUrl()+"\",\"imageUrl\":\""+transcript.getImageUrl()+"\",\"postedBy\":\"7ab74bc8-b292-4bd0-acd5-89c0ecb5c7a2\",\"priceInCents\":"+transcript.getPriceInCents()+",\"alertType\":6}",jsonRequest);

    }

}
