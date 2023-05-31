package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersTest extends BaseTest {
    protected static final String USER_NAME = "testuser";
    protected static final String PASSWORD = "p@ssword123";
    protected static final String EMAIL = "test@test.com";
    protected static final String USER_FULL_NAME = "Test User";
    protected static final String USER_LINK = "//a[@href='user/" + USER_NAME + "/']";
    private final By USER_NAME_LINK = By.xpath(USER_LINK);

    public static List<String> listText(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }

    @Test
    public void testAddDescriptionToUser() {
        final String displayedDescriptionText = "Test User Description";

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        new ManageUsersPage(getDriver()).clickUserIDName(USER_NAME);

        String actualDisplayedDescriptionText = new StatusUserPage(getDriver())
                .clickAddDescriptionLink()
                .clearDescriptionInputField()
                .setDescription(displayedDescriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
    }

    @Test(dependsOnMethods = "testAddDescriptionToUser")
    public void testEditDescriptionToUser() {
        final String displayedDescriptionText = "User Description Updated";

        new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers();

        new ManageUsersPage(getDriver())
                .clickUserIDName(USER_NAME);

        StatusUserPage statusUserPage = new StatusUserPage(getDriver());
        String existingDescriptionText = statusUserPage
                .clickAddDescriptionLink()
                .getDescriptionText();

        String actualDisplayedDescriptionText = statusUserPage
                .clearDescriptionInputField()
                .setDescription(displayedDescriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
        Assert.assertNotEquals(actualDisplayedDescriptionText, existingDescriptionText);
    }

        @Test
    public void testEditEmailByDropDown() {
        final String displayedEmail = "testedited@test.com";

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        new ManageUsersPage(getDriver())
                .clickUserIDDropDownMenu(USER_NAME)
                .selectConfigureUserIDDropDownMenu();

        ConfigureUserPage configureUserPage = new ConfigureUserPage(getDriver());

        String oldEmail = configureUserPage.getEmailValue("value");

        String actualEmail =configureUserPage
                .setEmail("testedited@test.com")
                .clickSaveButton()
                .clickConfigureSideMenu()
                .getEmailValue("value");

        Assert.assertNotEquals(actualEmail, oldEmail);
        Assert.assertEquals(actualEmail, displayedEmail);
    }

    @Test
    public void testVerifyUserPageMenu() {
        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        List<String> listMenuExpected = Arrays.asList("People", "Status", "Builds", "Configure", "My Views", "Delete");

        getDriver().findElement(USER_NAME_LINK).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        List<WebElement> listMenu = getDriver().findElements(By.className("task"));

        for (int i = 0; i < listMenu.size(); i++) {
            Assert.assertEquals(listMenu.get(i).getText(), listMenuExpected.get(i));
        }
    }

    @Test
    public void testViewPeoplePage() {
        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();
        WebElement nameOfPeoplePageHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(nameOfPeoplePageHeader.getText(), "People");
    }

    @Test
    public void testViewIconButtonsPeoplePage() {
        List<String> expectedIconButtonsNames = List.of("S" + "\n" + "mall", "M" + "\n" + "edium", "L" + "\n" + "arge");

        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();
        List<WebElement> iconButtons = getDriver().findElements(By.xpath("//div[@class='jenkins-icon-size']//ol/li"));
        List<String> actualIconButtonsNames = listText(iconButtons);

        Assert.assertEquals(actualIconButtonsNames, expectedIconButtonsNames);
    }

    @Test
    public void testSortArrowModeChangesAfterClickingSortHeaderButton() {
        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();

        WebElement userIdBtnNoSortArrowBeforeClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        Assert.assertTrue(userIdBtnNoSortArrowBeforeClick.getText().isEmpty());

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]")).click();

        String userIdBtnSortArrowUpAfterFirstClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(userIdBtnSortArrowUpAfterFirstClick, "  ↑");

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]")).click();

        String userIdBtnSortArrowDownAfterSecondClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(userIdBtnSortArrowDownAfterSecondClick, "  ↓");

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'Name')]")).click();

        WebElement userIdBtnNoArrowAfterAnotherButtonClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        Assert.assertTrue(userIdBtnNoArrowAfterAnotherButtonClick.getText().isEmpty());
    }

    @Test
    public void testSearchPeople() {
        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        WebElement searchField = getDriver().findElement(
                By.xpath("//input[@name='q']"));
        searchField.sendKeys(USER_NAME);
        searchField.sendKeys(Keys.RETURN);

        WebElement actualUserName = getDriver().findElement(
                By.xpath("//div[contains(text(), 'Jenkins User ID:')]"));

        Assert.assertEquals(actualUserName.getText(), "Jenkins User ID: " + USER_NAME);
    }

    @Test
    public void testDeleteUserViaPeopleMenu() {
        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        new WebDriverWait(getDriver(), Duration.ofSeconds(2)).until(
                ExpectedConditions.visibilityOf(getDriver().findElement(By.id("people"))));
        getDriver().findElement(By.id("jenkins-home-link")).click();
        getDriver().findElement(By.xpath("//*[@href='/asynchPeople/']")).click();

        WebElement userToDelete = getDriver().findElement(
                By.xpath("//a[@href='/user/" + USER_NAME + "/']"));
        userToDelete.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOf(getDriver().findElement(By.id("main-panel"))));

        getDriver().findElement(By.xpath("//a[@href='/user/" + USER_NAME + "/delete']")).click();

        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//*[@href='/asynchPeople/']")).click();

        Boolean isNotPresent = ExpectedConditions.not(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/user/" + USER_NAME + "/']")))
                .apply(getDriver());
        Assert.assertTrue(isNotPresent);
    }

    @Test
    public void testDeleteUserViaManageUsers() {

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        WebElement basketButtonSelectedUser = getDriver().findElement(
                By.xpath("//a[@href='user/" + USER_NAME + "/delete']"));
        basketButtonSelectedUser.click();

        getDriver().findElement(By.name("Submit")).click();

        Boolean userIsNotFind = ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[@href='user/" + USER_NAME + "/']"))).apply(getDriver());

        Assert.assertTrue(userIsNotFind);
    }

    @Test(dependsOnMethods = "testDeleteUserViaManageUsers")
    public void testLogInWithDeletedUserCredentials() {
        getDriver().findElement(By.xpath("//a[@href= '/logout']")).click();
        getDriver().findElement(By.id("j_username")).sendKeys(USER_NAME);
        getDriver().findElement(By.xpath("//input[@name='j_password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By
                        .xpath("//div[contains(@class, 'alert-danger')]")).getText(),
                "Invalid username or password");
    }
}
