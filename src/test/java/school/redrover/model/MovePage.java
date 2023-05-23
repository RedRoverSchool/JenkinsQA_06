package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class MovePage extends BuildPage{

    public MovePage(WebDriver driver) {
        super(driver);
    }

    public MovePage selectDestinationFolder() {
        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(By.name("destination")))).selectByIndex(1);
        return this;
    }

    public FolderPage clickMoveButton() {
        getDriver().findElement(By.name("Submit")).click();
        return new FolderPage(getDriver());
    }
}
