package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BasePage;

import java.time.Duration;

public class MainHeaderComponent<Page extends BasePage<?>> extends BaseComponent<Page> {

    public MainHeaderComponent(Page page) {
        super(page);
    }

    private static final By NOTIFICATION_ICON = By.id("visible-am-button");
    private static final By ADMIN_ICON = By.xpath("//a[@href='/user/admin']");
    private static final By LOGOUT_ICON = By.xpath("//a[@href='/logout']");


    public MainPage clickLogo() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-head-icon"))).click();
        return new MainPage(getDriver());
    }

    public Page clickNotificationIcon() {
        getWait2().until(ExpectedConditions.elementToBeClickable(NOTIFICATION_ICON)).click();
        return getPage();
    }

    public Page clickAdminIcon() {
        getDriver().findElement(ADMIN_ICON).click();
        return getPage();
    }

    public boolean isPopUpNotificationScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("visible-am-list"))).isDisplayed();
    }

    public boolean isPopUpAdminScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/user/admin']/button[@class='jenkins-menu-dropdown-chevron']"))).isDisplayed();
    }

    private void hoverOverIcon(By locator) {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(locator))
                .pause(Duration.ofMillis(300))
                .perform();
    }

    public MainHeaderComponent hoverOverNotificationIcon() {
        hoverOverIcon(NOTIFICATION_ICON);
        return this;
    }

    public MainHeaderComponent<Page> hoverOverAdminIcon() {
        hoverOverIcon(ADMIN_ICON);
        return this;
    }

    public MainHeaderComponent hoverOverLogOutIcon() {
        hoverOverIcon(LOGOUT_ICON);
        return this;
    }

    private String getIconBackgroundColor(By locator) {
        return getDriver().findElement(locator).getCssValue("background-color");
    }

    public String getNotificationIconColor() {
        return getIconBackgroundColor(NOTIFICATION_ICON);
    }

    public String getAdminIconColor() {
        return getIconBackgroundColor(ADMIN_ICON);
    }

    public String getLogOutIconColor() {
        return getIconBackgroundColor(LOGOUT_ICON);
    }

    public String getAdminTextDecorationValue() {
        WebElement adminLink = getWait5().until(ExpectedConditions.visibilityOfElementLocated(ADMIN_ICON));
        return adminLink.getCssValue("text-decoration");
    }

    public MainHeaderComponent<Page> expandAdminDropdownMenu() {
        WebElement dropDownMenu = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath
                ("//a[@href='/user/admin']/button")));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", dropDownMenu);
        return this;
    }

    public WebElement openBuildsTabFromAdminDropdownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div[@id='breadcrumb-menu']//span[.='Builds']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//h1[.='Builds for admin']")));
    }

    public WebElement openConfigureTabFromAdminDropdownMenu () {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//span[. ='Configure']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//li[@class='jenkins-breadcrumbs__list-item'][3]")));
    }

    public WebElement openMyViewsTabFromAdminDropdownMenu () {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div[@class='bd']//span[.='My Views']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//a[@href='/user/admin/my-views/']")));
    }

    public WebElement openCredentialsTabFromAdminDropdownMenu () {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//span[.='Credentials']"))).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//h1[.='Credentials']")));
    }
}
