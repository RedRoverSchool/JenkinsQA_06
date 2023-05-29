package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseModel;

public class RenameMultibranchPipelinePage extends BaseModel {
    public RenameMultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    public RenameMultibranchPipelinePage enterNewName (String name) {
        WebElement inputTextbox = getDriver().findElement(By.name("newName"));
        inputTextbox.clear();
        inputTextbox.sendKeys(name);
        return this;
    }
    public RenameMultibranchPipelinePage renameButton () {
        getDriver().findElement(By.name("Submit")).click();
        return new RenameMultibranchPipelinePage(getDriver());
    }

}