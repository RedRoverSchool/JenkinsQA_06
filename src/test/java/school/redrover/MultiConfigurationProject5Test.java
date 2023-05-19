package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProject5Test extends BaseTest {
    private static final String PROJECT_NAME = "TestName";
    @Test
    public void testCreateMultiConfigurationProject(){

        getDriver().findElement(By.xpath("(//section[@class='empty-state-section'] )[1]//li")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.className("hudson_matrix_MatrixProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@name,'Submit')]"))).click();

        WebElement findProjectName = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"));
        Assert.assertEquals(findProjectName.getText(),"Project " + PROJECT_NAME);
    }
    @Test (dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testAddDescriptionInMultiConfigurationProject(){
        final String textDescription = "Text Description Test";

        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='description']"))).sendKeys(textDescription);
        getDriver().findElement(By.xpath("//div[@id='description']//button[@name=\"Submit\"]")).click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualDescription.getText(),textDescription);
    }
    @Test
    public void testDisableEnableProject(){
        TestUtils.createMultiConfigurationProject(this, "DisableTestName", false);
        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();
        String expectedString = getDriver().findElement(By.id("enable-project")).getText();
        Assert.assertTrue(expectedString.contains("This project is currently disabled"));

        WebElement enableButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        enableButton.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).isDisplayed());
    }

}
