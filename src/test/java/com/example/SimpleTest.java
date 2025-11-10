package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimpleTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Selenium Manager (included in Selenium 4.6.0+)
        // will automatically download and set up chromedriver
        System.out.println("Starting local Chrome browser...");
        driver = new ChromeDriver();
        System.out.println("Chrome browser started.");
    }

    @Test
    public void testLocalSite() {
        System.out.println("Navigating to bstackdemo.com...");
        driver.get("https://bstackdemo.com/");

        String expectedTitle = "StackDemo";
        String actualTitle = driver.getTitle();

        System.out.println("Page title is: " + actualTitle);
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Expected title to contain 'StackDemo'");
        System.out.println("Test passed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}