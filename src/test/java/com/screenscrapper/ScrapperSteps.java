package com.screenscrapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.List;

public class ScrapperSteps {

    WebAutomation webAutomation;
    ScrapAndPostClass scrapAndPost;

    int headerCount;
    int imageCount;
    int priceCount;
    int linkCount;
    int descriptionCount;
    int numberOfRecords;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum(){
        webAutomation = new WebAutomation();
    }

    //========================Correct login========================
    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        webAutomation.userLogin(Variables.postedBy);
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        Assert.assertEquals(webAutomation.actualURL, Variables.marketalertumList);
    }

    //========================Incorrect login========================
    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        webAutomation.userLogin("12345");
    }
    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        Assert.assertEquals(webAutomation.actualURL, Variables.marketalertumLogin);
    }

    //========================3 Alerts========================
    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0){
        scrapAndPost = new ScrapAndPostClass();
        scrapAndPost.ScrapAndPost(arg0);
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts(){
        //Logging into list of users
        webAutomation.userLogin(Variables.postedBy);


        //Number of records on the page
        List<?> anchorRecords = webAutomation.driver.findElements(By.xpath("//table"));
        numberOfRecords = anchorRecords.size();

        //Read product header
        List<?> anchorHeader = webAutomation.driver.findElements(By.xpath("//h4"));
        headerCount = anchorHeader.size();

        //Read image
        List<?> anchorImage = webAutomation.driver.findElements(By.xpath("//img"));
        imageCount = anchorImage.size();

        //Read description
        List<?> anchorDescription = webAutomation.driver.findElements(By.xpath("//h4"));
        descriptionCount = anchorDescription.size();

        //Read price
        List<?> anchorPrice = webAutomation.driver.findElements(By.xpath("//b"));
        priceCount = anchorPrice.size();

        //Read link
        List<?> anchorLink = webAutomation.driver.findElements(By.xpath("//href"));
        linkCount = anchorLink.size();

    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        Assertions.assertEquals(numberOfRecords*2,imageCount);
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        Assertions.assertEquals(numberOfRecords,headerCount);
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        Assertions.assertEquals(numberOfRecords,descriptionCount);
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        Assertions.assertEquals(numberOfRecords*2,imageCount);
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        Assertions.assertEquals(numberOfRecords,priceCount);
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        Assertions.assertEquals(0,linkCount);
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        scrapAndPost = new ScrapAndPostClass();
        scrapAndPost.ScrapAndPost(arg0);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        Assertions.assertEquals(arg0,numberOfRecords);
    }
}
