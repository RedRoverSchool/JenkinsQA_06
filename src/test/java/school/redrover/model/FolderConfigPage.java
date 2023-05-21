package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class FolderConfigPage extends BasePage {
    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage saveProjectAndGoToFolderPage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new FolderPage(getDriver());
    }
}
