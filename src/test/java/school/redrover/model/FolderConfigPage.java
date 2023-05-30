package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseModel;
import school.redrover.model.base.BaseMainConfigPage;
import school.redrover.model.component.MainConfigComponent;


public class FolderConfigPage  extends BaseMainConfigPage<FolderConfigPage> {

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }


    public FolderConfigPage enterDisplayName(String displayName) {
        WebElement inputDisplayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return this;
    }

    public MainPage clickDashboard() {
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        return new MainPage(getDriver());
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

    public FolderConfigPage addDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[contains(@name, 'description')]")).sendKeys(description);
        return this;
    }
}