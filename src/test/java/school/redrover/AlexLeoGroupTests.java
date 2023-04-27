package school.redrover;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
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
        getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']")).click();
        WebElement iconButtonsRow = getDriver().
                findElement(By.xpath("//div[contains(@class, 'jenkins-buttons-row')]"));

        Assert.assertTrue(iconButtonsRow.isDisplayed());
    }

    @Test
    public void testVerifyFolderLabelFont() {
        getDriver().findElement(By.xpath("//a[contains(@href,'newJob')]")).click();
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

    @Test
    public void testVerifyLogoJenkinsIsPresentInMenuBar() {
        WebElement logoJenkins = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(logoJenkins.isDisplayed());
    }

    @Test
    public void testJenkinsLogoIsPresent(){
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testJenkinsNameIsPresent(){
        WebElement name = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(name.isDisplayed());
    }

    @Test
    public void testSearchBoxIsPresent(){
        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        Assert.assertTrue(searchBox.isDisplayed());
    }

    @Test
    public void testLogoutIconIsPresent(){
        WebElement logoutIcon = getDriver().findElement(By.xpath("//a[@href='/logout']/*[@class='icon-md']"));
        Assert.assertTrue(logoutIcon.isDisplayed());
    }

    @Test
    public void testLinkContainsText(){
        String logoutLink = getDriver().findElement(By.xpath("//a[@href='/logout']/span")).getText();
        Assert.assertEquals(logoutLink, "log out");
    }

    @Description("Verify to the search field functionality")
    @Test
    public void testSearchField(){
        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys("");
        searchBox.sendKeys(Keys.RETURN);

        Assert.assertTrue(getWait5().until(ExpectedConditions.textToBe
                (By.xpath("//div[@class='jenkins-app-bar__content']/h1"), "Built-In Node")));
    }

    @Test
    public void testNewFreestyleProjectVerification() {
        String nameOfProject = "NewProject2023";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        verifyElementVisible(getDriver().findElement(By.xpath("//div[@id='items']")));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        WebElement projectName = getDriver().findElement(By.xpath("//h1[starts-with(text(), 'Project ')]"));

        Assert.assertEquals(projectName.getText(), "Project " + nameOfProject);
    }

    @Test
    public void testNewFreestyleProjectDisabledVerification() {
        String nameOfProject = "NewProject2023";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        verifyElementVisible(getDriver().findElement(By.xpath("//div[@id='items']")));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#disable-project > button")).click();

        WebElement disabledNote = getDriver().findElement(By.cssSelector("#enable-project"));
        String actualString = disabledNote.getText().substring(0, disabledNote.getText().indexOf("\n"));
        String expectedString = "This project is currently disabled";

        Assert.assertEquals(actualString, expectedString);
    }
    @Test
    public void testAPILinkInTheFooter() {
        WebElement apiLinkButton = getDriver().findElement(By.xpath("//a[text()='REST API']"));
        apiLinkButton.click();

        Assert.assertEquals(getDriver().getTitle(), "Remote API [Jenkins]");
    }

    @Test
    public void testVerifySearchField() {
        Assert.assertTrue(getDriver().findElement(By.id("search-box")).isDisplayed());
    }

    @Test
    public void testVerifyLogOutIcon() {
        Assert.assertTrue(getDriver()
                .findElement(By.cssSelector("header#page-header > div > a:last-of-type > svg")).isDisplayed());
    }

    @Test
    public void testVerifyLogOutLink() {
        WebElement spanLogOut = getDriver().findElement(By.xpath("//header/div/a[@href='/logout']/span"));

        Assert.assertEquals(spanLogOut.getText(), "log out");
    }

    @Test
    public void testVerifyTextInDropDownMenu() {
        WebElement dashboardLink = getDriver().findElement(By.cssSelector("#breadcrumbs > li > a"));

        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
    }

    @Test
    public void testVerifyNewItemSectionPresent() {
        WebElement spanNewItem = getDriver()
                .findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]"));

        Assert.assertEquals(spanNewItem.getText(), "New Item");
    }

    @Test
    public void testVerifyJenkinsLogoIsPresent() {
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testVerifyWordIconJenkinsIsPresent() {
        WebElement wordIcon = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(wordIcon.isDisplayed());
    }

    @Test
    public void testVerifySearchFieldIsPresent() {
        WebElement searchField = getDriver().findElement(By.id("searchform"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void testVerifyLogoutIconIsPresent() {
        WebElement logoutIcon = getDriver().findElement(By.xpath("//a[@href = '/logout']/*[local-name()='svg']"));
        Assert.assertTrue(logoutIcon.isDisplayed());
    }

    @Test
    public void testVerifyLogoutLinkText() {
        WebElement logout = getDriver().findElement(By.xpath("//a[@href = '/logout']/span"));
        Assert.assertEquals(logout.getText(), "log out");
    }

    @Test
    public void testVerifyDashboardDropDownText() {
        WebElement dashboardDropDown = getDriver().findElement(By.xpath("//li[@class = 'jenkins-breadcrumbs__list-item']/a"));
        Assert.assertEquals(dashboardDropDown.getText(), "Dashboard");
    }

    @Test
    public void testVerifyNewItemIcon() {
        WebElement newItem = getDriver().findElement(By.linkText("New Item"));
        Assert.assertTrue(newItem.isDisplayed());
    }

    @Test
    public void testVerifyRestApiLink() {
        WebElement restApiButton = getDriver().findElement(By.linkText("REST API"));
        restApiButton.click();
        WebElement restApiHeader = getDriver().findElement(By.xpath(".//h1[text() = 'REST API']"));

        Assert.assertTrue(getDriver().getCurrentUrl().endsWith("/api/"));
        Assert.assertTrue(restApiHeader.isDisplayed());
    }

    @Test
    public void testVerifyJenkinsLink() {
        WebElement jenkinsLink = getDriver().findElement(By.linkText("Jenkins 2.387.2"));

        String originalWindow = getDriver().getWindowHandle();
        jenkinsLink.click();

        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                if (getDriver().getTitle().equals("Jenkins"))
                    break;
            }
        }
        WebElement jenkinsHeader = getDriver().findElement(By.xpath(".//h1[@class = 'page-title']/span[contains(text(), 'Jenkins')]"));

        Assert.assertTrue(jenkinsHeader.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/");
    }

    @Test
    public void testVerifyNewItemsList() {
        List<String> expectedItems = new ArrayList<>(Arrays.asList("Freestyle project", "Pipeline",
                "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder"));
        WebElement newItem = getDriver().findElement(By.linkText("New Item"));
        newItem.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        List<WebElement> items = wait.
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[@id = 'items']//li//span")));

        Assert.assertEquals(items.size(), expectedItems.size());

        for (int i = 0; i < items.size(); i++) {
            Assert.assertEquals(items.get(i).getText(), expectedItems.get(i));
        }
    }
}

