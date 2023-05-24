package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.runner.BaseTest;
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
    public void testSearchNumericSymbol() {

        String searchText = new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .inputToSearchField("1")
                .getNoResultTextInSearchField();

        Assert.assertEquals(searchText, "No results");
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

    @DataProvider(name = "ToolsAndActions")
    public Object [][] searchToolsAndActions() {
        return new Object [][] {{"Script Console"}, {"Jenkins CLI"}, {"Prepare for Shutdown"}};
    }


    @Test(dataProvider = "ToolsAndActions")
    public void testSearchToolsAndActions(String inputText)  {

        MainPage mainPage = new MainPage(getDriver())
            .navigateToManageJenkinsPage();
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(getDriver())
                .inputToSearchField(inputText);
        Assert.assertEquals(manageJenkinsPage.getDropdownResultsInSearchField(), inputText);

    }
}
