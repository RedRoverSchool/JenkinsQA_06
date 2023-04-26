package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class BugsBustersGroupTest extends BaseTest {

    @Test
    public void testAngelinaHeadIconIsDisplayed() {
        WebElement icon = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(icon.isDisplayed());
    }

    @Test
    public void testAngelinaDashboardButtonIsDisplayed() {
        WebElement dashboardButton = getDriver().findElement(By.xpath("//button[@class='jenkins-menu-dropdown-chevron']"));

        Assert.assertTrue(dashboardButton.isDisplayed());
    }

   @Test
    public void testMariaSearchBoxPresent() {
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@name='q']"));

        Assert.assertTrue(searchBox.isDisplayed());
    }

    @Ignore
    @Test
    public void testCreateAJobPageTitle(){
        WebElement createAJob = getDriver().findElement(By.xpath("//a[@href='newJob']/span"));
        createAJob.click();

        WebElement createAJobGetTitleText = getDriver().findElement(By.xpath("//div[@class='add-item-name']/label"));

        Assert.assertEquals(createAJobGetTitleText.getText(), "Enter an item name");
    }

    @Test
    public void testGetPeoplePage(){
        WebElement peoplePageMenu = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        peoplePageMenu.click();

        WebElement PeoplePageTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(PeoplePageTitle.getText(), "People");
    }
}