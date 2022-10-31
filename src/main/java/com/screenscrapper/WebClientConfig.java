package com.screenscrapper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.http.HttpClient;

public class WebClientConfig {
    WebClient webClient = new WebClient(BrowserVersion.CHROME);
    HtmlPage page = webClient.getPage(Variables.baseUrl);

    HttpClient httpClient = HttpClient.newHttpClient();

    public HtmlPage getPage() {
        return page;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    //to get rid of false errors
    public void WebClientErrors(){
        this.webClient.getOptions().setCssEnabled(false);
        this.webClient.getOptions().setJavaScriptEnabled(false);
        this.webClient.getOptions().setUseInsecureSSL(false);
        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        this.webClient.getOptions().setThrowExceptionOnScriptError(false);
        this.webClient.getOptions().setPrintContentOnFailingStatusCode(false);
    }

    public WebClientConfig() throws IOException {
    }
}
