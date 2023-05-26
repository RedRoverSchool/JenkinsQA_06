package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class OrganizationFolderPage extends BasePage {
    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashboard() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dashboard']"))).click();
        return new MainPage(getDriver());
    }

    public MovePage clickMoveOnLeftMenu() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[normalize-space(.)='Move']/a"))).click();
        return new MovePage(getDriver());
    }
}
