package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HeaderTest extends BaseTest {
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
    public void testSearchFieldPlaceholder() {
        Assert.assertEquals(getDriver().findElement(By.id("search-box")).getAttribute("placeholder"), "Search (CTRL+K)");
    }

    @Test
    public void testSearchFieldAutocomplete() {
        Assert.assertEquals(getDriver().findElement(By.id("search-box")).getAttribute("autocomplete"), "off");
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
    public void testUserButton() throws InterruptedException {
        Actions hover = new Actions(getDriver());

        WebElement userButton = getDriver().findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[1]"));
        WebElement userButtonIcon = getDriver()
                .findElement(By.cssSelector("#page-header > div.login.page-header__hyperlinks > a.model-link > svg"));
        WebElement dropDownButton = getDriver()
                .findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[1]/button"));

        Assert.assertTrue(userButtonIcon.isDisplayed());
        Assert.assertTrue(dropDownButton.isDisplayed());

        hover.moveToElement(userButton).perform();
        Thread.sleep(700);
        String hoverUserButtonBackground = userButton.getCssValue("background-color");
        String hoverUserButtonUnderline = userButton.getCssValue("text-decoration-line");

        assertEquals(hoverUserButtonBackground, "rgba(64, 64, 64, 1)");
        assertEquals(hoverUserButtonUnderline, "underline");
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
}