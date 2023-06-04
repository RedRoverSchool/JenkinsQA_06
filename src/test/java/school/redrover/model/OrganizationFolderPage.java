package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BaseModel;

public class OrganizationFolderPage extends BaseMainHeaderPage<OrganizationFolderPage> {
    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashboard() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dashboard']"))).click();
        return new MainPage(getDriver());
    }

    public MovePage<OrganizationFolderPage> clickMoveOnLeftMenu() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[normalize-space(.)='Move']/a"))).click();
        return new MovePage<>(this);
    }

    public String getTextFromDisableMessage(){

        return getDriver().findElement(By.xpath("//form[@method='post']")).getText();
    }

    public String getTextFromDescription(){

        return getDriver().findElement(By.xpath("//div[@id='view-message']")).getText();
    }
}
