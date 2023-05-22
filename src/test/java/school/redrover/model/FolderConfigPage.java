package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class FolderConfigPage extends BasePage {

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage interDisplayName(String displayName) {
        WebElement inputDisplayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return this;
    }

    public FolderConfigPage interDescription(String description) {
        WebElement inputDescription = getDriver().findElement(By.xpath("//input[@name='_.description']"));
        inputDescription.click();
        inputDescription.sendKeys(description);
        return this;
    }

    public FolderPage clickSaveButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='_.description']"))).click();
        return new FolderPage(getDriver());
    }

    public MainPage clickDashboard() {
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        return new MainPage(getDriver());
    }
    public FolderPage saveProjectAndGoToFolderPage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new FolderPage(getDriver());
    }
}
