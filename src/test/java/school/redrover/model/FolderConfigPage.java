package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.model.base.BaseModel;

public class FolderConfigPage extends BaseModel {

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage enterDisplayName(String displayName) {
        WebElement inputDisplayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return this;
    }

    public FolderConfigPage enterDescription(String description) {
        WebElement inputDescription = getDriver().findElement(By.xpath("//textarea[@name='_.description']"));
        inputDescription.click();
        inputDescription.sendKeys(description);
        return this;
    }

    public FolderPage clickSaveButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
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

    public FolderConfigPage clickHealthMetrics(){
        getDriver().findElement(By.xpath("//button [@class='jenkins-button advanced-button advancedButton']")).click();
        return this;
    }

    public FolderConfigPage clickAddMetric(){
        getDriver().findElement(By.xpath("//button [@id='yui-gen1-button']")).click();
        return this;
    }

    public FolderConfigPage clickChildWithWorstHealth(){
        getDriver().findElement(By.xpath("//a[@class='yuimenuitemlabel']")).click();
        return this;
    }

    public Boolean healthMetricIsVisible(){
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@name='healthMetrics']"))).isDisplayed();
    }
}