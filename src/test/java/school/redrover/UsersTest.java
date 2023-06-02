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
    public void testCreateNewUser() {
        boolean newUser = new ManageUsersPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickCreateUser()
                .fillUserDetails(USER_NAME)
                .clickYesButton()
                .isUserExist(USER_NAME);

        Assert.assertTrue(newUser);
    }

    @Test
    public void testErrorIfCreateNewUserWithInvalidEmail() {
        String errorEmail = new ManageUsersPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickCreateUser()
                .fillUserDetailsWithInvalidEmail(USER_NAME)
                .clickYesButton()
                .getInvalidEmailError();

        Assert.assertEquals(errorEmail, "Invalid e-mail address");
    }

    @Test
    public void testAddDescriptionToUserOnUserStatusPage() {
        final String displayedDescriptionText = "Test User Description";

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        new ManageUsersPage(getDriver()).clickUserIDName(USER_NAME);

        String actualDisplayedDescriptionText = new StatusUserPage(getDriver())
                .clickAddDescriptionLink()
                .clearDescriptionInputField()
                .enterDescription(displayedDescriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
    }

    @Test(dependsOnMethods = "testAddDescriptionToUserOnUserStatusPage")
    public void testEditDescriptionToUserOnUserStatusPage() {
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
                .enterDescription(displayedDescriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
        Assert.assertNotEquals(actualDisplayedDescriptionText, existingDescriptionText);
    }

    @Test
    public void testAddDescriptionToUserOnTheUserProfilePage() {
        String descriptionText = new ManageUsersPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickUserEditButton()
                .enterDescriptionText()
                .clickYesButton()
                .getDescriptionText();

        Assert.assertEquals("Description text",descriptionText);
    }

        @Test
    public void testEditEmailOnTheUserProfilePageByDropDown() {
        final String displayedEmail = "testedited@test.com";

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        new ManageUsersPage(getDriver())
                .clickUserIDDropDownMenu(USER_NAME)
                .selectConfigureUserIDDropDownMenu();

        ConfigureUserPage configureUserPage = new ConfigureUserPage(getDriver());

        String oldEmail = configureUserPage.getEmailValue("value");

        String actualEmail = configureUserPage
                .enterEmail(displayedEmail)
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
    public void testDeleteUserViaPeopleMenu()  {
        String newUserName = "testuser";
        new CreateUserPage(getDriver())
                .createUserAndReturnToMainPage(newUserName, PASSWORD, USER_FULL_NAME, EMAIL);

        boolean isUserDeleted = new MainPage(getDriver())
                .clickPeopleOnLeftSideMenu()
                .clickUserName(newUserName)
                .clickDeleteUserBtnFromUserPage(newUserName)
                .clickOnYesButton()
                .clickPeopleOnLeftSideMenu()
                .checkIfUserWasDeleted(newUserName);

        Assert.assertTrue(isUserDeleted);

    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testDeleteUserByDeleteButton() {
        boolean userNotFound = new ManageUsersPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickDeleteUser()
                .clickYesButton()
                .getUserDeleted(USER_NAME);

        Assert.assertFalse(userNotFound);
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
