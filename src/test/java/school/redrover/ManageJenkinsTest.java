package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.runner.BaseTest;

import java.util.List;

import static school.redrover.runner.TestUtils.getRandomStr;

public class ManageJenkinsTest extends BaseTest {

    final String NAME_NEW_NODE = "testNameNewNode";
    private static final String NODE_NAME = "NodeName";

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
        String textConfigureSystem = "Configure System";
        String configurePage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .inputToSearchField("m")
                .selectOnTheFirstLineInDropdown(textConfigureSystem)
                .getConfigureSystemPage();

        Assert.assertEquals(configurePage, textConfigureSystem);
    }

    @Test
    public void testNavigateToManageJenkinsFromMainPageUsingDashboard() {

        String actualResult = new MainPage(getDriver())
                .getBreadcrumb()
                .clickManageJenkinsOnDropDownMenu()
                .getActualHeader();

        Assert.assertEquals(actualResult, "Manage Jenkins");
    }

    @Test
    public void testNameNewNodeOnCreatePage() {
        final String nodeName = "NodeTest";

        String actualNodeName = new MainPage(getDriver())
                .clickBuildExecutorStatus()
                .clickNewNodeButton()
                .inputNodeNameField(nodeName)
                .clickPermanentAgentRadioButton()
                .clickCreateButton()
                .clickSaveButton()
                .getNodeName(nodeName);

        Assert.assertEquals(actualNodeName, nodeName);
    }

    @Test
    public void testTextErrorWhenCreateNewNodeWithEmptyName() {

        String textError = new MainPage(getDriver())
                .clickManageJenkinsPage()
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
                .clickManageJenkinsPage()
                .inputToSearchField("1")
                .getNoResultTextInSearchField();

        Assert.assertEquals(searchText, "No results");
    }

    @Test
    public void testNavigateToConfigureSystemPageBySearchField() {

        String configureSystemPageTitle = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .inputToSearchField("c")
                .clickConfigureSystemFromSearchDropdown()
                .getTitle();

        Assert.assertEquals(getDriver().getTitle(), "Configure System [Jenkins]");
        Assert.assertEquals(configureSystemPageTitle, "Configure System");
    }

    @DataProvider(name = "keywords")
    public Object[][] searchSettingsItem() {
        return new Object[][]{{"manage"}, {"tool"}, {"sys"}, {"sec"}, {"cred"}, {"dow"}, {"script"}, {"jenkins"}, {"stat"}};
    }

    @Test(dataProvider = "keywords")
    public void testSearchSettingsItemsByKeyword(String keyword) {

        boolean manageJenkinsPage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .inputToSearchField(keyword)
                .selectAllDropdownResultsFromSearchField()
                .isDropdownResultsFromSearchFieldContainsTextToSearch(keyword);

        Assert.assertTrue(manageJenkinsPage);
    }

    @DataProvider(name = "ToolsAndActions")
    public Object[][] searchToolsAndActions() {
        return new Object[][]{{"Script Console"}, {"Jenkins CLI"}, {"Prepare for Shutdown"}};
    }

    @Test(dataProvider = "ToolsAndActions")
    public void testSearchToolsAndActions(String inputText) {
        String searchResult = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .inputToSearchField(inputText)
                .getDropdownResultsInSearchField();
        Assert.assertEquals(searchResult, inputText);
    }

    @Test
    public void testAccessSearchSettingsFieldUsingShortcutKey() {
        final String partOfSettingsName = "manage";

        ManageJenkinsPage manageJenkinsPage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .inputToSearchFieldUsingKeyboardShortcut(partOfSettingsName)
                .selectAllDropdownResultsFromSearchField();

        Assert.assertTrue(manageJenkinsPage.isDropdownResultsFromSearchFieldContainsTextToSearch(partOfSettingsName));
        Assert.assertTrue(manageJenkinsPage.isDropdownResultsFromSearchFieldLinks());
    }

    @Test
    public void testCreateNewAgentNode() {

        String manageNodesPage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManageNodes()
                .clickNewNodeButton()
                .inputNodeNameField(NODE_NAME)
                .clickPermanentAgentRadioButton()
                .clickCreateButton()
                .clickSaveButton()
                .getNodeName(NODE_NAME);

        Assert.assertEquals(manageNodesPage, NODE_NAME);
    }

    @Test
    public void testCreateNewAgentNodeWithDescription() {
        final String nodeName = getRandomStr(10);
        final String description = getRandomStr(50);

        String nodeDescription = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManageNodes()
                .clickNewNodeButton()
                .inputNodeNameField(nodeName)
                .clickPermanentAgentRadioButton()
                .clickCreateButton()
                .addDescription(description)
                .clickSaveButton()
                .clickOnNode(nodeName)
                .getNodeDescription();

        Assert.assertEquals(nodeDescription, description);

    }

    @Test
    public void testCreateNewAgentNodeByCopyingExistingNode() {
        final String nodeName = getRandomStr(10);
        final String newNodeName = getRandomStr(10);
        final String description = getRandomStr(50);

        String newNodeDescription = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManageNodes()
                .clickNewNodeButton()
                .inputNodeNameField(nodeName)
                .clickPermanentAgentRadioButton()
                .clickCreateButton()
                .addDescription(description)
                .clickSaveButton()
                .clickNewNodeButton()
                .inputNodeNameField(newNodeName)
                .clickCopyExistingNode()
                .inputExistingNode(nodeName)
                .clickCreateButton()
                .clickSaveButton()
                .clickOnNode(newNodeName)
                .getNodeDescription();

        Assert.assertEquals(newNodeDescription, description);
    }

    @Test
    public void testCreateNewAgentNodeByCopyingNonExistingNode() {
        final String nonExistingNodeName = ".0";

        String errorMessage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManageNodes()
                .clickNewNodeButton()
                .inputNodeNameField("NewNode")
                .clickCopyExistingNode()
                .inputExistingNode(nonExistingNodeName)
                .clickCreateButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "No such agent: " + nonExistingNodeName);
    }

    @Test
    public void testFourTasksOnLeftsidePanel() {
        final List<String> expectedListOfTasks = List.of(new String[]{"Updates", "Available plugins", "Installed plugins", "Advanced settings"});
        List<String> actualListOfTasks = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManagePlugins()
                .checkFourTasksOnTheLeftsidePanel();

        Assert.assertEquals(actualListOfTasks, expectedListOfTasks);
    }

    @Test
    public void testServerHelpInfo(){
        final String expectedServerHelpInfo = """
                If your Jenkins server sits behind a firewall and does not have the direct access to the internet, and if your server JVM is not configured appropriately ( See JDK networking properties for more details ) to enable internet connection, you can specify the HTTP proxy server name in this field to allow Jenkins to install plugins on behalf of you. Note that Jenkins uses HTTPS to communicate with the update center to download plugins.
                Leaving this field empty means Jenkins will try to connect to the internet directly.
                If you are unsure about the value, check the browser proxy configuration.""";
        String ServerHelpInfo = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManagePlugins()
                .clickAdvancedSettings()
                .clickExtraInfoServerIcon()
                .getExtraInfoServerTextBox();

        Assert.assertEquals(ServerHelpInfo, expectedServerHelpInfo);
    }

    @Test(dependsOnMethods = "testCreateNewAgentNode")
    public void testDeleteNodeBySideMenuOnNodePage() {
        List<String> nodeNameList = new MainPage(getDriver())
                .clickOnNodeName(NODE_NAME)
                .clickOnDeleteAgent()
                .clickYesButton()
                .getNodesList();

        Assert.assertFalse(nodeNameList.contains(NODE_NAME));
    }
}