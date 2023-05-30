package school.redrover.model.component;

import org.openqa.selenium.By;
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

    public MainHeaderComponent hoverOverAdminIcon() {
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
}
