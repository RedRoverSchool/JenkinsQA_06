package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseProjectsConfigPage;

public class FreestyleProjectConfigPage extends BaseProjectsConfigPage<FreestyleProjectConfigPage, FreestyleProjectPage> {


    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
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
