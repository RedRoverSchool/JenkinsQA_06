package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GroupDreamTeamTest extends BaseTest {

    @Test
    public void testWelcomeToJenkinsPresent() {
        WebElement welcome = getDriver().findElement(By.xpath("//div[@id='main-panel']//h1"));
        Assert.assertEquals(welcome.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testJenkinsMainPageLilia() {
        WebElement headerWelcome = getDriver().findElement(By.tagName("h1"));
        Assert.assertEquals(headerWelcome.getText(), "Welcome to Jenkins!");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement addDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescription.click();
        WebElement textBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='description']")));
        textBox.clear();
        textBox.sendKeys("Hello Jenkins!");
        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();
        WebElement helloJenkins = getDriver().findElement(By.xpath("//div[contains(text(),'Hello Jenkins!')]"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Assert.assertEquals(helloJenkins.getText(), "Hello Jenkins!");

        WebElement addDescription2 = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescription2.click();
        WebElement textBox2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='description']")));
        textBox2.clear();
        WebElement saveButton2 = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton2.click();
    }
@Ignore
    @Test
    public void testDashboardSidePanelItemsList() {
        List<WebElement> sidePanelItems = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));
        int itemsQuantity = sidePanelItems.size();

        Assert.assertEquals(itemsQuantity, 5);
    }
    @Ignore
    @Test
    public void testSideMenu() {
        List<String> expectedMenus = List.of("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        List<WebElement> sideMenus = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));
        List<String> menuNames = new ArrayList<>();
        for (WebElement element : sideMenus) {
            menuNames.add(element.getText());
        }

        Assert.assertEquals(menuNames, expectedMenus);
    }

    @Test
    public void testConfigureItemsMenu() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        String expectedPageHeader = "Configure";
        List<String> expectedConfigureMenuNames = List.of(
                "General",
                "Source Code Management",
                "Build Triggers",
                "Build Environment",
                "Build Steps",
                "Post-build Actions");

        WebElement createNewProject = getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a"));
        createNewProject.click();

        WebElement inputItemName = getDriver().findElement(By.id("name"));
        wait.until(ExpectedConditions.elementToBeClickable(inputItemName)).sendKeys("First Project");

        WebElement freestyleProjectTab =
                getDriver().findElement(By.xpath("//ul[@class ='j-item-options']/li[@tabindex='0']"));
        freestyleProjectTab.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement actualPageHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(actualPageHeader.getText(), expectedPageHeader);

        List<WebElement> configureMenu = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));

        List<String> actualConfigureMenuNames = new ArrayList<>();
        for (WebElement element : configureMenu) {
            actualConfigureMenuNames.add(element.getText());
        }

        Assert.assertEquals(actualConfigureMenuNames, expectedConfigureMenuNames);

        int configureMenuQuantity = actualConfigureMenuNames.size();

        Assert.assertEquals(configureMenuQuantity, 6);
    }

    @Test
    public void testDoesManageJenkinsMenuItemExist() {
        final String expectedMenuItemName = "Manage Jenkins";
        WebElement manageJenkinsMenuItem = getDriver().findElement(By.xpath("//a[@href='/manage']/span[contains(text(), 'Manage')]"));

        Assert.assertEquals(manageJenkinsMenuItem.getText(), expectedMenuItemName);
    }

    @Test
    public void testIsManageJenkinsMenuItemClickable() {
        final String expectedPageHeader = "Manage Jenkins";
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement manageJenkinsMenuItem = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkinsMenuItem.click();

        WebElement pageHeader = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(pageHeader.getText(), expectedPageHeader);
    }

    @Ignore
    @Test
    public void testDoesSysConfSectionContain4Items() {
        List<String> expSysConfItemNames = List.of(
                "Configure System",
                "Global Tool Configuration",
                "Manage Plugins",
                "Manage Nodes and Clouds"
        );
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement manageJenkinsMenuItem = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkinsMenuItem.click();

        List<WebElement> sysConfItems = getDriver().findElements(By.xpath(
                "//section[@class='jenkins-section jenkins-section--bottom-padding'][1]/descendant::dt"));

        List<String> actSysConfItemNames = new ArrayList<>();
        for (WebElement sysConfItem : sysConfItems) {
            actSysConfItemNames.add(sysConfItem.getText());
        }

        Assert.assertEquals(actSysConfItemNames, expSysConfItemNames);
    }

    @Test
    public void testNewItem() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Folder01");
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
        WebElement folder01 = getDriver().findElement(By.xpath("//span[text()='Folder01']"));

        Assert.assertTrue(folder01.isDisplayed());
    }

    @Test
    public void testUserSidePanelMenu() {
        List<String> expectedUserSidePanelMenu = List.of(
                "People",
                "Status",
                "Builds",
                "Configure",
                "My Views",
                "Credentials");

        WebElement userSidePanelMenu = getDriver().findElement(By.xpath("//a[@href='/user/admin']"));
        userSidePanelMenu.click();

        List<WebElement> sidePanelMenu = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));

        List<String> actualUserSidePanelMenu = new ArrayList<>();
        for (WebElement element : sidePanelMenu) {
            actualUserSidePanelMenu.add(element.getText());
        }

        Assert.assertEquals(actualUserSidePanelMenu, expectedUserSidePanelMenu);
    }

    @Test
    public void testAddNewCredentials() {
        WebDriverWait wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[text()= 'Configure credentials ']"))).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h2")).getText(), "Stores scoped to Jenkins");
    }

    @Test
    public void testProjectDisabled() {
        //expected Project Disabled

        //WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement nameBox = getDriver().findElement(By.xpath("//input[@id='name']"));
        getWait10().until(ExpectedConditions.elementToBeClickable(nameBox)).sendKeys("Project001");
        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.cssSelector("label.jenkins-toggle-switch__label")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        WebElement actualProjectDisabled = getDriver().findElement(By.xpath("//form[contains(text(), 'This project is currently disabled')]"));

        Assert.assertTrue(actualProjectDisabled.isDisplayed());
    }

      @Test
    public void testSearchBoxInsensitive() {
        //WebDriverWait wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        getDriver().findElement(By.xpath("//div[@class=\'login page-header__hyperlinks\']//a[@class=\'model-link\']")).click();

        getDriver().findElement(By.xpath("//a[@href='/user/admin/configure']")).click();
        WebElement checkBoxInsensitiveSearch = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='insensitiveSearch']")));

        Assert.assertEquals(checkBoxInsensitiveSearch.getAttribute("checked"), "true");

        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys("built");
        WebElement searchItem = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='yui-ac-bd']/ul/li[1]")));

        Assert.assertEquals(searchItem.getText(), "Built-In Node");

        searchBox.clear();
        searchBox.sendKeys("Built");
        WebElement searchItem2 = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='yui-ac-bd']/ul/li[1]")));

        Assert.assertEquals(searchItem2.getText(), "Built-In Node");
    }


    @Test
    public void testVerifyLogoJenkinsIsPresent() {
        WebElement element = getDriver().findElement(By.cssSelector("img#jenkins-head-icon"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testMakeProjectDisabled() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        WebElement nameInput = getDriver().findElement(By.xpath("//input[@id='name']"));
        getWait10().until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys("First Project");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebElement actualProjectHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(actualProjectHeader.getText(), "Project First Project");

        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();
        WebElement receivedMessage = getDriver().findElement(By.xpath("//div/form[@id='enable-project']"));

        Assert.assertEquals(receivedMessage.getText().substring(0,34), "This project is currently disabled");
    }
}
