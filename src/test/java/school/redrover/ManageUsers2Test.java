package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.ManageUsersPage;
import school.redrover.runner.BaseTest;

public class ManageUsers2Test extends BaseTest {

    @Test
    public void testCreateUser() {
        boolean isNewUserCreated = new ManageUsersPage(getDriver())
                .openUsersPage()
                .clickCreateUserBtn()
                .inputUsername()
                .inputPassword()
                .inputConfirmPassword()
                .inputFullName()
                .inputEmail()
                .clickSubmitBtn()
                .isUserExist();
        Assert.assertTrue(isNewUserCreated);
    }
}
