package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class RenameFolderPage extends BasePage {

    public RenameFolderPage(WebDriver driver) {
        super(driver);
    }

    public RenameFolderPage setNewName (String newName) {
        WebElement newNameField = getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.name("newName"))));
        newNameField.click();
        newNameField.clear();
        newNameField.sendKeys(newName);

        return this;
    }

    public FolderPage clickRenameButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name=Submit]"))).click();

        return new FolderPage(getDriver());
    }
}
