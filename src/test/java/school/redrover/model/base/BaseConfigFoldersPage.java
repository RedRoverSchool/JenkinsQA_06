package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobsconfig.MultibranchPipelineConfigPage;
import school.redrover.model.jobs.MultibranchPipelinePage;
import school.redrover.runner.TestUtils;

public abstract class BaseConfigFoldersPage<Self extends BaseConfigPage<?, ?>, FolderPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, FolderPage>{


    public BaseConfigFoldersPage(FolderPage foldersPage) {
        super(foldersPage);
    }

    public Self enterDisplayName(String displayName) {
        WebElement inputDisplayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return (Self)this;
    }

    public Self clickHealthMetrics(){
        WebElement healthMetric = getDriver().findElement(By.xpath("//button[contains(text(), 'Health metrics')]"));
        TestUtils.scrollToElementByJavaScript(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())), healthMetric);
        healthMetric.click();

        return (Self)this;
    }

    public Self addHealthMetrics(){
       clickHealthMetrics();
        WebElement addMetric = getDriver().findElement(By.xpath("//button [text()='Add metric']"));
        TestUtils.scrollToElementByJavaScript(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())), addMetric);
        addMetric.click();
        getDriver().findElement(By.xpath("//a[text()='Child item with worst health']")).click();

        return (Self)this;
    }

    public Boolean healthMetricIsVisible(){
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@name='healthMetrics']"))).isDisplayed();
    }

    public Self clickOnHealthMetricsType(){
        getDriver().findElement(By.xpath("//button[contains(text(), 'Health metrics')]")).click();
        return (Self)this;
    }

    public Self setHealthMetricsType(){
        getDriver().findElement(By.xpath("//button[contains(text(), 'Health metrics')]")).click();
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
