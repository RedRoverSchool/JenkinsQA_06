package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobsconfig.MultibranchPipelineConfigPage;
import school.redrover.model.jobs.MultibranchPipelinePage;
import school.redrover.runner.TestUtils;

public abstract class BaseConfigFoldersPage<Self extends BaseConfigPage<?, ?>, FolderPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, FolderPage>{

    @FindBy(xpath = "//input[@name='_.displayNameOrNull']")
    private WebElement inputDisplayName;

    @FindBy(xpath = "//button[contains(text(), 'Health metrics')]")
    private WebElement healthMetric;

    @FindBy(xpath = "//button [text()='Add metric']")
    private WebElement addHealthMetric;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement childItemWithWorstHealth;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement addedHealthMetric;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement recursiveCheckbox;

    public BaseConfigFoldersPage(FolderPage foldersPage) {
        super(foldersPage);
    }

    public Self enterDisplayName(String displayName) {
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return (Self)this;
    }

    public Self clickHealthMetrics(){
        TestUtils.scrollToElementByJavaScript(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())), healthMetric);
        healthMetric.click();
        return (Self)this;
    }

    public Self addHealthMetrics(){
        clickHealthMetrics();

        TestUtils.scrollToElementByJavaScript(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())), addHealthMetric);
        getWait5().until(ExpectedConditions.visibilityOf(addHealthMetric)).click();
        childItemWithWorstHealth.click();

        return (Self)this;
    }

    public Boolean healthMetricIsVisible(){
        return getWait5().until(ExpectedConditions.visibilityOf(addedHealthMetric)).isDisplayed();
    }

    public boolean isRecursive(){
        return getWait10().until(ExpectedConditions.visibilityOf(recursiveCheckbox)).isDisplayed();
    }
}
