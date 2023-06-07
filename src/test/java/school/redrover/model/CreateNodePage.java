package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.List;

public class CreateNodePage extends BaseMainHeaderPage<CreateNodePage> {

    public CreateNodePage(WebDriver driver) {
        super(driver);
    }

    public CreateNodePage clearNameField() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']"))).clear();
        return this;
    }

    public ErrorNodePage clickSaveButtonWhenNameFieldEmpty() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        return new ErrorNodePage(getDriver());
    }

    public ManageNodesPage clickSaveButton() {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        return new ManageNodesPage(getDriver());
    }

}
