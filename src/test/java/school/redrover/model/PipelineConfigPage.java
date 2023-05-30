package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineConfigPage extends ConfigurePage {

    public PipelineConfigPage(WebDriver driver){
        super(driver);
    }

    public PipelinePage clickSaveButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new PipelinePage(getDriver());
    }

    public PipelineConfigPage scrollAndClickAdvancedButton() {
        WebElement scriptSection = getDriver().findElement(By.xpath("//div[@class='ace_content']"));
        WebElement advancedButton = getDriver().findElement(By.xpath("//div[@class='jenkins-section']//button[@type='button'][normalize-space()='Advanced']"));

        new Actions(getDriver()).scrollToElement(scriptSection).moveToElement(advancedButton).click().perform();
        return this;
    }

    public PipelineConfigPage setDisplayName(String displayName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(displayName);
        return this;
    }

    public PipelineConfigPage enterDescription(String description) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(description);
        return this;
    }

    public PipelineConfigPage clickScriptDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text() = 'try sample Pipeline...']"))).click();
        return this;
    }

    public PipelinePage selectHelloWord() {
        getDriver().findElement(By.cssSelector("option[value='hello']")).click();
        return new PipelinePage(getDriver());
    }

    public PipelineConfigPage clickPipelineLeftMenu() {
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-section-id='pipeline']"))).click();
        return this;
    }
}
