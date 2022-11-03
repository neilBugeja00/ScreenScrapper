package com.screenscrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebAutomation {

    WebDriver driver;
    String actualURL;

    public void testUserLogin(String userID){
        driver = new ChromeDriver();
        driver.get("https://www.marketalertum.com/Alerts/Login");

        //Finding userID
        WebElement userLogin=driver.findElement(By.id("UserId"));

        //Finding login
        WebElement login = driver.findElement(By.cssSelector("input[type='submit']"));

        //Enter userID and login
        userLogin.sendKeys(userID);
        login.click();

        actualURL= driver.getCurrentUrl();
    }

}
