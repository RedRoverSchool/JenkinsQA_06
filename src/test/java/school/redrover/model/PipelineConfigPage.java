package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class PipelineConfigPage extends BasePage {

    public PipelineConfigPage(WebDriver driver){
        super(driver);
    }

    public PipelinePage clickSaveButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new PipelinePage(getDriver());
    }

    public PipelineConfigPage scrollAndClickAdvancedButton() {
        WebElement pipelineSection = getDriver().findElement(By.xpath("//div[@id='pipeline']"));
        WebElement advancedButton = getDriver().findElement(By.xpath("//div[@class='jenkins-section']//button[@type='button'][normalize-space()='Advanced']"));

        new Actions(getDriver()).scrollToElement(pipelineSection).moveToElement(advancedButton).click().perform();
        return this;
    }

    public PipelineConfigPage setDisplayName(String displayName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(displayName);
        return this;
    }
}
