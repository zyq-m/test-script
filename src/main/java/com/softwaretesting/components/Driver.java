package com.softwaretesting.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    private WebDriver driver = new ChromeDriver();

    public Driver() {
        WebDriverManager.chromedriver().setup();
    }

    public void launch(String url) {
        driver.get(url);
    }

    public WebElement findById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findByXpath(String id) {
        return driver.findElement(By.xpath(id));
    }

    public void setSelectForm(String id, String value) {
        new Select(findById(id)).selectByValue(value);
    }

    public void setInputForm(String id, String value) {
        findById(id).sendKeys(value);
    }

    public String getValue(String id) {
        return findById(id).getAttribute("value");
    }

    public String getText(String id) {
        return findById(id).getText();
    }

    public void clickButton(String id) {
        findById(id).click();
    }

    public void quit() {
        driver.quit();
    }
}
