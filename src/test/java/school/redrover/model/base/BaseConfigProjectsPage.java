package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.runner.TestUtils;

public abstract class BaseConfigProjectsPage<Self extends BaseConfigPage<?, ?>,ProjectPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, ProjectPage> {

    public BaseConfigProjectsPage(ProjectPage projectPage) {
        super(projectPage);
    }


    public Self clickPreviewSeeOrHide(Boolean seeAndHidePreview) {
        if (seeAndHidePreview) {
            getDriver().findElement(By.xpath("//a[contains(@previewendpoint, 'previewDescription')]")).click();
        } else {
            getDriver().findElement(By.xpath("//a[normalize-space(text())='Hide preview']")).click();
        }
        return (Self)this;
    }

    public Self addExecuteShellBuildStep(String command) {
        WebElement buildStep = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add build step')]")));

        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(By.xpath("//button[contains(text(), 'Add post-build action')]"))).click().perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(buildStep)).click();

        WebElement executeShell = getDriver().findElement(By.xpath("//a[contains(text(), 'Execute shell')]"));
        executeShell.click();

        WebElement codeMirror = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("CodeMirror")));
        actions.scrollToElement(getDriver().findElement(By.xpath("//button[contains(text(), 'Add post-build action')]"))).click().perform();
        WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-lines")).get(0);
        codeLine.click();
        WebElement commandField = codeMirror.findElement(By.cssSelector("textarea"));
        commandField.sendKeys(command);

        return (Self)this;
    }

    public Self clickOldBuildCheckBox(){
        TestUtils.clickByJavaScript(this, getDriver()
                .findElement(By.xpath("//span[@class='jenkins-checkbox']//input[@id='cb4']")));

        return (Self)this;
    }

    public Self enterDaysToKeepBuilds(int number){
        WebElement daysToKeepBuilds = getDriver()
                .findElement(By.xpath("//input[@name='_.daysToKeepStr']"));
        TestUtils.sendTextToInput(this, daysToKeepBuilds, String.valueOf(number));

        return (Self)this;
    }

    public Self enterMaxNumOfBuildsToKeep(int number){
        WebElement maxNumOfBuildsToKeepNumber = getDriver()
                .findElement(By.xpath("//input[@name='_.numToKeepStr']"));
        TestUtils.sendTextToInput(this, maxNumOfBuildsToKeepNumber, String.valueOf(number));

        return (Self)this;
    }

    public Self switchCheckboxDisable() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//span[text() = 'Enabled']")))).click();
        return (Self)this;
    }

    public Self switchCheckboxEnabled() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//label[@for='enable-disable-project']")))).click();
        return (Self)this;
    }

    public WebElement getTextDisable() {

        return getWait5().until(ExpectedConditions.elementToBeClickable
                (getDriver().findElement(By.xpath("//span[text() = 'Disabled']"))));
    }

    public WebElement getTextEnabled() {

        return getWait5().until(ExpectedConditions.elementToBeClickable
                (getDriver().findElement(By.xpath("//span[text() = 'Enabled']"))));
    }

    public String getDaysToKeepBuilds(String attribute){
        WebElement daysToKeepBuilds = getDriver()
                .findElement(By.xpath("//input[@name='_.daysToKeepStr']"));

        return daysToKeepBuilds.getAttribute(attribute);
    }

    public String getMaxNumOfBuildsToKeep(String attribute){
        WebElement maxNumOfBuildsToKeepNumber = getDriver()
                .findElement(By.xpath("//input[@name='_.numToKeepStr']"));

        return maxNumOfBuildsToKeepNumber.getAttribute(attribute);
    }
}
