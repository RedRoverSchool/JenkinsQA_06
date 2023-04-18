package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class IuliiaKuznetcovaTest extends BaseTest {

    WebDriver driver;
    Faker faker = new Faker();
    String firstName = faker.internet().uuid();
    String lastName = faker.internet().uuid();
    String postCode = faker.address().zipCode();

    By firstNameField = By.xpath("//input[@placeholder='First Name']");
    By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    By postCodeField = By.xpath("//input[@placeholder='Post Code']");
    By homeButton = By.xpath("//*[@ng-click='home()']");
    By addCustomerRegistrationButton = By.xpath("//button[@type='submit']");

    @Test
    public void testAddCastomerGlobalsqa() throws InterruptedException {
        getDriver().get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust");
        Thread.sleep(1000);

        fillField(firstName, firstNameField);
        fillField(lastName, lastNameField);
        fillField(postCode, postCodeField);
        getDriver().findElement(addCustomerRegistrationButton).click();

        getDriver().switchTo().alert().accept();
        getDriver().findElement(homeButton).click();
    }

    private void fillField(String userData, By locator) {
        getDriver().findElement(locator).click();
        getDriver().findElement(locator).sendKeys(userData);
    }
}


