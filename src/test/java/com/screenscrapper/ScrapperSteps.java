package com.screenscrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class ScrapperSteps {

    WebAutomation webAutomation;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum(){
        webAutomation = new WebAutomation();
    }

    //Correct login
    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        webAutomation.testUserLogin(Variables.postedBy);
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        Assert.assertEquals(webAutomation.actualURL, Variables.marketalertumList);
    }

    //Incorrect login
    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        webAutomation.testUserLogin("12345");
    }
    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        Assert.assertEquals(webAutomation.actualURL, Variables.marketalertumLogin);
    }
}
