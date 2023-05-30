package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;


public class FolderConfigPage extends BaseConfigPage<FolderConfigPage, FolderPage> {

    public FolderConfigPage(FolderPage folderPage) {
        super(folderPage);
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

    public FolderConfigPage clickOnHealthMetricsType(){
        getDriver().findElement(By.xpath("//*[@class='jenkins-button advanced-button advancedButton']")).click();
        return this;
    }

    public FolderConfigPage setHealthMetricsType(){
        getDriver().findElement(By.xpath("//*[@class='jenkins-button advanced-button advancedButton']")).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='yui-gen1-button']"))).click();
        getDriver().findElement(By.xpath("//a[@class='yuimenuitemlabel']")).click();
        return this;
    }

    public boolean isRecursive(){
        return getWait10()
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//input[@name='_.recursive']"))).isDisplayed();
    }
}