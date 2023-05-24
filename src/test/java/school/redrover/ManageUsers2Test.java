package school.redrover;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.ManageUsersPage;
import school.redrover.runner.BaseTest;

public class ManageUsers2Test extends BaseTest {
    private final Faker faker = new Faker();

    @Test
    public void testCreateUser() {
        String userName = faker.name().firstName();
        String password = faker.internet()
                .password(5, 10, true, true, true);
        String fullName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        boolean isNewUserCreated = new ManageUsersPage(getDriver())
                .openUsersPage()
                .clickCreateUserBtn()
                .inputUsername(userName)
                .inputPassword(password)
                .inputConfirmPassword(password)
                .inputFullName(fullName)
                .inputEmail(email)
                .clickSubmitBtn()
                .isUserExist(userName);
        Assert.assertTrue(isNewUserCreated);
    }
}
