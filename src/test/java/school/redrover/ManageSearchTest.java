package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ManageSearchTest extends BaseTest {

    @Test
    public void testSearchOneLetter() {

        WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkins.click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("settings-search-bar")));

        WebElement searchField = getDriver().findElement(By.id("settings-search-bar"));
        searchField.sendKeys("m");

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'results-container')]")));

        WebElement configureSystem = getDriver().findElement(By.xpath("//a[contains(@href,'/manage/configure')]"));
        configureSystem.click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title")));

        String title = getDriver().getTitle();
        Assert.assertEquals(title, "Configure System [Jenkins]");
    }
}
