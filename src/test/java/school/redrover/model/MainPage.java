package school.redrover.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.*;

public class MainPage extends BaseDashboardPage<MainPage> {

    @FindBy(css = "[href='/manage']")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[@href='/me/my-views']")
    private WebElement myViews;

    @FindBy(xpath = "//div[@class='tippy-box']//td[@align='left' and not(contains(@class, 'jenkins-table__icon'))]")
    private WebElement tooltipDescription;

    @FindBy(xpath = "//h1[text()='Welcome to Jenkins!']")
    private WebElement welcomeToJenkins;

    @FindBy(xpath = "//div[@class='empty-state-block']/h1")
    private WebElement welcomeJenkins;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ManageJenkinsPage clickManageJenkinsPage() {
        manageJenkins.click();
        return new ManageJenkinsPage(getDriver());
    }

    public MyViewsPage clickMyViewsSideMenuLink() {
        getWait5().until(ExpectedConditions.elementToBeClickable(myViews)).click();
        return new MyViewsPage(getDriver());
    }

    public boolean isMainPageOpen() {
        return getWait5().until(ExpectedConditions.titleContains("Dashboard [Jenkins]"));
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public String getTooltipDescription(){
        return getWait10().until(ExpectedConditions.visibilityOf(tooltipDescription)).getText();
    }

    public NodePage clickOnNodeName(String name) {
        getDriver().findElement(By.xpath("//span[text()='" + name + "']")).click();
        return new NodePage(getDriver());
    }

    public boolean WelcomeIsDisplayed() {
        return welcomeToJenkins.isDisplayed();
    }

    public String getWelcomeText() {
        return welcomeJenkins.getText();
    }
}
