package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AlexLeoGroupTests extends BaseTest {

    private WebDriverWait webDriverWait5;

    private final WebDriverWait getWait5() {
        if (webDriverWait5 == null) {
            webDriverWait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return webDriverWait5;
    }

    private final void verifyElementVisible(WebElement element) {
        getWait5().until(ExpectedConditions.visibilityOf(element));
    }

    @Test
    public void testVerifyLogoJenkinsIsPresent() {
        WebElement element = getDriver().findElement(By.cssSelector("img#jenkins-head-icon"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testVerifyWordIconJenkinsPresent() {
        WebElement logoWord = getDriver()
                .findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(logoWord.isDisplayed());
    }

    @Test
    public void validateJenkinsLoginTest() {
        String verify = getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1")).getText();
        Assert.assertEquals(verify, "Welcome to Jenkins!");
    }

    @Test
    public void testVerifyPeopleLinkTextPresentOnHomePage() {
        List<WebElement> elems = getDriver().findElements(By.xpath("//span[@class = 'task-link-text']"));
        String PeopleText = elems.get(1).getText();

        Assert.assertEquals(PeopleText, "People");

    }

    @Test
    public void testVerifyBuildQueueTextPresentOnHomePage() {
        List<WebElement> elems = getDriver().findElements(By.xpath("//span[@class = 'pane-header-title']"));
        String BuildQueueText = elems.get(0).getText();

        Assert.assertEquals(BuildQueueText, "Build Queue");
    }

    @Test
    public void testVerifyIconButtonsRowPresent() {
        getDriver().get("http://localhost:8080/asynchPeople/");
        WebElement iconButtonsRow = getDriver().
                findElement(By.xpath("//div[contains(@class, 'jenkins-buttons-row')]"));

        Assert.assertTrue(iconButtonsRow.isDisplayed());
    }

    @Test
    public void testVerifyFolderLabelFont() {
        getDriver().get("http://localhost:8080/view/all/newJob");
        WebElement elem = getDriver().findElement(By.id("items"));
        List<WebElement> items = elem.findElements(By.cssSelector("li span"));

        for ( WebElement element  : items) {
            Assert.assertTrue(element.getAttribute("baseURI").contains("newJob"));
        }
    }
    @Test
    public void testLogoJenkinsIsPresent() {
        WebElement logoJenkins = getDriver().findElement(By.xpath("//img[@id='jenkins-head-icon']"));
        Assert.assertTrue(logoJenkins.isDisplayed());
    }

    @Test
    public void testWordIconJenkinsIsPresent() {
        WebElement wordJenkins = getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']"));
        Assert.assertTrue(wordJenkins.isDisplayed());
    }

    @Test
    public void testJenkinsLogoVerification() {
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testJenkinsWordIconVerification() {
        WebElement icon = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(icon.isDisplayed());
    }

    @Test
    public void testSearchFieldVerification() {
        WebElement searchField = getDriver().findElement(By.xpath("//input[@role='searchbox']"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void testLogOutIconVerification() {
        WebElement logOutIcon = getDriver().findElement(By.xpath("//a[@href='/logout']"));
        Assert.assertTrue(logOutIcon.isDisplayed());
    }

    @Test
    public void testLogOutIconTextVerification() {
        String logOutIconText = getDriver().findElement(By.xpath("//a[@href='/logout']/span")).getText();
        Assert.assertEquals(logOutIconText, "log out");
    }

    @Test
    public void testDashboardMenuVerification() {
        String dashboardMenu = getDriver().findElement(By.xpath("//li/a[@class='model-link']")).getText();
        Assert.assertEquals(dashboardMenu, "Dashboard");
    }

    @Test
    public void testNewItemSectionVerification() {
        String newItemText = getDriver().findElement(By.xpath("//span[contains(text(), 'New Item')]")).getText();
        Assert.assertEquals(newItemText, "New Item");
    }

    @Test
    public void testRestApiLinkVerification() {
        getDriver().findElement(By.xpath("//a[@href='api/']")).click();
        WebElement expectedElement = getDriver().findElement(By.xpath("//div/h1[contains(text(), 'REST API')]"));
        Assert.assertTrue(expectedElement.isDisplayed());
    }

    @Test
    public void testJenkinsLinkInFooterVerification() {
        WebElement jenkinsLink = getDriver().findElement(By.xpath("//div/a[@href='https://www.jenkins.io/']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Object result = js.executeScript("return arguments[0].target='_self'", jenkinsLink);
        jenkinsLink.click();

        String actualWebPage = getDriver().getCurrentUrl();
        String expectedWebPage = "https://www.jenkins.io/";
        Assert.assertEquals(actualWebPage, expectedWebPage);
    }

    @Test
    public void testNewItemListVerification() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        verifyElementVisible(getDriver().findElement(By.xpath("//div[@id='items']")));

        List<WebElement> newItemProjectTypes = getDriver().findElements(By.xpath("//ul/li/label/span[@class='label']"));
        List<String> expectedProjectTypes = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project",
                "Folder", "Multibranch Pipeline", "Organization Folder");

        for (int i = 0; i < newItemProjectTypes.size(); i++) {
            String actualProjectType = newItemProjectTypes.get(i).getText();
            Assert.assertEquals(actualProjectType, expectedProjectTypes.get(i));
        }
    }

}
