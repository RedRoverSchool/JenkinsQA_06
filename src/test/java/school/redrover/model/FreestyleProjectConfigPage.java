package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;

public class FreestyleProjectConfigPage extends BaseConfigPage<FreestyleProjectConfigPage, FreestyleProjectPage> {


    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
    }

    public FreestyleProjectConfigPage switchEnableAndDisable() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@for='enable-disable-project']"))).click();
        return this;
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

    public MainPage clickJenkinsLogo() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link"))).click();
        return new MainPage(getDriver());
    }

    public FreestyleProjectConfigPage addExecuteShellBuildStep(String command) {
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

        return this;
    }

    public FreestyleProjectConfigPage addSourceCodeManagementGit(String urlGithub) {
        new Actions(getDriver())
                .scrollByAmount(0,600)
                .click(getWait2().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@for='radio-block-1']"))))
                .perform();
        new Actions(getDriver())
                .click(getDriver().findElement(
                        By.xpath("//*[@checkdependson='credentialsId']")))
                .sendKeys(urlGithub)
                .perform();
        return this;
    }

    public FreestyleProjectConfigPage addBuildStepsExecuteShell(String buildSteps) {
        new Actions(getDriver())
                .scrollByAmount(0,2000)
                .click(getWait2().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='yui-gen9-button']"))))
                .perform();

        getDriver().findElement(
                        By.xpath("//*[@id='yui-gen24']")).click();
        new Actions(getDriver())
                .click(getDriver().findElement(By.xpath("//*[@name='description']")))
                .sendKeys(buildSteps)
                .perform();
        return this;
    }
}
