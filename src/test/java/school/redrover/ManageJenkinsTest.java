package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import java.util.List;
import java.util.Objects;

public class ManageJenkinsTest extends BaseTest {
    final String NAME_NEW_NODE = "testNameNewNode";

    public boolean isTitleAppeared(List<WebElement> titleTexts, String title) {
        for (WebElement element : titleTexts) {
            if (element.getText().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testSearchWithLetterConfigureSystem() {
        String configurePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .enterSearchQuery("m")
                .clickSearchButton()
                .selectOnTheFirstLineInDropdown()
                .getConfigureSystemPage();

        Assert.assertEquals(configurePage,"Configure System");
    }

    @Test
    public void testCreateNewUser() {
        boolean newUser = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickCreateUser()
                .fillUserDetails()
                .submit()
                .findUserCreated();

        Assert.assertTrue(newUser);
    }

    @Test
    public void testCreateNewUserWithInvalidEmail() {
        String errorEmail = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickCreateUser()
                .fillUserDetailsWithInvalidEmail()
                .submit()
                .assertInvalidEmailError();

        Assert.assertEquals(errorEmail, "Invalid e-mail address");
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testDeleteUser() {
        boolean userNotFound = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickDeleteUser()
                .submit()
                .getUserDeleted();

        Assert.assertFalse(userNotFound);
    }

    @Test
    public void testAddDescriptionToUserOnTheUserProfilePage() {
        String descriptionText = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickUserEditButton()
                .enterDescriptionText()
                .submit()
                .getDescriptionText();

        Assert.assertEquals("Description text",descriptionText);
    }

    @Test
    public void testManageConfigureNumberOfExecutorsInMasterNode() {
        String number = "3";

        String numberInLine = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateManageNodesAndClouds()
                .clickConfigureMasterNode()
                .changeNumberOfExecutorsAndSave(number)
                .navigateToMasterNodeConfiguration()
                .numberOfExecutors();

        Assert.assertEquals(number, numberInLine);
    }

    @Test
    public void testBreadcrumbNavigateManageJenkins() {

        String page = new ManagePage(getDriver())
                .navigateToDashboardIcon()
                .dropdownBreadcrumps()
                .navigateToManageJenkinsAndClick()
                .verifyManageJenkinsPage();

        Assert.assertEquals(page,"Manage Jenkins" );
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
    public void testTextErrorWhenCreateNewNodeWithEmptyName() {

        String textError = new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageNodes()
                .clickNewNodeButton()
                .inputNodeNameField(NAME_NEW_NODE)
                .clickPermanentAgentRadioButton()
                .clickCreateButton()
                .clearNameField()
                .clickSaveButtonWhenNameFieldEmpty()
                .getTextError();

        Assert.assertEquals(textError, "Query parameter 'name' is required");
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
        String searchResult = new MainPage(getDriver())
            .navigateToManageJenkinsPage()
            .inputToSearchField(inputText)
            .getDropdownResultsInSearchField();
        Assert.assertEquals(searchResult, inputText);
    }
}
