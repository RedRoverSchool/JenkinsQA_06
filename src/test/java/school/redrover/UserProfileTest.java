package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.CreateUserPage;
import school.redrover.model.ManageUsersPage;
import school.redrover.model.StatusUserPage;
import school.redrover.model.ConfigureUserPage;
import school.redrover.runner.BaseTest;


public class UserProfileTest extends BaseTest {

    protected static final String USER_NAME = "testuser";
    protected static final String PASSWORD = "p@ssword123";
    protected static final String EMAIL = "test@test.com";
    protected static final String USER_FULL_NAME = "Test User";

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

        configureUserPage
                .setEmail("testedited@test.com")
                .clickConfigureSideMenu();

        String actualEmail = configureUserPage.getEmailValue("value");

        Assert.assertNotEquals(actualEmail, oldEmail);
        Assert.assertEquals(actualEmail, displayedEmail);
    }
}
