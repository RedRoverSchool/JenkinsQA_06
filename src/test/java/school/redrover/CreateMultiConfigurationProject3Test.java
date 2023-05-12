package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigurationProject3Test extends BaseTest {

    @Test
    public void CreateMultiConfigurationProjectTest() {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement enterMultiConfigurationProjectName = getDriver().findElement(By.xpath("//input[@id='name']"));

        enterMultiConfigurationProjectName.sendKeys("MultiConfigurationProject");
        enterMultiConfigurationProjectName.sendKeys(Keys.RETURN);

        WebElement createMultiConfigurationProject = getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']"));
        createMultiConfigurationProject.click();

        WebElement clickOkButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        clickOkButton.click();

        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();


        WebElement nameProject = getDriver().findElement(By.linkText("MultiConfigurationProject"));

        Assert.assertEquals(nameProject.getText(), "MultiConfigurationProject");

}

}
