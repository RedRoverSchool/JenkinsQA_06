package school.redrover.model.component;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BasePage;
import school.redrover.runner.TestUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainHeaderComponent<Page extends BasePage<?, ?>> extends BaseComponent<Page> {

    @FindBy(id = "jenkins-head-icon")
    private WebElement logoIcon;

    @FindBy(id = "jenkins-name-icon")
    private WebElement logoText;

    @FindBy(xpath = "//a[@href='/user/admin']/button")
    private WebElement adminDropdown;

    @FindBy(id = "visible-am-list")
    private WebElement popUpScreenNotification;

    @FindBy(id = "visible-am-button")
    private WebElement notificationIcon;

    @FindBy(id = "breadcrumb-menu")
    private WebElement adminDropdownMenu;

    @FindBy(xpath = "//a[contains(text(),'Manage Jenkins')]")
    private WebElement manageJenkinsLinkFromPopUp;

    @FindBy(xpath = "//div[@id='breadcrumb-menu']//span[.='Builds']")
    private WebElement buildsTabFromAdminDropdownMenu;

    @FindBy(xpath = "//h1[.='Builds for admin']")
    private WebElement buildsTableTitle;

    @FindBy(xpath = "//span[. ='Configure']")
    private WebElement configureTabFromAdminDropdownMenu;

    @FindBy(xpath = "//div[@class='bd']//span[.='My Views']")
    private WebElement myViewsTabFromAdminDropdownMenu;

    @FindBy(xpath = "//li/a[@href='/user/admin/my-views/']")
    private WebElement myViewsLink;

    @FindBy(xpath = "//span[.='Credentials']")
    private WebElement credentialsTabFromAdminDropdownMenu;

    @FindBy(xpath = "//h1[.='Credentials']")
    private WebElement credentialsTitle;

    @FindBy(xpath = "//a[@class='model-link']/span[contains(@class,'hidden-xs')]")
    private WebElement currentUserName;

    @FindBy(xpath = "//a[text()='Jenkins 2.387.2']")
    private WebElement jenkinsVersionLink;

    @FindBy(id = "search-box")
    private WebElement searchBox;

    @FindBy(css = ".main-search__icon-trailing svg")
    private WebElement helpIcon;

    @FindBy(css = ".main-search__icon-leading svg")
    private WebElement searchBoxIcon;

    @FindBy(xpath = "//div[@id='search-box-completion']//li")
    private List<WebElement> searchResultList;

    @FindBy(css = "#visible-am-list > p > a")
    private WebElement headerManageJenkins;

    @FindBy(css = "#visible-sec-am-button > svg")
    private WebElement securityButtonIcon;

    @FindBy(xpath = "//*[@id='visible-sec-am-button']")
    private WebElement headerSecurityButton;

    @FindBy(css = "#page-header > div.login.page-header__hyperlinks > a:nth-child(4) > svg")
    private WebElement exitButtonIcon;

    @FindBy(xpath = "//*[@id='page-header']/div[3]/a[2]")
    private WebElement logOutLink;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[@href='/user/admin']")
    private WebElement adminButton;

    @FindBy(xpath = "//a[contains(@href,'api')]")
    private WebElement restApi;

    public MainHeaderComponent(Page page) {
        super(page);
    }

    private void hoverOver(WebElement webElement) {
        new Actions(getDriver())
                .moveToElement(webElement)
                .pause(Duration.ofMillis(300))
                .perform();
    }

    private String getIconBackgroundColor(WebElement webElement) {
        return webElement.getCssValue("background-color");
    }

    public MainPage clickLogo() {
        getWait2().until(ExpectedConditions.visibilityOf(logoIcon)).click();

        return new MainPage(getDriver());
    }

    public boolean isDisplayedLogoIcon() {
        return logoIcon.isDisplayed();
    }

    public boolean isDisplayedLogoText() {
        return logoText.isDisplayed();
    }

    public MainHeaderComponent<Page> clickNotificationIcon() {
        getWait2().until(ExpectedConditions.elementToBeClickable(notificationIcon)).click();

        return this;
    }

    public MainHeaderComponent<Page> clickAdminDropdownMenu() {
        TestUtils.clickByJavaScript(this, adminDropdown);

        return this;
    }

    public boolean isPopUpNotificationScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOf(popUpScreenNotification)).isDisplayed();
    }

    public boolean isAdminDropdownScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOf(adminDropdownMenu)).isDisplayed();
    }

    public MainHeaderComponent<Page> hoverOverNotificationIcon() {
        hoverOver(notificationIcon);

        return this;
    }

    public MainHeaderComponent<Page> hoverOverAdminButton() {
        hoverOver(adminButton);

        return this;
    }

    public MainHeaderComponent<Page> hoverOverLogOutButton() {
        hoverOver(logOutButton);

        return this;
    }

    public String getNotificationIconBackgroundColor() {
        return getIconBackgroundColor(notificationIcon);
    }

    public String getAdminButtonBackgroundColor() {
        return getIconBackgroundColor(adminButton);
    }

    public String getLogOutButtonBackgroundColor() {
        return getIconBackgroundColor(logOutButton);
    }

    public String getAdminTextDecorationValue() {
        WebElement adminLink = getWait5().until(ExpectedConditions.visibilityOf(adminButton));

        return adminLink.getCssValue("text-decoration");
    }

    public String getLogOutTextDecorationValue() {
        WebElement logoutLink = logOutButton;
        getWait5().until(ExpectedConditions.attributeToBeNotEmpty(logoutLink, "text-decoration"));

        return logoutLink.getCssValue("color");
    }

    public ManageJenkinsPage clickManageLinkFromPopUp() {
        getWait2().until(ExpectedConditions.elementToBeClickable(manageJenkinsLinkFromPopUp)).click();

        return new ManageJenkinsPage(getDriver());
    }

    public boolean openBuildsTabFromAdminDropdownMenuIsDisplayed() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buildsTabFromAdminDropdownMenu)).click();

        return getWait5().until(ExpectedConditions.visibilityOf(buildsTableTitle)).isDisplayed();
    }

    public UserConfigPage openConfigureTabFromAdminDropdownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(configureTabFromAdminDropdownMenu)).click();

        return new UserConfigPage(new StatusUserPage(getDriver()));
    }

    public boolean openMyViewsTabFromAdminDropdownMenuIsDisplayed() {
        getWait5().until(ExpectedConditions.elementToBeClickable(myViewsTabFromAdminDropdownMenu)).click();

        return getWait5().until(ExpectedConditions.visibilityOf(myViewsLink)).isDisplayed();
    }

    public boolean openCredentialsTabFromAdminDropdownMenuIsDisplayed() {
        getWait5().until(ExpectedConditions.elementToBeClickable(credentialsTabFromAdminDropdownMenu)).click();

        return getWait5().until(ExpectedConditions.visibilityOf(credentialsTitle)).isDisplayed();
    }

    public String getCurrentUserName() {
        return currentUserName.getAttribute("innerText");
    }

    public String getBackgroundColorNotificationIcon() {
        return notificationIcon.getCssValue("background-color");
    }

    public String getLinkVersion() {
        return jenkinsVersionLink.getText();
    }

    public LoginPage clickLogoutButton() {
        logOutButton.click();

        return new LoginPage(getDriver());
    }

    public MainHeaderComponent<Page> typeToSearch(String search) {
        getWait5().until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(search);

        return this;
    }

    public BuiltInNodePage sendSearchBox() {
        searchBox.sendKeys(Keys.RETURN);

        return new BuiltInNodePage(getDriver());
    }

    public UserPage sendSearchBoxUser(String name) {
        searchBox.sendKeys(name);
        searchBox.sendKeys(Keys.RETURN);
        return new UserPage(getDriver());
    }

    public String getAttributeFromSearchBox() {
        return searchBox.getAttribute("placeholder");
    }

    public boolean isDisplayedHelpIcon() {
        return helpIcon.isDisplayed();
    }

    public boolean isDisplayedSearchBoxIcon() {
        return searchBoxIcon.isDisplayed();
    }

    public List<String> getListOfSearchResult() {
        List<String> searchResult = new ArrayList<>();
        for (WebElement webElement : searchResultList) {
            if (!webElement.getText().equals("")) {
                searchResult.add(webElement.getText());
            }
        }
        return searchResult;
    }

    public boolean isSearchResultContainsText(String text) {
        List<String> searchResult = getListOfSearchResult();
        for (String str : searchResult) {
            if (!str.toLowerCase().contains(text.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public String getTextFromHeaderManageJenkins() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(headerManageJenkins)).getText();
    }

    public boolean getSecurityButtonOnHeader() {
        return securityButtonIcon.isDisplayed();
    }

    public String getBackgroundSecurityButton() {
        WebElement securityButton = headerSecurityButton;

        Actions hover = new Actions(getDriver());
        hover.moveToElement(securityButton).perform();

        return securityButton.getCssValue("background-color");
    }

    public boolean iconExitButton() {
        return exitButtonIcon.isDisplayed();
    }

    public String getUnderLineExitButton() {
        WebElement exitButton = logOutLink;

        Actions hover = new Actions(getDriver());
        hover.moveToElement(exitButton).perform();

        return exitButton.getCssValue("text-decoration-line");
    }

    public String getBackgroundExitButton() {
        WebElement exitButton = logOutLink;

        Actions hover = new Actions(getDriver());
        hover.moveToElement(exitButton).perform();

        return exitButton.getCssValue("background-color");
    }

    public LoginPage clickLogOUTButton() {
        logOutButton.click();

        return new LoginPage(getDriver());
    }

    public AdminPage clickOnAdminButton() {
        getWait2().until(ExpectedConditions.visibilityOf(adminButton)).click();

        return new AdminPage(getDriver());
    }

    public RestApiPage clickOnRestApiLink() {
        restApi.click();
        return new RestApiPage(getDriver());
    }

    public MainPage clickLogoWithPause() {
        new Actions(getDriver())
                .moveToElement(logoIcon)
                .pause(3000)
                .click()
                .perform();

        return new MainPage(getDriver());
    }
}
