package com.screenscrapper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import java.io.IOException;
import java.util.List;

public class ScrapperSteps {

    WebAutomation webAutomation;
    ScrapAndPostClass scrapAndPost;

    int iconCount=0;
    int headerCount=0;
    int imageCount=0;
    int priceCount=0;
    int linkCount=0;
    int descriptionCount=0;
    int numberOfRecords;
    String icon;

    //========================Setup========================
    @Before
    public void deleteRecords() throws IOException, InterruptedException {
        PostClass.deleteFromWeb();
    }





    //========================Correct login========================
    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum(){
        webAutomation = new WebAutomation();
    }

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

        for(int i=1; i<numberOfRecords+1; i++){
            //checking icon
            if(webAutomation.driver.findElement(By.xpath("//table["+i+"]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src")!=null){
                iconCount++;
            }
            //checking header
            if(webAutomation.driver.findElement(By.xpath("//table["+i+"]/tbody/tr[1]/td[1]/h4")).isDisplayed()){
                headerCount++;
            }
            //checking image
            if(webAutomation.driver.findElement(By.xpath("//table["+i+"]/tbody/tr[2]/td/img")).getAttribute("src")!=null){
                imageCount++;
            }
            //checking description
            if(webAutomation.driver.findElement(By.xpath("//table["+i+"]/tbody/tr[3]/td")).isDisplayed()){
                descriptionCount++;
            }
            //checking price
            if(webAutomation.driver.findElement(By.xpath("//table["+i+"]/tbody/tr[4]/td/b")).isDisplayed()){
                priceCount++;
            }
            //checking link
            if(webAutomation.driver.findElement(By.xpath("//table["+i+"]/tbody/tr[5]/td/a")).getAttribute("href")!=null){
                linkCount++;
            }
        }

    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        Assertions.assertEquals(numberOfRecords,iconCount);
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
        Assertions.assertEquals(numberOfRecords,imageCount);
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        Assertions.assertEquals(numberOfRecords,priceCount);
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        Assertions.assertEquals(numberOfRecords,linkCount);
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        scrapAndPost = new ScrapAndPostClass();
        scrapAndPost.ScrapAndPost(arg0);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0){
        Assertions.assertEquals(arg0,numberOfRecords);

    }









    //========================icon check========================
    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(int arg0) {
        scrapAndPost = new ScrapAndPostClass();
        scrapAndPost.postAlertType=arg0;
        scrapAndPost.ScrapAndPost(1);
    }

    @And("the icon displayed should be icon-car.png")
    public void theIconDisplayedShouldBeIconCarPng() {
        icon = webAutomation.driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src");
        Assertions.assertEquals("https://www.marketalertum.com/images/icon-car.png",icon);
    }

    @And("the icon displayed should be icon-boat.png")
    public void theIconDisplayedShouldBeIconBoatPng() {
        icon = webAutomation.driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src");
        Assertions.assertEquals("https://www.marketalertum.com/images/icon-boat.png",icon);
    }

    @And("the icon displayed should be icon-property-rent.png")
    public void theIconDisplayedShouldBeIconPropertyRentPng() {
        icon = webAutomation.driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src");
        Assertions.assertEquals("https://www.marketalertum.com/images/icon-property-rent.png",icon);
    }

    @And("the icon displayed should be icon-property-sale.png")
    public void theIconDisplayedShouldBeIconPropertySalePng() {
        icon = webAutomation.driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src");
        Assertions.assertEquals("https://www.marketalertum.com/images/icon-property-sale.png",icon);
    }

    @And("the icon displayed should be icon-toys.png")
    public void theIconDisplayedShouldBeIconToysPng() {
        icon = webAutomation.driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src");
        Assertions.assertEquals("https://www.marketalertum.com/images/icon-toys.png",icon);
    }

    @And("the icon displayed should be icon-electronics.png")
    public void theIconDisplayedShouldBeIconElectronicsPng() {
        icon = webAutomation.driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]/h4/img")).getAttribute("src");
        Assertions.assertEquals("https://www.marketalertum.com/images/icon-electronics.png",icon);
    }





    //========================Teardown========================
    @After
    public void deleteRecordsAfter() {
        //webAutomation.closeDriver();
    }

}
