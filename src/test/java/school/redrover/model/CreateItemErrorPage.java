package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.component.MainHeaderComponent;

public class CreateItemErrorPage extends BaseMainHeaderPage<CreateItemErrorPage> {

    public CreateItemErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getDriver().findElement(By.xpath("//div//p")).getText();
    }

    public String getError() {
        return getDriver().findElement(By.xpath("//h1")).getText();
    }

    public String getHeaderText() {

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//h1"))).getText();
    }


}
