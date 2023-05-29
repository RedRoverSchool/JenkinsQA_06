package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BasePage;

public class MainHeaderComponent<Page extends BasePage<?>> extends BaseComponent<Page> {

    public MainHeaderComponent(Page page) {
        super(page);
    }

    public MainPage clickLogo() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-head-icon"))).click();
        return new MainPage(getDriver());
    }

    public Page clickNotificationIcon() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("visible-am-button"))).click();
        return getPage();
    }

    public Page clickAdminIcon() {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();
        return getPage();
    }
}
