package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HeaderTest extends BaseTest {

    private static final By NOTIFICATION_ICON = By.id("visible-am-button");
    private static final By MANAGE_JENKINS_LINK = By.xpath("//a[text()='Manage Jenkins']");

    @Test
    public void testHeaderLogoIcon() throws IOException {
        WebElement logoIcon = getDriver().findElement(By.xpath("//*[@id=\"jenkins-head-icon\"]"));
        Assert.assertTrue(logoIcon.isDisplayed());

        String imageSrc = logoIcon.getAttribute("src");
        URL imageUrl = new URL(imageSrc);
        URLConnection urlConnection = imageUrl.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.connect();
        httpURLConnection.setConnectTimeout(1000);

        assertEquals(httpURLConnection.getResponseCode(), 200);
    }

    @Test
    public void testHeaderLogoText() throws IOException {
        WebElement logoText = getDriver().findElement(By.xpath("//*[@id=\"jenkins-name-icon\"]"));
        Assert.assertTrue(logoText.isDisplayed());

        String imageSrc = logoText.getAttribute("src");
        URL imageUrl = new URL(imageSrc);
        URLConnection urlConnection = imageUrl.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.connect();
        httpURLConnection.setConnectTimeout(1000);

        assertEquals(httpURLConnection.getResponseCode(), 200);
    }

    @Test
    public void testSearchTextField() throws InterruptedException {
        Actions hover = new Actions(getDriver());

        WebElement searchTextBox = getDriver().findElement(By.xpath("//*[@id=\"search-box\"]"));
        WebElement searchIcon = getDriver().findElement(By.cssSelector(".main-search__icon-leading svg"));
        WebElement helpButton = getDriver().findElement(By.xpath("//*[@id=\"searchform\"]/a"));
        WebElement helpButtonIcon = getDriver().findElement(By.cssSelector(".main-search__icon-trailing svg"));

        String placeholder = searchTextBox.getAttribute("placeholder");
        String defaultHelpButtonColor = helpButton.getCssValue("color");

        Assert.assertTrue(searchIcon.isDisplayed());
        assertEquals(placeholder, "Search (CTRL+K)");
        Assert.assertTrue(helpButtonIcon.isDisplayed());
        assertEquals(defaultHelpButtonColor, "rgba(115, 115, 140, 1)");

        hover.moveToElement(helpButton).perform();
        Thread.sleep(500);
        String hoverHelpButtonColor = helpButton.getCssValue("color");

        assertEquals(hoverHelpButtonColor, "rgba(64, 64, 64, 1)");
    }

    @Test
    public void testSecurityButton() throws InterruptedException {
        Actions hover = new Actions(getDriver());

        WebElement securityButton = getDriver()
                .findElement(By.xpath("//*[@id=\"visible-sec-am-button\"]"));
        WebElement securityButtonIcon = getDriver().findElement(By.cssSelector("#visible-sec-am-button > svg"));

        Assert.assertTrue(securityButtonIcon.isDisplayed());

        hover.moveToElement(securityButton).perform();
        Thread.sleep(500);
        String hoverSecurityButtonBackground = securityButton.getCssValue("background-color");
        assertEquals(hoverSecurityButtonBackground, "rgba(64, 64, 64, 1)");
    }

    @Test
    public void testAdminButtonBackgroundColorIsPresentWhenMouseOver()  {
        String actualAdminButtonBackgroundColor = new MainPage(getDriver())
                .getHeader()
                .hoverOverAdminButton()
                .getAdminButtonBackgroundColor();

        Assert.assertEquals(actualAdminButtonBackgroundColor, "rgba(64, 64, 64, 1)");
    }

    @Test
    public void testExitButton() throws InterruptedException {
        Actions hover = new Actions(getDriver());

        WebElement exitButton = getDriver().findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[2]"));
        WebElement exitButtonIcon = getDriver()
                .findElement(By.cssSelector("#page-header > div.login.page-header__hyperlinks > a:nth-child(4) > svg"));

        Assert.assertTrue(exitButtonIcon.isDisplayed());

        hover.moveToElement(exitButton).perform();
        Thread.sleep(500);
        String hoverExitButtonBackground = exitButton.getCssValue("background-color");
        String hoverExitButtonUnderline = exitButton.getCssValue("text-decoration-line");

        assertEquals(hoverExitButtonBackground, "rgba(64, 64, 64, 1)");
        assertEquals(hoverExitButtonUnderline, "underline");
    }

    @Test
    public void testCheckIconJenkinsOnHeader(){

        Assert.assertTrue(getDriver().findElement(By.cssSelector("img#jenkins-name-icon")).isDisplayed());

        Assert.assertTrue(getDriver().findElement(By.cssSelector("img#jenkins-head-icon")).isDisplayed());
    }

    @Test
    public void testClickLogoReturnToMainPage(){

        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create a job')]"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"jenkins-home-link\"]"))).click();

        WebElement mainPageText = getDriver().findElement(By.xpath("//h1[contains(text(),'Welcome to Jenkins!')]"));
        Assert.assertEquals(mainPageText.getText(),"Welcome to Jenkins!");
    }

    @Test
    public void testSearchField() {
        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys("");
        searchBox.sendKeys(Keys.RETURN);

        Assert.assertTrue(getWait5().until(ExpectedConditions.textToBe
                (By.xpath("//div[@class='jenkins-app-bar__content']/h1"), "Built-In Node")));
    }

    @Test
    public void testLogOutButtonTransfersBackToLoginPaged() {
        final String expectedHeader = "Welcome to Jenkins!";

        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();
        WebElement actualHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(actualHeader.getText(), expectedHeader);
    }

    @Test
    public void testNotificationAndSecurityIcon() {

        String expectedManageJenkinsPageHeader = "Manage Jenkins";

        String backgroundColorBefore = new MainPage(getDriver())
                .getHeader()
                .getBackgroundColorNotificationIcon();

        String backgroundColorAfter = new MainPage(getDriver())
                .getHeader()
                .clickNotificationIcon()
                .getNotificationIconBackgroundColor();

        String actualManageJenkinsPageHeader = new ManageJenkinsPage(getDriver())
                .clickManageJenkinsLink()
                .getActualHeader();

        Assert.assertNotEquals(backgroundColorBefore, backgroundColorAfter, " The color of icon is not changed");
        Assert.assertEquals(actualManageJenkinsPageHeader,expectedManageJenkinsPageHeader, " The page is not correct");
    }

    @Test
    public void testReturnToTheDashboardPageAfterCreatingTheItem() {
        final List<String> listItemName = new ArrayList<>(List.of("Test Item", "Second"));

        TestUtils.createFreestyleProject(this, listItemName.get(0), true);
        TestUtils.createFreestyleProject(this, listItemName.get(1), false);

        boolean isPageOpen = new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver()))
                .getHeader()
                .clickLogo()
                .isMainPageOpen();

        Assert.assertTrue(isPageOpen, "Wrong title or wrong page");

        List<String> listJob = new MainPage(getDriver())
                .getJobList();

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < listJob.size(); i++) {
            softAssert.assertTrue(listJob.contains(listItemName.get(i)),
                    "The result list doesn't contain the item " + listItemName.get(i));
        }
        softAssert.assertAll();
    }

    @Test
    public void testOpenBuildsTabFromDropdownMenu() {
        WebElement page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openBuildsTabFromAdminDropdownMenu();

        Assert.assertTrue(page.isDisplayed(), "Page should be displayed");
    }

    @Test
    public void testAdminPageIsAvailable() {

        WebElement adminButton = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/user/admin']")));
        adminButton.click();

        WebElement adminPageSign = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel > div:nth-child(4)")));
        assertEquals(adminPageSign.getText(),"Jenkins User ID: admin");
    }
    @Test
    public void testButtonNotificationsWorks() {

        WebElement notificationsButton = getDriver().findElement(By.xpath("//a[@id='visible-am-button']"));
        notificationsButton.click();

        WebElement manageJenkinsString = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#visible-am-list > p > a")));
        assertEquals(manageJenkinsString.getText(),"Manage Jenkins");
    }

    @Test
    public void testOfNotificationIconColorChange() {
        String notificationIconColorBefore = new MainPage(getDriver())
                .getHeader()
                .getNotificationIconBackgroundColor();

        String notificationIconColorAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverNotificationIcon()
                .getNotificationIconBackgroundColor();

        Assert.assertNotEquals(notificationIconColorAfter, notificationIconColorBefore,
                "The Notification icon background has not changed");
    }

    @Test
    public void testOfAdminButtonColorChange() {
        String adminButtonColorBefore = new MainPage(getDriver())
                .getHeader()
                .getAdminButtonBackgroundColor();

        String adminButtonColorAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverAdminButton()
                .getAdminButtonBackgroundColor();

        Assert.assertNotEquals(adminButtonColorAfter, adminButtonColorBefore,
                "The Admin button background has not changed");
    }

    @Test
    public void testOfLogOutButtonColorChange() {
        String logOutButtonColorBefore = new MainPage(getDriver())
                .getHeader()
                .getLogOutButtonBackgroundColor();

        String logOutButtonColorAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverLogOutButton()
                .getLogOutButtonBackgroundColor();

        Assert.assertNotEquals(logOutButtonColorAfter, logOutButtonColorBefore,
                "The LogOut button background has not changed");
    }

    @Test
    public void testAppearanceOfPopUpMenuWhenClickingOnNotificationIcon() {
        boolean isPopUpScreenDisplayed = new MainPage(getDriver())
                .getHeader()
                .clickNotificationIcon()
                .isPopUpNotificationScreenDisplayed();

        Assert.assertTrue(isPopUpScreenDisplayed, "The pop-up Notification icon screen is not displayed");
    }

    @Test
    public void testAppearanceOfPopUpMenusWhenClickingOnAdminIcon() {
        boolean isPopUpScreenDisplayed = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .isAdminDropdownScreenDisplayed();

        Assert.assertTrue(isPopUpScreenDisplayed, "The pop-up Admin icon screen is not displayed");
    }

    @Ignore
    @Test
    public void testOpenTheLinkOfManageJenkinsLinkFromThePopUpScreen(){
        getDriver().findElement(NOTIFICATION_ICON).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("visible-am-list")));
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Manage Jenkins')]"))).click();

        Assert.assertTrue(
                getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("main-panel"))).isDisplayed());
    }

    @Test
    public void testAdminButtonIsUnderlinedWhenMouseOver() {

        String textUnderlineAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverAdminButton()
                .getAdminTextDecorationValue();

        Assert.assertTrue(textUnderlineAfter.contains("underline"));
    }

    @Test
    public void testLogoutButtonColorChange() {
        WebElement logoutLink = getDriver().findElement(By.linkText("log out"));
        Actions actions = new Actions((getDriver()));
        actions.moveToElement(logoutLink).perform();
        getWait5().until(ExpectedConditions.attributeToBeNotEmpty(logoutLink, "text-decoration"));
        String expectedColor = "rgba(245, 245, 245, 1)";
        String actualColor = logoutLink.getCssValue("color");

        assertEquals(actualColor, expectedColor);
    }
    public void iconChangeColor(By el){
        String colorBefore = getDriver().findElement(el).getCssValue("background-color");
        String colorAfter = "";
        new Actions(getDriver()).moveToElement(getDriver().findElement(el)).perform();
        colorAfter = getDriver().findElement(el).getCssValue("background-color");

        Assert.assertNotEquals(colorBefore, colorAfter);
    }

    @Ignore
    @Test
    public void testNotificationIcon(){
        iconChangeColor(NOTIFICATION_ICON);
        getDriver().findElement(NOTIFICATION_ICON).click();
        String actualRes = getDriver().findElement(MANAGE_JENKINS_LINK).getText();
        Assert.assertEquals(actualRes, "Manage Jenkins");
    }

    @Test
    public void testConfigureTabFromDropdownMenu() {
        WebElement page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openConfigureTabFromAdminDropdownMenu();

        Assert.assertTrue(page.isDisplayed(), "Page should be displayed");
    }

    @Test
    public void testMyViewsTabFromDropdownMenu() {
        WebElement page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openMyViewsTabFromAdminDropdownMenu();

        Assert.assertTrue(page.isDisplayed(), "Page should be displayed");
    }

    @Test
    public void testCredentialsTabFromDropdownMenu() {
        WebElement page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openCredentialsTabFromAdminDropdownMenu();

        Assert.assertTrue(page.isDisplayed(), "Page should be displayed");
    }
}
