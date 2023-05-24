package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public PipelineConfigPage clickPipelineButton() {
        getDriver().findElement(By.xpath("//button[@data-section-id='pipeline']")).click();
        return this;
    }

    public String getTextFromDefinitionField() {
        return getDriver()
                .findElement(By.xpath("((//div[@class='jenkins-form-item'])[2]//select//option)[1]"))
                .getText();
    }
}
