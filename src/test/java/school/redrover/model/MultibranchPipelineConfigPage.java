package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class MultibranchPipelineConfigPage extends BasePage {
    public MultibranchPipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelinePage saveButton() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return new MultibranchPipelinePage(getDriver());
    }
    public MultibranchPipelineConfigPage displayName (String name) {
        getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='main-panel']/form[1]/div[1]/div[2]/div[1]/div[2]/input[1]"))
                .sendKeys("Random Name");
        return this;
    }
    public MultibranchPipelineConfigPage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='_.description']"))
                .sendKeys("Random Description");
        return this;
    }
    public WebElement titleMultibranchPipeline() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//body/div[@id='page-body']/div[@id='main-panel']/h1[1]"))));

    }
    public  WebElement viewDescription(){
        return getDriver().findElement(By.xpath("//*[@id=\"description\"]/div[1]"));
    }
}
