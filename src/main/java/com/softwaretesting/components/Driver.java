package com.softwaretesting.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Point;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    private static WebDriver driver = new EdgeDriver();

    public Driver() {
        WebDriverManager.edgedriver().setup();
    }

    public void navigate(String url) {
        driver.navigate().to(url);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void acceptAlert() {
        // Switching to Alert
        Alert alert = driver.switchTo().alert();

        // Capturing alert message.
        String alertMessage = alert.getText();

        // Displaying alert message
        System.out.println("Alert msg: " + alertMessage);

        // Accepting alert
        alert.accept();
    }

    public void launch(String url) {
        driver.get(url);
    }

    public WebElement findById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findByXpath(String id) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    public void clickButtonByXpath(String id) {
        findByXpath(id).click();
    }

    public void quit() {
        driver.quit();
    }

    public void takeSnapShot(String fileWithPath) throws Exception {
        // Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        // Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        // Move image file to new destination
        File DestFile = new File(fileWithPath);
        // Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public void screenShot(String id, String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        WebElement logo = findById(id);
        // Convert the screenshot into BufferedImage
        BufferedImage fullScreen;
        fullScreen = ImageIO.read(screenshot);

        // Find location of the webelement logo on the page
        Point location = logo.getLocation();

        // Find width and height of the located element logo
        int width = logo.getSize().getWidth();
        int height = logo.getSize().getHeight();

        // cropping the full image to get only the logo screenshot
        BufferedImage logoImage = fullScreen.getSubimage(location.getX(), location.getY(),
                width, height);
        ImageIO.write(logoImage, "png", screenshot);

        // Save cropped Image at destination location physically.
        FileUtils.copyFile(screenshot, new File("C:\\Users\\HAZIQ\\Pictures\\QRCode Cafe Owner\\" + fileName + ".png"));
    }

    public void clearForm(String id) {
        findById(id).clear();
    }
}
