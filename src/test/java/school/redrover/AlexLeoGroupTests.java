package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlexLeoGroupTests extends BaseTest {

    private static final String USER_FULL_NAME = RandomStringUtils.randomAlphanumeric(13);

    private static final String DESCRIPTION = RandomStringUtils.randomAlphanumeric(130) + "\n\n" + RandomStringUtils.randomAlphanumeric(23);

    private static final By USER_NAME_LINK = By.xpath("//a[@href='/user/admin']");

    @Test
    public void testVerifyLogoJenkinsIsPresent() {
        WebElement element = getDriver().findElement(By.cssSelector("img#jenkins-head-icon"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testVerifyWordIconJenkinsPresent() {
        WebElement logoWord = getDriver()
                .findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(logoWord.isDisplayed());
    }

    @Test
    public void validateJenkinsLoginTest() {
        String verify = getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1")).getText();
        Assert.assertEquals(verify, "Welcome to Jenkins!");
    }

    @Test
    public void testVerifyPeopleLinkTextPresentOnHomePage() {
        List<WebElement> elems = getDriver().findElements(By.xpath("//span[@class = 'task-link-text']"));
        String PeopleText = elems.get(1).getText();

        Assert.assertEquals(PeopleText, "People");
    }

    @Test
    public void testVerifyBuildQueueTextPresentOnHomePage() {
        List<WebElement> elems = getDriver().findElements(By.xpath("//span[@class = 'pane-header-title']"));
        String BuildQueueText = elems.get(0).getText();

        Assert.assertEquals(BuildQueueText, "Build Queue");
    }

    @Test
    public void testVerifyIconButtonsRowPresent() {
        getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']")).click();
        WebElement iconButtonsRow = getDriver().
                findElement(By.xpath("//div[contains(@class, 'jenkins-buttons-row')]"));

        Assert.assertTrue(iconButtonsRow.isDisplayed());
    }

    @Test
    public void testVerifyFolderLabelFont() {
        getDriver().findElement(By.xpath("//a[contains(@href,'newJob')]")).click();
        WebElement elem = getDriver().findElement(By.id("items"));
        List<WebElement> items = elem.findElements(By.cssSelector("li span"));

        for (WebElement element : items) {
            Assert.assertTrue(element.getAttribute("baseURI").contains("newJob"));
        }
    }

    @Test
    public void testLogoJenkinsIsPresent() {
        WebElement logoJenkins = getDriver().findElement(By.xpath("//img[@id='jenkins-head-icon']"));
        Assert.assertTrue(logoJenkins.isDisplayed());
    }

    @Test
    public void testWordIconJenkinsIsPresent() {
        WebElement wordJenkins = getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']"));
        Assert.assertTrue(wordJenkins.isDisplayed());
    }

    @Test
    public void testJenkinsLogoVerification() {
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testJenkinsWordIconVerification() {
        WebElement icon = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(icon.isDisplayed());
    }

    @Test
    public void testSearchFieldVerification() {
        WebElement searchField = getDriver().findElement(By.xpath("//input[@role='searchbox']"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void testLogOutIconVerification() {
        WebElement logOutIcon = getDriver().findElement(By.xpath("//a[@href='/logout']"));
        Assert.assertTrue(logOutIcon.isDisplayed());
    }

    @Test
    public void testLogOutIconTextVerification() {
        String logOutIconText = getDriver().findElement(By.xpath("//a[@href='/logout']/span")).getText();
        Assert.assertEquals(logOutIconText, "log out");
    }

    @Test
    public void testDashboardMenuVerification() {
        String dashboardMenu = getDriver().findElement(By.xpath("//li/a[@class='model-link']")).getText();
        Assert.assertEquals(dashboardMenu, "Dashboard");
    }

    @Test
    public void testNewItemSectionVerification() {
        String newItemText = getDriver().findElement(By.xpath("//span[contains(text(), 'New Item')]")).getText();
        Assert.assertEquals(newItemText, "New Item");
    }

    @Test
    public void testRestApiLinkVerification() {
        getDriver().findElement(By.xpath("//a[@href='api/']")).click();
        WebElement expectedElement = getDriver().findElement(By.xpath("//div/h1[contains(text(), 'REST API')]"));
        Assert.assertTrue(expectedElement.isDisplayed());
    }

    @Test
    public void testJenkinsLinkInFooterVerification() {
        WebElement jenkinsLink = getDriver().findElement(By.xpath("//div/a[@href='https://www.jenkins.io/']"));
        String originalWindow = getDriver().getWindowHandle();
        jenkinsLink.click();

        for (String windowHandle : getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        String actualWebPage = getDriver().getCurrentUrl();
        String expectedWebPage = "https://www.jenkins.io/";
        Assert.assertEquals(actualWebPage, expectedWebPage);
    }

    @Test
    public void testNewItemListVerification() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='items']"))));

        List<WebElement> newItemProjectTypes = getDriver().findElements(By.xpath("//ul/li/label/span[@class='label']"));
        List<String> expectedProjectTypes = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project",
                "Folder", "Multibranch Pipeline", "Organization Folder");

        for (int i = 0; i < newItemProjectTypes.size(); i++) {
            String actualProjectType = newItemProjectTypes.get(i).getText();
            Assert.assertEquals(actualProjectType, expectedProjectTypes.get(i));
        }
    }

    @Test
    public void testVerifyLogoJenkinsIsPresentInMenuBar() {
        WebElement logoJenkins = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(logoJenkins.isDisplayed());
    }

    @Test
    public void testJenkinsLogoIsPresent() {
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testJenkinsNameIsPresent() {
        WebElement name = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(name.isDisplayed());
    }

    @Test
    public void testSearchBoxIsPresent() {
        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        Assert.assertTrue(searchBox.isDisplayed());
    }

    @Test
    public void testLogoutIconIsPresent() {
        WebElement logoutIcon = getDriver().findElement(By.xpath("//a[@href='/logout']/*[@class='icon-md']"));
        Assert.assertTrue(logoutIcon.isDisplayed());
    }

    @Test
    public void testLinkContainsText() {
        String logoutLink = getDriver().findElement(By.xpath("//a[@href='/logout']/span")).getText();
        Assert.assertEquals(logoutLink, "log out");
    }

    @Ignore
    @Test
    public void testSearchField() {
        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys("");
        searchBox.sendKeys(Keys.RETURN);

        Assert.assertTrue(getWait5().until(ExpectedConditions.textToBe
                (By.xpath("//div[@class='jenkins-app-bar__content']/h1"), "Built-In Node")));
    }

    @Test
    public void testNewFreestyleProjectVerification() {
        String nameOfProject = "NewProject2023";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='items']"))));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        WebElement projectName = getDriver().findElement(By.xpath("//h1[starts-with(text(), 'Project ')]"));

        Assert.assertEquals(projectName.getText(), "Project " + nameOfProject);
    }

    @Test
    public void testNewFreestyleProjectDisabledVerification() {
        String nameOfProject = "NewProject2023";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='items']"))));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#disable-project > button")).click();

        WebElement disabledNote = getDriver().findElement(By.cssSelector("#enable-project"));
        String actualString = disabledNote.getText().substring(0, disabledNote.getText().indexOf("\n"));
        String expectedString = "This project is currently disabled";

        Assert.assertEquals(actualString, expectedString);
    }

    @Test
    public void testAPILinkInTheFooter() {
        WebElement apiLinkButton = getDriver().findElement(By.xpath("//a[text()='REST API']"));
        apiLinkButton.click();

        Assert.assertEquals(getDriver().getTitle(), "Remote API [Jenkins]");
    }

    @Test
    public void testVerifySearchField() {
        Assert.assertTrue(getDriver().findElement(By.id("search-box")).isDisplayed());
    }

    @Test
    public void testVerifyLogOutIcon() {
        Assert.assertTrue(getDriver()
                .findElement(By.cssSelector("header#page-header > div > a:last-of-type > svg")).isDisplayed());
    }

    @Test
    public void testVerifyLogOutLink() {
        WebElement spanLogOut = getDriver().findElement(By.xpath("//header/div/a[@href='/logout']/span"));

        Assert.assertEquals(spanLogOut.getText(), "log out");
    }

    @Test
    public void testVerifyTextInDropDownMenu() {
        WebElement dashboardLink = getDriver().findElement(By.cssSelector("#breadcrumbs > li > a"));

        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
    }

    @Test
    public void testVerifyNewItemSectionPresent() {
        WebElement spanNewItem = getDriver()
                .findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]"));

        Assert.assertEquals(spanNewItem.getText(), "New Item");
    }

    @Test
    public void testVerifyJenkinsLogoIsPresent() {
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testVerifyWordIconJenkinsIsPresent() {
        WebElement wordIcon = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(wordIcon.isDisplayed());
    }

    @Test
    public void testVerifySearchFieldIsPresent() {
        WebElement searchField = getDriver().findElement(By.id("searchform"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void testVerifyLogoutIconIsPresent() {
        WebElement logoutIcon = getDriver().findElement(By.xpath("//a[@href = '/logout']/*[local-name()='svg']"));
        Assert.assertTrue(logoutIcon.isDisplayed());
    }

    @Test
    public void testVerifyLogoutLinkText() {
        WebElement logout = getDriver().findElement(By.xpath("//a[@href = '/logout']/span"));
        Assert.assertEquals(logout.getText(), "log out");
    }

    @Test
    public void testVerifyDashboardDropDownText() {
        WebElement dashboardDropDown = getDriver().findElement(By.xpath("//li[@class = 'jenkins-breadcrumbs__list-item']/a"));
        Assert.assertEquals(dashboardDropDown.getText(), "Dashboard");
    }

    @Test
    public void testVerifyNewItemIcon() {
        WebElement newItem = getDriver().findElement(By.linkText("New Item"));
        Assert.assertTrue(newItem.isDisplayed());
    }

    @Test
    public void testVerifyRestApiLink() {
        WebElement restApiButton = getDriver().findElement(By.linkText("REST API"));
        restApiButton.click();
        WebElement restApiHeader = getDriver().findElement(By.xpath(".//h1[text() = 'REST API']"));

        Assert.assertTrue(getDriver().getCurrentUrl().endsWith("/api/"));
        Assert.assertTrue(restApiHeader.isDisplayed());
    }

    @Test
    public void testVerifyJenkinsLink() {
        WebElement jenkinsLink = getDriver().findElement(By.linkText("Jenkins 2.387.2"));

        String originalWindow = getDriver().getWindowHandle();
        jenkinsLink.click();

        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                if (getDriver().getTitle().equals("Jenkins"))
                    break;
            }
        }
        WebElement jenkinsHeader = getDriver().findElement(By.xpath(".//h1[@class = 'page-title']/span[contains(text(), 'Jenkins')]"));

        Assert.assertTrue(jenkinsHeader.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/");
    }

    @Test
    public void testVerifyNewItemsList() {
        List<String> expectedItems = new ArrayList<>(Arrays.asList("Freestyle project", "Pipeline",
                "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder"));
        WebElement newItem = getDriver().findElement(By.linkText("New Item"));
        newItem.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        List<WebElement> items = wait.
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[@id = 'items']//li//span")));

        Assert.assertEquals(items.size(), expectedItems.size());

        for (int i = 0; i < items.size(); i++) {
            Assert.assertEquals(items.get(i).getText(), expectedItems.get(i));
        }
    }

    @Test
    public void testVerifyLogoJenkins() {
        Assert.assertTrue(getDriver().findElement(By.id("jenkins-head-icon")).isDisplayed());
    }

    @Ignore
    @Test
    public void testVerifyUserPageMenu() {
        List<String> listMenuExpected = Arrays.asList("People", "Status", "Builds", "Configure", "My Views", "Credentials");

        getDriver().findElement(By.xpath("//a[@class='model-link']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        List<WebElement> listMenu = getDriver().findElements(By.className("task"));

        for (int i = 0; i < listMenu.size(); i++) {
            Assert.assertEquals(listMenu.get(i).getText(), listMenuExpected.get(i));
        }
    }

    @Ignore
    @Test
    public void testVerifyChangeNameUser() {
        getDriver().findElement(USER_NAME_LINK).click();

        WebElement configure = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/user/admin/configure']")));
        configure.click();

        WebElement fullName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.fullName']")));
        fullName.clear();
        fullName.sendKeys(USER_FULL_NAME);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(USER_NAME_LINK).getText(), USER_FULL_NAME);
    }

    @Test
    public void testTasksMenuNavigation() {
        WebElement newItemElementLink = getDriver().findElement(By.linkText("New Item"));
        newItemElementLink.click();
        WebElement textEnterAnItemNameOnPage = getDriver().findElement(By.xpath("//div[@class='add-item-name']/label[.='Enter an item name']"));
        Assert.assertEquals(textEnterAnItemNameOnPage.getText(), "Enter an item name");
        WebElement dashboardReturn = getDriver().findElement(By.linkText("Dashboard"));
        dashboardReturn.click();

        WebElement peopleLink = getDriver().findElement(By.linkText("People"));
        peopleLink.click();
        WebElement textPeopleInThePageHeader = getDriver().findElement(By.xpath("//div[@id='main-panel']/div/div/h1"));
        Assert.assertEquals(textPeopleInThePageHeader.getText(), "People");

        WebElement buildHistoryLink = getDriver().findElement(By.linkText("Build History"));
        buildHistoryLink.click();
        WebElement textJenkinsBuildHistoryInThePageHeader = getDriver().findElement(By.xpath("//div[@id='main-panel']/div/div/h1"));
        Assert.assertEquals(textJenkinsBuildHistoryInThePageHeader.getText(),"Build History of Jenkins");

        WebElement manageJenkinsLink = getDriver().findElement(By.linkText("Manage Jenkins"));
        manageJenkinsLink.click();
        WebElement textManageJenkinsInPageHeader = getDriver().findElement(By.xpath("//div[@id='main-panel']/div/div/h1"));
        Assert.assertEquals(textManageJenkinsInPageHeader.getText(), "Manage Jenkins");
    }
    @Test
    public void testVerifyJenkinsLogo(){
        WebElement findLogoJenkins = getDriver().findElement((By.id("jenkins-head-icon")));
        Point point = new Point(19, 8);
        Assert.assertEquals(findLogoJenkins.getLocation(), point);

    }

    @Ignore
    @Test
    public void testVerifyUserDescription() {
        getDriver().findElement(USER_NAME_LINK).click();

        WebElement editDescription = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='description-link']")));
        editDescription.click();

        WebElement fullName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));
        fullName.clear();
        fullName.sendKeys(DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div")).getText(), DESCRIPTION);
    }

    @Test
    public void testVerifySomething() {
        List<String> listSystemConfigurationExpected = Arrays.asList
                ("System Configuration", "Security", "Status Information", "Troubleshooting", "Tools and Actions");

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Manage')]")));
        List<WebElement> listSystemConfiguration = getDriver().findElements(By.cssSelector(".jenkins-section__title"));
        for (int i = 0; i < listSystemConfiguration.size(); i++) {

            Assert.assertEquals(listSystemConfiguration.get(i).getText(), listSystemConfigurationExpected.get(i));
        }
    }
}
