package com.screenscrapper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.http.HttpClient;

public class WebClientConfig {

    HtmlPage page;
    HttpClient httpClient;
    public void connect(String link) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //getting rid of false errors
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        page = webClient.getPage(link);
        httpClient = HttpClient.newHttpClient();
    }

    public HtmlPage getPage() {
        return page;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public WebClientConfig() throws IOException {
    }

}
