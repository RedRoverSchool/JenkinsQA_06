package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class PageHeaderTest extends BaseTest {

    @Ignore
    @Test
    public void testClickLogoToReturnToDashboardPage() {

        WebElement createNewItemFreeStyleProject = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        createNewItemFreeStyleProject.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//input[@name='name']")))).sendKeys("New Item 1");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//button[@id='ok-button']")))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//button[normalize-space()='Save']")))).click();

        WebElement goToDashboard1 = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        goToDashboard1.click();

        WebElement createNewItemFolder = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        createNewItemFolder.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//input[@name='name']")))).sendKeys("New Item 2");
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//button[@id='ok-button']")))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//button[normalize-space()='Save']")))).click();

        WebElement goToDashboard2 = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        goToDashboard2.click();

        WebElement goToUserIdPage = getDriver()
                .findElement(By.xpath("//a[@href='/user/admin']//*[not(self::button)]"));
        goToUserIdPage.click();

        WebElement clickJenkinsLogoToReturnToDashboardPage = getDriver()
                .findElement(By.xpath("//div[@class='logo']/a"));
        clickJenkinsLogoToReturnToDashboardPage.click();

        List<WebElement> ifBreadcrumbBarMenuListConsistsOfDashboardWord = getDriver()
                .findElements(By.xpath("//div[@id='breadcrumbBar']/descendant::text()/parent::*"));
            for (WebElement i : ifBreadcrumbBarMenuListConsistsOfDashboardWord) {
                assertEquals(i.getText(), "Dashboard");
            }

        List<String> expectedCreatedItemsList = Arrays.asList("New Item 1", "New Item 2");
        List<WebElement> actualItemsList = getDriver()
                .findElements(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span"));
            for (int i = 0; i < actualItemsList.size(); i++) {
                assertEquals(actualItemsList.get(i).getText(), expectedCreatedItemsList.get(i));
            }
    }
}
