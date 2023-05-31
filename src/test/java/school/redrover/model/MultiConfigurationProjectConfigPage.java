package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectConfigPage extends BaseConfigPage<MultiConfigurationProjectConfigPage, MultiConfigurationProjectPage> {


    public MultiConfigurationProjectConfigPage(MultiConfigurationProjectPage multiConfigurationProjectPage) {
        super(multiConfigurationProjectPage);
    }

    public MultiConfigurationProjectConfigPage toggleDisable(){
        getDriver().findElement(By.cssSelector("label.jenkins-toggle-switch__label ")).click();
        return this;
    }

    public WebElement getCheckboxById(int id){
        return getDriver().findElement(By.id("cb" + id));
    }

    public ProjectPage saveConfigurePageAndGoToProjectPage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new ProjectPage(getDriver());
    }

    public MultiConfigurationProjectPage saveConfigurePageAndGoToConfigPage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new MultiConfigurationProjectPage(getDriver());
    }

    public MultiConfigurationProjectConfigPage clickOldBuildCheckBox(){
        TestUtils.clickByJavaScript(this, getDriver()
                .findElement(By.xpath("//span[@class='jenkins-checkbox']//input[@id='cb4']")));

        return this;
    }

    public MultiConfigurationProjectConfigPage enterDaysToKeepBuilds(int number){
        WebElement daysToKeepBuilds = getDriver()
                .findElement(By.xpath("//input[@name='_.daysToKeepStr']"));
        TestUtils.sendTextToInput(this, daysToKeepBuilds, String.valueOf(number));

        return this;
    }

    public MultiConfigurationProjectConfigPage enterMaxNumOfBuildsToKeep(int number){
        WebElement maxNumOfBuildsToKeepNumber = getDriver()
                .findElement(By.xpath("//input[@name='_.numToKeepStr']"));
        TestUtils.sendTextToInput(this, maxNumOfBuildsToKeepNumber, String.valueOf(number));

        return this;
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
