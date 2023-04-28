package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupILoveBugsTest extends BaseTest {

    @Test
    public void testVerifySearchbarIsPresent() {
        WebElement searchBar = getDriver().findElement(By.id("search-box"));
        Assert.assertTrue(searchBar.isDisplayed());
    }

    @Test
    public void testCreateProjectNameInBreadcrumbs() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));

        String name = "testProject";
        final By createItemBTN = By.xpath("//a[@href='/view/all/newJob']");
        final By inputField = By.id("name");
        final By modelFreeStyleProjectBTN = By.xpath("//li[@class='hudson_model_FreeStyleProject']");
        final By okBTN = By.id("ok-button");
        final By nameProjectInBreadcrumbs = By.xpath("//li[@class='jenkins-breadcrumbs__list-item'][2]/a[@class='model-link']");

        wait.until(ExpectedConditions.elementToBeClickable(createItemBTN));
        getDriver().findElement(createItemBTN).click();

        wait.until(ExpectedConditions.visibilityOf( getDriver().findElement(inputField)));
        getDriver().findElement(inputField).sendKeys(name);
        getDriver().findElement(modelFreeStyleProjectBTN).click();
        getDriver().findElement(okBTN).click();

        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(nameProjectInBreadcrumbs)));
        Assert.assertEquals(name, getDriver().findElement(nameProjectInBreadcrumbs).getText(), "Wrong name project");
    }

    @Test
    public void testPeoplePageTitle(){
        WebElement peoplePageMenu = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        peoplePageMenu.click();

        WebElement peoplePageTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(peoplePageTitle.getText(), "People",
                "People page Title is not as expected");
    }

    @Test
    public void testBuildHistoryPageTitle() {
        WebElement buildHistoryMenu = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        buildHistoryMenu.click();

        WebElement buildHistoryPageTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(buildHistoryPageTitle.getText(), "Build History of Jenkins",
                "Build History page Title is not as expected");
    }

    @Test
    public void testManageJenkinsPageTitle() {
        WebElement manageJenkinsMenu = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkinsMenu.click();

        WebElement manageJenkinsPageTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(manageJenkinsPageTitle.getText(), "Manage Jenkins",
                "Manage Jenkins page Title is not as expected");
    }
}
