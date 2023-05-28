package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.base.BaseHeaderComponent;

public class MainHeaderComponent extends BaseHeaderComponent {

    public MainHeaderComponent(WebDriver driver) {
        super(driver);
    }

    public MainPage clickLogo() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-head-icon"))).click();

        return new MainPage(getDriver());
    }

    public MainPage clickNotificationIcon() {
        getDriver().findElement(By.id("visible-am-button")).click();
        return new MainPage(getDriver());
    }

    public MainPage clickAdminIcon() {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();
        return new MainPage(getDriver());
    }

}
