package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class FreestyleProjectConfigPage extends BaseMainHeaderPage {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectPage clickSave() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return new FreestyleProjectPage(getDriver());
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

    public FreestyleProjectConfigPage clickEditDescription() {
        getDriver().findElement(By.xpath("//*[@href = 'editDescription']")).click();
        return this;
    }

    public FreestyleProjectConfigPage removeOldDescriptionAndAddNew (String description) {
        WebElement oldDescription = getDriver().findElement(By.xpath("//*[@id='description']/form/div[1]/div[1]/textarea"));
        oldDescription.clear();
        oldDescription.sendKeys(description);
        return this;
    }

    public FreestyleProjectConfigPage clickSaveDescription() {
        getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button")).click();
        return new FreestyleProjectConfigPage(getDriver());
    }

    public String getDescription() {
        return getDriver().findElement(By.xpath("//*[@id='description']/div")).getText();
    }

    public FreestyleProjectConfigPage clickPreviewSeeOrHide(Boolean seeAndHidePreview) {
        if (seeAndHidePreview) {
            getDriver().findElement(By.xpath("//a[contains(@previewendpoint, 'previewDescription')]")).click();
        } else {
            getDriver().findElement(By.xpath("//a[normalize-space(text())='Hide preview']")).click();
        }
        return this;
    }

    public FreestyleProjectConfigPage clickPreviewButton () {
        getDriver().findElement(By.xpath("//a[@class = 'textarea-show-preview']")).click();
        return this;
    }

    public String getPreviewDescription () {
        return getDriver().findElement(By.xpath("//*[@class = 'textarea-preview']")).getText();
    }

    public MainPage clickJenkinsLogo() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link"))).click();
        return new MainPage(getDriver());
    }
}
