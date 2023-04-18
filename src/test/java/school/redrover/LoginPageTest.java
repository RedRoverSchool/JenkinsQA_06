package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class LoginPageTest extends BaseTest {

  private final static String EXPECTED_URL = "https://practicetestautomation.com/logged-in-successfully/";
  private final static String EXPECTED_TEXT = "Logged In Successfully";
  private final static String USERNAME_IS_INVALID = "Your username is invalid!";
  private final static String PASSWORD_IS_INVALID = "Your password is invalid!";
  private final static String INCORRECT_USER = "incorrectUser";
  private final static String INCORRECT_PASS = "incorrectPassword";
  private final static String PAGE = "https://practicetestautomation.com/practice-test-login/";
  private final String EXPECTED_COLOR = "rgba(227, 72, 72, 1)";
  private final String PASSWORD = "Password123";
  private final String USERNAME = "student";
  private final By userNameID = By.id("username");
  private final By passwordID = By.id("password");
  private final By submitID = By.id("submit");
  private final By errorID = By.id("error");
  private final By post_text = By.xpath("//h1[@class=\"post-title\"]");
  private final By log_out = By.xpath(
    "//a[@class=\"wp-block-button__link has-text-color has-background has-very-dark-gray-background-color\"]");


  @Test
  public void testPositiveLogin() {
    mainPage();
    sendUsername(USERNAME);
    sendPassword(PASSWORD);
    clickSubmitButton();

    Assert.assertEquals(getCurrentUrl(), EXPECTED_URL);
    Assert.assertEquals(getActualText(), EXPECTED_TEXT);
    Assert.assertTrue(isDisplayedLogOut());
  }

  @Ignore
  @Test
  public void testNegativeUsername() {
    mainPage();
    sendUsername(INCORRECT_USER);
    sendPassword(PASSWORD);
    clickSubmitButton();

    Assert.assertEquals(getBackgroundColor(), EXPECTED_COLOR);
    Assert.assertEquals(getErrorMessage(), USERNAME_IS_INVALID);
  }

  @Ignore
  @Test
  public void testNegativePassword() {
    mainPage();
    sendUsername(USERNAME);
    sendPassword(INCORRECT_PASS);
    clickSubmitButton();

    Assert.assertEquals(getBackgroundColor(), EXPECTED_COLOR);
    Assert.assertEquals(getErrorMessage(), PASSWORD_IS_INVALID);
  }

  private void mainPage() {
    getDriver().get(PAGE);
  }

  private void sendUsername(String username) {
    getDriver().findElement(userNameID).sendKeys(username);
  }

  private void sendPassword(String password) {
    getDriver().findElement(passwordID).sendKeys(password);
  }

  private void clickSubmitButton() {
    getDriver().findElement(submitID).click();
  }

  private boolean isDisplayedLogOut() {
    return getDriver().findElement(log_out).isDisplayed();
  }

  private String getActualText() {
    return getDriver().findElement(post_text).getText();
  }

  private String getCurrentUrl() {
    return getDriver().getCurrentUrl();
  }

  private String getErrorMessage() {
    return getDriver().findElement(errorID).getText();
  }

  private String getBackgroundColor() {
    return getDriver().findElement(errorID).getCssValue("background-color");
  }
}
