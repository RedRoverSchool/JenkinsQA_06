package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfiguration2Test extends BaseTest {
    public final String projectName = "Sample Project";
    private void createMultiConfigurationProject (){
        getDriver().findElement(By.xpath("//span[contains(text(),'New Item')]/parent::a")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(projectName);
        getDriver().findElement(By.xpath("//label/span[contains(text(), 'Multi-configuration proj')]")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='jenkins-home-link']"))).click();
    }
    @Test
    public void testAddDescriptionToMultiConfigProj (){
        final String description = "Sample Description";
        createMultiConfigurationProject();

        getDriver().findElement(By.xpath(String.format("//td/a/span[contains(text(),'%s')]",projectName))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='description-link']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")))
                .sendKeys(description);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String actualDescription = getDriver().findElement(By.xpath("//div[@id='description']/div")).getText();
        Assert.assertEquals(actualDescription, description);
    }
}

