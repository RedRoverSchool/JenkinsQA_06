package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class ManageJenkinsTest extends BaseTest {
    final String NAME_NEW_NODE = "testNameNewNode";

    private final By Manage_Jenkins = By.xpath("//a[@href='/manage']");

    @Test
    public void testNameNewNodeOnCreatePage() {

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

    @Test
    public void testErrorWhenCreateNewNodeWithEmptyName() {

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
        nameField.clear();
        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();
        WebElement H1Text = getDriver().findElement(By.xpath("//h1"));
        WebElement textError = getDriver().findElement(By.xpath("//p"));

        Assert.assertEquals(H1Text.getText(), "Error");
        Assert.assertEquals(textError.getText(), "Query parameter 'name' is required");
    }

    @Test
    public void testVerifySystemConfiguration() {
        List<String> listSystemConfigurationExpected = Arrays.asList
                ("System Configuration", "Security", "Status Information", "Troubleshooting", "Tools and Actions");

        getDriver().findElement(Manage_Jenkins).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Manage')]")));
        List<WebElement> listSystemConfiguration = getDriver().findElements(By.cssSelector(".jenkins-section__title"));
        for (int i = 0; i < listSystemConfiguration.size(); i++) {

            Assert.assertEquals(listSystemConfiguration.get(i).getText(), listSystemConfigurationExpected.get(i));
        }
    }

    @Test
    public void testManageOldData() {
        getDriver().findElement(Manage_Jenkins).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//dt[contains(text(),'Manage Old Data')]"))).click();

        WebElement oldData = getWait5().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#main-panel > h1")));
        Assert.assertEquals(oldData.getText(), "Manage Old Data");
        Assert.assertEquals(oldData.getLocation().toString(), "(372, 133)");
        Assert.assertEquals(oldData.getCssValue("font-size").toString(), "25.6px");
        Assert.assertEquals(oldData.getCssValue("font-weight").toString(), "700");

        List<WebElement> listSortTable = getDriver().findElements(By.xpath("//thead //a"));
        Assert.assertEquals(listSortTable.size(), 4);

        Assert.assertTrue(getDriver().findElement(By.id("main-panel")).getText().contains("No old data was found."));
    }
    @Test
    public void testSearchNumericSimbol(){
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("settings-search-bar")));
        getDriver().findElement(By.id("settings-search-bar")).sendKeys("1");
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-search__results']")));
        String noResults = getDriver().findElement(
                By.xpath("//p[@class='jenkins-search__results__no-results-label']")).getText();

        Assert.assertEquals(noResults, "No results");
    }
}
