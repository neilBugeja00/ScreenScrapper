package com.screenscrapper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import java.io.IOException;
import java.net.http.HttpClient;

public class WebClientConfig {

    HtmlPage page;
    HttpClient httpClient;
    public void connect() throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        page = webClient.getPage(Variables.baseUrl);
        httpClient = HttpClient.newHttpClient();
    }

    public HtmlPage getPage() {
        return page;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    //to get rid of false errors
    /*
    public void WebClientErrors(){
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
    }

     */

    //Check if marketalertum list is reachable
    public static boolean isReachable(String url) {
        boolean isReachable = true;
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build())
        {
            HttpHead request = new HttpHead(url);
            CloseableHttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 404) {
                System.out.println("URL " + url + " Not found");
                isReachable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isReachable = false;
        }

        System.out.println("isReacable: "+isReachable);
        return isReachable;
    }

    public WebClientConfig() throws IOException {
    }
}
