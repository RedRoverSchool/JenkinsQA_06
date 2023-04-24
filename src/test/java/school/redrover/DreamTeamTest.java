package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

public class DreamTeamTest extends BaseTest {

    @Test
    public void testWelcomeToJenkinsPresent() {
        WebElement welcome = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/h1"));
        Assert.assertEquals(welcome.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testNewFreestyleProjectCreated() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2000));

        WebElement createAJobArrow = getDriver().findElement(
                By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']")
        );
        createAJobArrow.click();

        WebElement inputItemName = getDriver().findElement(By.id("name"));
        wait.until(ExpectedConditions.elementToBeClickable(inputItemName)).sendKeys("Project1");

        WebElement freestyleProjectTab = getDriver().findElement(
                By.xpath("//ul[@class = 'j-item-options']/li[@tabindex='0']")
        );
        freestyleProjectTab.click();

        WebElement okButton = getDriver().findElement(By.className("btn-decorator"));
        okButton.click();

        WebElement dashboardLink = getDriver().findElement(
                By.xpath("//ol[@id='breadcrumbs']/li/a[text() = 'Dashboard']")
        );
        dashboardLink.click();

        Assert.assertTrue(getDriver().findElement(By.id("projectstatus")).isDisplayed());

        List<WebElement> newProjectsList = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));

        Assert.assertEquals(newProjectsList.size(), 1);

        List<WebElement> projectDetailsList = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']/tbody/tr/td")
        );

        Assert.assertEquals(projectDetailsList.get(2).getText(), "Project1");
    }

    @Test
    public void testDashboardSidePanelItemsList() {
        List<WebElement> sidePanelItems = getDriver().findElements(By.xpath("//*[@id=\"tasks\"]/div"));
        int itemsQuantity = sidePanelItems.size();

        Assert.assertEquals(itemsQuantity, 5);
    }
}
