package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public abstract class BaseFoldersConfigPage<Self extends BaseConfigPage<?, ?>, FolderPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, FolderPage>{


    public BaseFoldersConfigPage(FolderPage foldersPage) {
        super(foldersPage);
    }

    public Self enterDisplayName(String displayName) {
        WebElement inputDisplayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return (Self)this;
    }

    public Self clickHealthMetrics(){
        getDriver().findElement(By.xpath("//button [@class='jenkins-button advanced-button advancedButton']")).click();
        return (Self)this;
    }

    public Self clickAddMetric(){
        getDriver().findElement(By.xpath("//button [@id='yui-gen1-button']")).click();
        return (Self)this;
    }
    public Self clickChildWithWorstHealth(){
        getDriver().findElement(By.xpath("//a[@class='yuimenuitemlabel']")).click();
        return (Self)this;
    }

    public Boolean healthMetricIsVisible(){
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@name='healthMetrics']"))).isDisplayed();
    }

    public Self clickOnHealthMetricsType(){
        getDriver().findElement(By.xpath("//*[@class='jenkins-button advanced-button advancedButton']")).click();
        return (Self)this;
    }

    public Self setHealthMetricsType(){
        getDriver().findElement(By.xpath("//*[@class='jenkins-button advanced-button advancedButton']")).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='yui-gen1-button']"))).click();
        getDriver().findElement(By.xpath("//a[@class='yuimenuitemlabel']")).click();
        return (Self)this;
    }
    public boolean isRecursive(){
        return getWait10()
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//input[@name='_.recursive']"))).isDisplayed();
    }
}
