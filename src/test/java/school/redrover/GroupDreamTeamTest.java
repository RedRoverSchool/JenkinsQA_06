package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    public void testNewFreestyleProjectCreated() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2000));

        WebElement createAJobArrow = getDriver().findElement(
                By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']")
        );
        createAJobArrow.click();

        WebElement inputItemName = getDriver().findElement(By.id("name"));
        wait.until(ExpectedConditions.elementToBeClickable(inputItemName)).sendKeys("Project1");

        WebElement freestyleProjectTab = getDriver().findElement(
                By.xpath("//ul[@class = 'j-item-options']/li[@tabindex='0']")
        );
        freestyleProjectTab.click();

        WebElement okButton = getDriver().findElement(By.className("btn-decorator"));
        okButton.click();

        WebElement dashboardLink = getDriver().findElement(
                By.xpath("//ol[@id='breadcrumbs']/li/a[text() = 'Dashboard']")
        );
        dashboardLink.click();

        Assert.assertTrue(getDriver().findElement(By.id("projectstatus")).isDisplayed());

        List<WebElement> newProjectsList = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));

        Assert.assertEquals(newProjectsList.size(), 1);

        List<WebElement> projectDetailsList = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']/tbody/tr/td")
        );

        Assert.assertEquals(projectDetailsList.get(2).getText(), "Project1");
    }

    @Test
    public void testJenkinsMainPageLilia() {
        WebElement headerWelcome = getDriver().findElement(By.tagName("h1"));
        Assert.assertEquals(headerWelcome.getText(), "Welcome to Jenkins!");

        WebElement addDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescription.click();
        WebElement textBox = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textBox.sendKeys("Hello Jenkins!");
        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();
    }

    @Test
    public void testDashboardSidePanelItemsList() {
        List<WebElement> sidePanelItems = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));
        int itemsQuantity = sidePanelItems.size();

        Assert.assertEquals(itemsQuantity, 5);
    }

    @Test
    public void testSideMenu() {
        List <String> expectedMenus = List.of("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        List<WebElement> sideMenus = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));
        List<String> menuNames = new ArrayList<>();
        for (WebElement element: sideMenus){
            menuNames.add(element.getText());
        }

        Assert.assertEquals(menuNames, expectedMenus);
    }

    @Test
    public void testConfigureItemsMenu() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2000));

        List <String> expectedConfigureMenuNames = List.of(
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

        List<WebElement> configureMenu = getDriver().findElements(By.xpath("//div[@id='tasks']/div"));

        List<String> actualConfigureMenuNames = new ArrayList<>();
        for (WebElement element: configureMenu){
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

    @Test
    public void testDoesSysConfSectionContain4Items() {
        List<String> expSysConfItemNames = List.of("Configure System", "Global Tool Configuration", "Manage Plugins", "Manage Nodes and Clouds");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        getDriver().get(getDriver().getCurrentUrl() + "/manage/");
        List<WebElement> sysConfItems = getDriver().findElements(By.xpath("//section[@class='jenkins-section jenkins-section--bottom-padding'][1]/descendant::dt"));

        List<String> actSysConfItemNames = new ArrayList<>();
        for (WebElement sysConfItem: sysConfItems) {
            actSysConfItemNames.add(sysConfItem.getText());
        }

        Assert.assertEquals(actSysConfItemNames, expSysConfItemNames);
    }
}
