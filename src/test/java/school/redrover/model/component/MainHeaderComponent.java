package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BasePage;
import school.redrover.runner.TestUtils;

import java.time.Duration;

public class MainHeaderComponent<Page extends BasePage<?>> extends BaseComponent<Page> {

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

    public String getCurrentUserName() {
        return getDriver().findElement(By.xpath("//a[@class='model-link']/span[contains(@class,'hidden-xs')]")).getAttribute("innerText");
    }

    public String getBackgroundColorNotificationIcon() {
        return getDriver().findElement(By.id("visible-am-button")).getCssValue("background-color");
    }

    public WebElement getLinkVersion() {
        return getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']"));
    }
}
