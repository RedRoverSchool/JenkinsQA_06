package school.redrover.model;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.time.Duration;
import java.util.List;

public class ManageJenkinsPage extends BaseMainHeaderPage<ManageJenkinsPage> {

    @FindBy(tagName = "html")
    private WebElement page;

    @FindBy(tagName = "h1")
    private WebElement headline;

    @FindBy(linkText = "New Item")
    private WebElement newItemOnSideMenu;

    @FindBy(xpath = "//a[@href='/computer/']")
    private WebElement manageNodesOnSideMenu;

    @FindBy(xpath = "//a[text()='Manage Jenkins']")
    private WebElement manageJenkinsLink;

    @FindBy(id = "settings-search-bar")
    private WebElement searchBarOnManageJenkinsPage;

    @FindBy(xpath = "//div[@class = 'jenkins-search__results']")
    private WebElement searchResults;

    @FindBy(css = "div.jenkins-search__results-container--visible")
    private WebElement searchResultsIsVisible;

    @FindBy(css = ".jenkins-search__results-item--selected")
    private WebElement itemInDropdownSearchResults;

    @FindBy(css = ".jenkins-search__results-item--selected")
    private List<WebElement> listItemsInDropdownMenuSearchResults;

    @FindBy(xpath = "//div[@class = 'jenkins-search__results']/a")
    private List<WebElement> listItemsInDropdownMenuSearchResultsWithTagA;

    @FindBy (xpath = "//div[@class = 'jenkins-search__results']//*[contains(text(), 'Configure System')]")
    private WebElement configureSystemLinkInSearchResult;

    @FindBy(xpath = "//a[@href='configureSecurity']")
    private WebElement configureGlobalSecurityLink;

    @FindBy(xpath = "//a[@href='securityRealm/']")
    private WebElement manageUsersLink;

    @FindBy(xpath = "//a[@href='pluginManager']")
    private WebElement managePluginsLink;

    @FindBy(xpath = "//h1[normalize-space()='Configure System']")
    private WebElement configureSystem;

    @FindBy(xpath = "//h1[normalize-space(.)= 'Manage Jenkins']")
    private WebElement manageJenkins;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public ManageJenkinsPage inputToSearchField(String text) {
        getWait2().until(ExpectedConditions.elementToBeClickable(searchBarOnManageJenkinsPage));
        searchBarOnManageJenkinsPage.sendKeys(text);
        return new ManageJenkinsPage(getDriver());
    }

    public ManageJenkinsPage inputToSearchFieldUsingKeyboardShortcut(String text) {
        page.sendKeys(Keys.chord("/"));
        new Actions(getDriver()).sendKeys(text).perform();
        return this;
    }

    public String getNoResultTextInSearchField() {
        Actions action = new Actions(getDriver());
        action.moveToElement(searchResultsIsVisible).perform();
        return searchResultsIsVisible.getText();
    }

    public ManageUsersPage clickManageUsers() {
        getWait2().until(ExpectedConditions.elementToBeClickable(manageUsersLink)).click();
        return new ManageUsersPage(getDriver());
    }

    public String getActualHeader() {
        getWait5().until(ExpectedConditions.visibilityOf(headline));
        return headline.getText();
    }

    public String getDropdownResultsInSearchField() {
        return getWait10().until(ExpectedConditions.elementToBeClickable(searchResults)).getText();
    }

    public NewJobPage clickNewItem() {
        newItemOnSideMenu.click();
        return new NewJobPage(getDriver());
    }

    public ConfigureGlobalSecurityPage clickConfigureGlobalSecurity() {
        getWait2().until(ExpectedConditions.elementToBeClickable(configureGlobalSecurityLink)).click();
        return new ConfigureGlobalSecurityPage(getDriver());
    }

    public ManageNodesPage clickManageNodes() {
        getWait2().until(ExpectedConditions.elementToBeClickable(manageNodesOnSideMenu));
        manageNodesOnSideMenu.click();
        return new ManageNodesPage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkinsLink() {
        new Actions(getDriver())
                .pause(Duration.ofMillis(300))
                .click(getWait5().until(ExpectedConditions.elementToBeClickable(manageJenkinsLink))).perform();
        return new ManageJenkinsPage(getDriver());
    }

    public ManagePluginsPage clickManagePlugins() {
        getWait2().until(ExpectedConditions.elementToBeClickable(managePluginsLink)).click();
        return new ManagePluginsPage(getDriver());
    }

    public ManageJenkinsPage selectOnTheFirstLineInDropdown(String text) {

        getWait5().until(ExpectedConditions.visibilityOfAllElements(itemInDropdownSearchResults));

        for (WebElement option : listItemsInDropdownMenuSearchResults) {
            if (option.getText().equals(text)) {
                option.click();
                break;
            }
        }
        return this;
    }

    public ManageJenkinsPage selectAllDropdownResultsFromSearchField() {
        Actions action = new Actions(getDriver());
        action.moveToElement(searchResultsIsVisible).perform();
        return this;
    }

    public boolean isDropdownResultsFromSearchFieldContainsTextToSearch(String text) {
        for (WebElement option : listItemsInDropdownMenuSearchResultsWithTagA) {
            if (!option.getText().toLowerCase().contains(text)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDropdownResultsFromSearchFieldLinks() {
        for (WebElement option : listItemsInDropdownMenuSearchResultsWithTagA) {
            if (!"a".equals(option.getTagName())) {
                return false;
            }
        }
        return true;
    }

    public String getConfigureSystemPage() {
        getWait2().until(ExpectedConditions.visibilityOf(configureSystem));
        return configureSystem.getText();
    }

    public ConfigureSystemPage clickConfigureSystemFromSearchDropdown() {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(searchResults));
        configureSystemLinkInSearchResult.click();
        return new ConfigureSystemPage(getDriver());
    }
}
