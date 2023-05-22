package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreeStyleProject13Test extends BaseTest {
    private String name = "FreestyleProject1";

    @Test
    public void testCreateFreestyleProjectNewItem() {
        TestUtils.createFreestyleProject(this, name, false);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//div[@id ='main-panel']//h1[text()='Project " + name + "']"))
                .getText(), "Project " + name);
    }

    @Test
    public void testFindFreestyleProjectOnDashboard() {
        TestUtils.createFreestyleProject(this, name, true);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//tr[@id='job_" + name + "']//a//span['" + name + "']"))
                .getText(), name);
    }
    @Test
    public void testCreateProjectWithDescription(){
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name"))).sendKeys(name);

        getDriver().findElement(By.xpath("//span[text() = 'Freestyle project']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(description)).click();
        final String name1 = "DescriptionOfProjectFreestyle1";
        description.sendKeys(name1);

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//div[@id='description']/div[text()='" + name1 + "']")).getText(), name1);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//div[@id ='main-panel']//h1[text()='Project " + name + "']"))
                .getText(), "Project " + name);
    }
    @Test
    public void testDisableFreestyleProject(){
         TestUtils.createFreestyleProject(this, name, false);

        getDriver().findElement(By.name("Submit")).click();

        WebElement enableButton = getDriver().findElement(By.name("Submit"));
        getWait2().until(ExpectedConditions.visibilityOf(enableButton));

        Assert.assertEquals(enableButton.getText(), "Enable");
    }
}