package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ManageJenkinsTest extends BaseTest {

    @Test
    public void testAddNewNode() {
        final String NAME_NEW_NODE = "testNameNewNode";

        WebElement buildExecutorStatus = getDriver().findElement(By.xpath("//a[@href='/computer/']"));
        buildExecutorStatus.click();
        WebElement newNodeButton = getDriver().findElement(By.xpath("//div[@id='main-panel']//a[@href='new']"));
        newNodeButton.click();
        WebElement inputNodeName = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputNodeName.sendKeys(NAME_NEW_NODE);
        WebElement permanentAgentRadioButton = getDriver().findElement(By.xpath("//label"));
        permanentAgentRadioButton.click();
        WebElement createButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        createButton.click();
        WebElement nameField = getDriver().findElement(By.xpath("//input[@name='name']"));
        String actualValueName = nameField.getAttribute("value");

        Assert.assertEquals(actualValueName, NAME_NEW_NODE);
    }
}
