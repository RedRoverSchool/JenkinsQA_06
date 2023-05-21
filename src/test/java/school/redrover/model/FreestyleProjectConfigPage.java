package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class FreestyleProjectConfigPage extends BasePage {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectConfigPage projectSave() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return this;
    }

    public FreestyleProjectConfigPage switchEnableAndDisable() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@for='enable-disable-project']"))).click();
        return this;
    }

    public FreestyleProjectConfigPage addDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        return this;
    }

    public FreestyleProjectConfigPage clickPreviewSeeOrHide(Boolean seeAndHidePreview) {
        if(seeAndHidePreview) {
            getDriver().findElement(By.xpath("//a[contains(@previewendpoint, 'previewDescription')]")).click();
        } else {
            getDriver().findElement(By.xpath("//a[normalize-space(text())='Hide preview']")).click();
        }
        return this;
    }
}
