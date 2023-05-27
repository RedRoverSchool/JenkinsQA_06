package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ConfigureGlobalSecurityTest extends BaseTest {
    public void navigateToConfigureGlobalSecurityPage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[text()='Configure Global Security']")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Configure Global Security']")));
    }

    @Test
    public void testCheckTitleTexts() {
        List<String> expectedTitleTexts = List.of(
                "Authentication",
                "Markup Formatter",
                "Agents",
                "CSRF Protection",
                "Git plugin notifyCommit access tokens",
                "Git Hooks",
                "Hidden security warnings",
                "API Token",
                "SSH Server",
                "Git Host Key Verification Configuration");

        navigateToConfigureGlobalSecurityPage();

        List<WebElement> titles = getDriver().findElements(By.cssSelector(".jenkins-section__title"));
        List<String> actualTitleTexts = new ArrayList<>();
        for (WebElement element : titles) {
            actualTitleTexts.add(element.getText());
        }
        Assert.assertEquals(actualTitleTexts, expectedTitleTexts);
    }

    @Test
    public void testCheckNumberOfTitles() {
        int expectedNumberOfTitles = 10;

        int actualNumberOfTitles = new MainPage(getDriver())
                .navigateToConfigureGlobalSecurityPage()
                .getNumberOfTitles();

        Assert.assertEquals(actualNumberOfTitles, expectedNumberOfTitles);
    }

    @Test
    public void testCheckNumberOfHelpButton() {
        int expectedNumberOfHelpButton = 15;

        int actualNumberOfHelpButton = new MainPage(getDriver())
                .navigateToConfigureGlobalSecurityPage()
                .getNumberOfHelpButton();

        Assert.assertEquals(actualNumberOfHelpButton, expectedNumberOfHelpButton);
    }

    @Test
    public void testHostKeyVerificationStrategyDropdownMenuOptions() {
        List<String> expectedMenuNames = List.of(
                "Accept first connection",
                "Known hosts file",
                "Manually provided keys",
                "No verification");

        navigateToConfigureGlobalSecurityPage();

        Actions action = new Actions(getDriver());
        WebElement hostKeyVerificationDropdown = getDriver().findElement(By.xpath("//div[@class='jenkins-form-item ']//div[@class='jenkins-select']"));
        action.moveToElement(hostKeyVerificationDropdown).click().perform();

        List<WebElement> menus = getDriver().findElements(
                By.xpath("//div[@class='jenkins-form-item ']//div[@class='jenkins-select']//option"));
        List<String> actualMenuNames = new ArrayList<>();
        for (WebElement element : menus) {
            actualMenuNames.add(element.getText());
        }

        Assert.assertEquals(actualMenuNames, expectedMenuNames);
    }
}
