package school.redrover.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {

    private static void createProject(WebDriver driver, WebDriverWait wait, String name) {
        driver.findElement(By.linkText("New Item")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);
    }
    private static void saveProject(WebDriver driver, WebDriverWait wait, String name){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        driver.findElement(By.linkText("Dashboard")).click();
    }

    public static void createFreestyleProject(WebDriver driver, WebDriverWait wait , String name){
        createProject(driver,wait,name);

        driver.findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();

        saveProject(driver,wait, name);
    }

    public static void createPipeline(WebDriver driver, WebDriverWait wait , String name){
        createProject(driver,wait,name);

        driver.findElement(By.xpath("//label/span[text()='Pipeline']")).click();

        saveProject(driver,wait, name);
    }

    public static void createMultiConfigurationProject(WebDriver driver, WebDriverWait wait , String name){
        createProject(driver,wait,name);

        driver.findElement(By.xpath("//label/span[contains(text(), 'Multi-configuration proj')]")).click();

        saveProject(driver,wait, name);
    }

    public static void createFolder(WebDriver driver, WebDriverWait wait , String name){
        createProject(driver,wait,name);

        driver.findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();

        saveProject(driver,wait, name);
    }

    public static void createMultibranchPipeline(WebDriver driver, WebDriverWait wait , String name) {
        createProject(driver,wait,name);

        driver.findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();

        saveProject(driver,wait, name);
    }

    public static void createOrganizationFolder(WebDriver driver, WebDriverWait wait , String name) {
        createProject(driver,wait,name);

        driver.findElement(By.xpath("//label/span[contains(text(), 'Organization Folder')]")).click();

        saveProject(driver,wait, name);
    }
}
