package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.runner.BaseTest;

public class UtilsTest{

    public static void testCreateFreestyleProject(WebDriver driver, WebDriverWait wait2 ,String name){
        driver.findElement(By.linkText("New Item")).click();

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);
        driver.findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
    }

    public static void testCreatePipeline(WebDriver driver, WebDriverWait wait2 ,String name){
        driver.findElement(By.linkText("New Item")).click();

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);
        driver.findElement(By.xpath("//label/span[text()='Pipeline']")).click();
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
    }

    public static void testCreateMultiConfigurationProject(WebDriver driver, WebDriverWait wait2 ,String name){
        driver.findElement(By.linkText("New Item")).click();

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);
        driver.findElement(By.xpath("//label/span[contains(text(), 'Multi-configuration proj')]")).click();
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
    }

    public static void testCreateFolder(WebDriver driver, WebDriverWait wait2 ,String name){
        driver.findElement(By.linkText("New Item")).click();

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);

        driver.findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
    }

    public static void testCreateMultibranchPipeline(WebDriver driver, WebDriverWait wait2 ,String name) {
        driver.findElement(By.linkText("New Item")).click();

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Multibranch Pipeline']")))
                .sendKeys(name);
        driver.findElement(By.xpath("//label/span[contains(text(), 'Multi-configuration proj')]")).click();
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
    }

    public static void testCreateOrganizationFolder(WebDriver driver, WebDriverWait wait2 ,String name) {
        driver.findElement(By.linkText("New Item")).click();

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);
        driver.findElement(By.xpath("//label/span[contains(text(), 'Organization Folder')]")).click();
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
    }
}
