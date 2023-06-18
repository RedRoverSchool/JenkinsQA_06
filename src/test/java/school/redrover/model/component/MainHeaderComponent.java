package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BasePage;
import school.redrover.runner.TestUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainHeaderComponent<Page extends BasePage<?, ?>> extends BaseComponent<Page> {

    public MainHeaderComponent(Page page) {
        super(page);
    }

    private static final By NOTIFICATION_ICON = By.id("visible-am-button");
    private static final By ADMIN_BUTTON = By.xpath("//a[@href='/user/admin']");
    private static final By LOGOUT_BUTTON = By.xpath("//a[@href='/logout']");

    private void hoverOver(By locator) {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(locator))
                .pause(Duration.ofMillis(300))
                .perform();
    }

    private String getIconBackgroundColor(By locator) {
        return getDriver().findElement(locator).getCssValue("background-color");
    }

    public MainPage clickLogo() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-head-icon"))).click();
        return new MainPage(getDriver());
    }

    public MainHeaderComponent<Page> clickNotificationIcon() {
        getWait2().until(ExpectedConditions.elementToBeClickable(NOTIFICATION_ICON)).click();
        return this;
    }

    public MainHeaderComponent<Page> clickAdminDropdownMenu() {
        TestUtils.clickByJavaScript(this, getDriver().findElement(By.xpath("//a[@href='/user/admin']/button")));
        return this;
    }

    public boolean isPopUpNotificationScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("visible-am-list"))).isDisplayed();
    }

    public boolean isAdminDropdownScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("breadcrumb-menu"))).isDisplayed();
    }

    public MainHeaderComponent<Page> hoverOverNotificationIcon() {
        hoverOver(NOTIFICATION_ICON);
        return this;
    }

    public MainHeaderComponent<Page> hoverOverAdminButton() {
        hoverOver(ADMIN_BUTTON);
        return this;
    }

    public MainHeaderComponent<Page> hoverOverLogOutButton() {
        hoverOver(LOGOUT_BUTTON);
        return this;
    }

    public String getNotificationIconBackgroundColor() {
        return getIconBackgroundColor(NOTIFICATION_ICON);
    }

    public String getAdminButtonBackgroundColor() {
        return getIconBackgroundColor(ADMIN_BUTTON);
    }

    public String getLogOutButtonBackgroundColor() {
        return getIconBackgroundColor(LOGOUT_BUTTON);
    }

    public String getAdminTextDecorationValue() {
        WebElement adminLink = getWait5().until(ExpectedConditions.visibilityOfElementLocated(ADMIN_BUTTON));
        return adminLink.getCssValue("text-decoration");
    }

    public String getLogOutTextDecorationValue(){
        WebElement logoutLink = getDriver().findElement(LOGOUT_BUTTON);
        getWait5().until(ExpectedConditions.attributeToBeNotEmpty(logoutLink, "text-decoration"));

        return logoutLink.getCssValue("color");
    }

    public ManageJenkinsPage clickManageLinkFromPopUp(){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Manage Jenkins')]"))).click();

        return new ManageJenkinsPage(getDriver());
    }

    public boolean openBuildsTabFromAdminDropdownMenuIsDisplayed () {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div[@id='breadcrumb-menu']//span[.='Builds']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//h1[.='Builds for admin']"))).isDisplayed();
    }

    public UserConfigPage openConfigureTabFromAdminDropdownMenu () {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//span[. ='Configure']"))).click();
        return new UserConfigPage(new StatusUserPage(getDriver()));
    }

    public boolean openMyViewsTabFromAdminDropdownMenuIsDisplayed() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div[@class='bd']//span[.='My Views']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//a[@href='/user/admin/my-views/']"))).isDisplayed();
    }

    public boolean openCredentialsTabFromAdminDropdownMenuIsDisplayed () {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//span[.='Credentials']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//h1[.='Credentials']"))).isDisplayed();
    }

    public String getCurrentUserName() {
        return getDriver().findElement(By.xpath("//a[@class='model-link']/span[contains(@class,'hidden-xs')]")).getAttribute("innerText");
    }

    public String getBackgroundColorNotificationIcon() {
        return getDriver().findElement(By.id("visible-am-button")).getCssValue("background-color");
    }

    public String getLinkVersion() {
        return getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']")).getText();
    }

    public LoginPage clickLogoutButton() {
        getDriver().findElement(LOGOUT_BUTTON).click();
        return new LoginPage(getDriver());
    }

    public UserPage clickUserName() {
        getDriver().findElement(By.xpath("//div[3]/a[1]/span")).click();
        return new UserPage(getDriver());
    }

    public MainHeaderComponent<Page> typeToSearch(String search){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("search-box"))).sendKeys(search);
        return this;
    }

    public List<String> getListOfSearchResult(){
        List<String> searchResult = new ArrayList<>();
        List<WebElement> webElementList = getDriver().findElements(By.xpath("//div[@id='search-box-completion']//li"));
        for(WebElement webElement : webElementList){
            if (!webElement.getText().equals("")){
                searchResult.add(webElement.getText());
            }
        }

        return searchResult;
    }

    public boolean isSearchResultContainsText(String text){
        List<String> searchResult = getListOfSearchResult();
        for(String str : searchResult){
            if(!str.toLowerCase().contains(text.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public String getTextFromHeaderManageJenkins(){

        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#visible-am-list > p > a"))).getText();
    }

    public boolean getSecurityButtonOnHeader(){

        return getDriver().findElement(By.cssSelector("#visible-sec-am-button > svg")).isDisplayed();
    }

    public String getBackgroundSecurityButton(){
        WebElement securityButton = getDriver()
                .findElement(By.xpath("//*[@id=\"visible-sec-am-button\"]"));

        Actions hover = new Actions(getDriver());
        hover.moveToElement(securityButton).perform();

        return securityButton.getCssValue("background-color");
    }

    public boolean iconExitButton(){

        return getDriver()
                .findElement(By.cssSelector("#page-header > div.login.page-header__hyperlinks > a:nth-child(4) > svg"))
                .isDisplayed();
    }

    public String getUnderLineExitButton(){
        WebElement exitButton = getDriver().findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[2]"));

        Actions hover = new Actions(getDriver());
        hover.moveToElement(exitButton).perform();

        return exitButton.getCssValue("text-decoration-line");
    }

    public String getBackgroundExitButton(){
        WebElement exitButton = getDriver().findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[2]"));

        Actions hover = new Actions(getDriver());
        hover.moveToElement(exitButton).perform();

        return exitButton.getCssValue("background-color");
    }

    public LoginPage clickLogOUTButton(){
        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();

        return new LoginPage(getDriver());
    }

    public AdminPage clickOnAdminButton() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/user/admin']"))).click();

        return new AdminPage(getDriver());
    }
}
