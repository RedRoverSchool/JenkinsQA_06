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
    private static final By ADMIN_DROPDOWN_CHEVRON = By.xpath("//a[@href='/user/admin']/button[@class='jenkins-menu-dropdown-chevron']");
    private static final By LOGOUT_ICON = By.xpath("//a[@href='/logout']");

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

    public MainHeaderComponent<Page> clickAdminDropDownChevron() {
        hoverOver(ADMIN_DROPDOWN_CHEVRON);
        getDriver().findElement(ADMIN_DROPDOWN_CHEVRON).click();
        return this;
    }

    public boolean isPopUpNotificationScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("visible-am-list"))).isDisplayed();
    }

    public boolean isDropDownAdminScreenDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("breadcrumb-menu"))).isDisplayed();
    }

    public MainHeaderComponent<Page> hoverOverNotificationIcon() {
        hoverOver(NOTIFICATION_ICON);
        return this;
    }

    public MainHeaderComponent<Page> hoverOverAdminIcon() {
        hoverOver(By.xpath("//a[@href='/user/admin']"));
        return this;
    }

    public MainHeaderComponent<Page> hoverOverLogOutIcon() {
        hoverOver(LOGOUT_ICON);
        return this;
    }

    public String getNotificationIconColor() {
        return getIconBackgroundColor(NOTIFICATION_ICON);
    }

    public String getAdminIconColor() {
        return getIconBackgroundColor(By.xpath("//a[@href='/user/admin']"));
    }

    public String getLogOutIconColor() {
        return getIconBackgroundColor(LOGOUT_ICON);
    }
}
