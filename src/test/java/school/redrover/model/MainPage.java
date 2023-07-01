package school.redrover.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.*;

public class MainPage extends BaseDashboardPage<MainPage> {

    @FindBy(xpath = "//div[@id = 'breadcrumbBar']//a[@href='/']")
    private WebElement dashboard;

    @FindBy(xpath = "//div[@id = 'breadcrumbBar']//a[@href='/']//button")
    private WebElement sliderDashboard;

    @FindBy(xpath = "//*[@id='yui-gen4']/a/span")
    private WebElement manageJenkinsInSliderDashboard;

    @FindBy(css = "[href='/manage']")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[@href='/me/my-views']")
    private WebElement myViews;

    @FindBy(xpath = "//a[contains(@href,'api')]")
    private WebElement restApi;

    @FindBy(css = "svg[title='Folder']")
    private WebElement iconFolder;

    @FindBy(xpath = "//h1[text()='Welcome to Jenkins!']")
    private WebElement welcomeToJenkins;

    @FindBy(xpath = "//div[@class='empty-state-block']/h1")
    private WebElement welcomeJenkins;

    @FindBy(id = "search-box")
    private WebElement searchbox;

    @FindBy(xpath = "//div[@class='tippy-box']//td[@align='left' and not(contains(@class, 'jenkins-table__icon'))]")
    private WebElement tooltipDescription;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private void  clickOnSliderDashboardInDropDownMenu() {
        Actions actions = new Actions(getDriver());

        actions.moveToElement(dashboard).perform();
        actions.moveToElement(sliderDashboard).perform();
        sliderDashboard.sendKeys(Keys.RETURN);
    }

    public ManageJenkinsPage clickManageJenkinsOnDropDown() {
        clickOnSliderDashboardInDropDownMenu();
        getWait5().until(ExpectedConditions.elementToBeClickable(manageJenkinsInSliderDashboard)).click();
        return new ManageJenkinsPage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkinsPage() {
        manageJenkins.click();
        return new ManageJenkinsPage(getDriver());
    }

    public MyViewsPage clickMyViewsSideMenuLink() {
        getWait5().until(ExpectedConditions.elementToBeClickable(myViews)).click();
        return new MyViewsPage(getDriver());
    }

    public RestApiPage clickOnRestApiLink() {
        restApi.click();
        return new RestApiPage(getDriver());
    }

    public boolean isMainPageOpen() {
        return getWait5().until(ExpectedConditions.titleContains("Dashboard [Jenkins]"));
    }

    public boolean iconFolderIsDisplayed(){
        return iconFolder.isDisplayed();
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public boolean WelcomeIsDisplayed() {
        return welcomeToJenkins.isDisplayed();
    }

    public String  getWelcomeText() {
        return welcomeJenkins.getText();
    }

    public BuiltInNodePage sendSearchbox(){
        searchbox.sendKeys(Keys.RETURN);

        return new BuiltInNodePage(getDriver());
    }

    public UserPage sendSearchboxUser(String name){
        WebElement searchField = searchbox;
        searchField.sendKeys(name);
        searchField.sendKeys(Keys.RETURN);

        return new UserPage(getDriver());
    }

    public MainPage hoverOverWeather(String jobName){
        WebElement weather = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//tr[@id = 'job_%s']/td[contains(@class,'healthReport')]", jobName))));
        new Actions(getDriver())
                .moveToElement(weather)
                .perform();
        return this;
    }

    public String getTooltipDescription(){
        return getWait10().until(ExpectedConditions.visibilityOf(tooltipDescription)).getText();
    }

    public NodePage clickOnNodeName(String name) {
        getDriver().findElement(By.xpath("//span[text()='" + name + "']")).click();
        return new NodePage(getDriver());
    }
}
