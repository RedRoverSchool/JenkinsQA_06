package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ManageJenkinsTest extends BaseTest {
    final String NAME_NEW_NODE = "testNameNewNode";

    private final By Manage_Jenkins = By.xpath("//a[@href='/manage']");

    public boolean isTitleAppeared(List<WebElement> titleTexts, String title) {
        for (WebElement element : titleTexts) {
            if (element.getText().equals(title)) {
                return true;
            }
        }
        return false;
    }

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
        Assert.assertEquals(oldData.getCssValue("font-size"), "25.6px");
        Assert.assertEquals(oldData.getCssValue("font-weight"), "700");

        List<WebElement> listSortTable = getDriver().findElements(By.xpath("//thead //a"));
        Assert.assertEquals(listSortTable.size(), 4);

        Assert.assertTrue(getDriver().findElement(By.id("main-panel")).getText().contains("No old data was found."));
    }

    @Ignore
    @Test
    public void testSearchNumericSymbol() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("settings-search-bar"))).click();
        getDriver().findElement(By.id("settings-search-bar")).sendKeys("1");
        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".jenkins-search__results-container--visible")));
        WebElement noResults = getDriver().findElement(By.cssSelector(".jenkins-search__results__no-results-label"));

        Assert.assertEquals(noResults.getText(), "No results");
    }

    @Test
    public void testSearchConfigureSystemByC() {
        String oldUrl = getDriver().getCurrentUrl();

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.id("settings-search-bar")).sendKeys("c");

        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'results-container')]")));

        List<WebElement> titleTexts = getDriver()
                .findElements(By.xpath("//div/a[contains(@href, 'manage')]"));

        Assert.assertTrue(isTitleAppeared(titleTexts, "Configure System"));

        getWait2()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='jenkins-search__results-item--selected']")))
                .click();
        getWait10().until(t -> !Objects.equals(getDriver().getCurrentUrl(), oldUrl));

        Assert.assertEquals(getDriver().getTitle(), "Configure System [Jenkins]");
    }

    @DataProvider(name = "keywords")
    public Object[][] searchSettingsItem() {
        return new Object[][]{{"manage"}, {"tool"}, {"sys"}, {"sec"}, {"cred"}, {"dow"}, {"script"}, {"jenkins"}, {"stat"}};
    }

    @Test(dataProvider = "keywords")
    public void testSearchSettingsItemsByKeyword(String keyword) {
        List<WebElement> actualResult, expectedResult, listSettingsItems;

        getDriver().findElement(By.xpath("//div[@id='tasks']//div//a[@href='/manage']")).click();

        listSettingsItems = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']//dt"));

        WebElement searchSettingsField = getDriver().findElement(By.xpath("//input[@id='settings-search-bar']"));
        searchSettingsField.click();
        searchSettingsField.sendKeys(keyword);
        getWait10().until(ExpectedConditions.textToBePresentInElementValue(searchSettingsField, keyword));

        actualResult = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='jenkins-search__results']//a")));
        expectedResult = listSettingsItems.stream().filter(item -> item.getText().toLowerCase().contains(keyword)).toList();

        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(actualResult.get(i).getText(), expectedResult.get(i).getText());
        }
    }
}
