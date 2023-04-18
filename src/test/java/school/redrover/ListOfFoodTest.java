package school.redrover;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ListOfFoodTest extends BaseTest {

  private final static String PAGE = "https://practicetestautomation.com/practice-test-exceptions/";
  private final static int WAIT_TIME = 10;
  private final static String SAVE_ROW1_INPUT_TEXT = "Row 1 was saved";
  private final static String ADD_ROW2_FIELD = "Row 2 was added";
  private final static String SOME_TEXT_FOR_ROW1_INPUT = "Hello, Row 1 input";
  private final static String SOME_TEXT_FOR_ROW2_INPUT = "Hello, Row 2 input";
  private final By saveButtonRow1 = By.xpath("//div[@id=\"row1\"]/button[@id=\"save_btn\"]");
  private final By saveButtonRow2 = By.xpath("//div[@id=\"row2\"]/button[@name=\"Save\"]");
  private final By addButton = By.id("add_btn");
  private final By editButton = By.id("edit_btn");
  private final By row2 = By.id("row2");
  private final By confirmation = By.xpath("//div[@id=\"confirmation\"]");
  private final By row1InputField = By.xpath("//div[@id=\"row1\"]/input");
  private final By row2InputField = By.xpath("//div[@id=\"row2\"]/input");

  @Test
  public void testWaitLoadRow2() {
    openMainPage();
    clickAddButton();
    waitElement(row2);

    Assert.assertEquals(getConfirmationRow2Text(), ADD_ROW2_FIELD);
  }

  @Test
  public void testWaitLoadRow2AndSendText() {
    openMainPage();
    clickAddButton();
    waitElement(row2);
    row2SendText();
    clickSaveButtonRow2();

    Assert.assertEquals(getRow2SaveText(), SOME_TEXT_FOR_ROW2_INPUT);
  }

  @Test
  public void testEditRow1Text() {
    openMainPage();
    clickEditButton();
    setRow1SendText();
    clickSaveButtonRow1();

    Assert.assertEquals(getRow1SaveText(), SOME_TEXT_FOR_ROW1_INPUT);
    Assert.assertEquals(getConfirmationRow2Text(), SAVE_ROW1_INPUT_TEXT);
  }

  private void clickAddButton() {
    getDriver().findElement(addButton).click();
  }

  private void clickEditButton() {
    getDriver().findElement(editButton).click();
  }

  private void clickSaveButtonRow1() {
    getDriver().findElement(saveButtonRow1).click();
  }

  private void clickSaveButtonRow2() {
    getDriver().findElement(saveButtonRow2).click();
  }

  private void setRow1SendText() {
    WebElement element = getDriver().findElement(row1InputField);
    element.clear();
    element.sendKeys(SOME_TEXT_FOR_ROW1_INPUT);
  }

  private void row2SendText() {
    getDriver().findElement(row2InputField).sendKeys(SOME_TEXT_FOR_ROW2_INPUT);
  }

  private String getConfirmationRow2Text() {
    return getDriver().findElement(confirmation).getText();
  }

  private String getRow1SaveText() {
    return getDriver().findElement(row1InputField).getAttribute("value");
  }

  private String getRow2SaveText() {
    return getDriver().findElement(row2InputField).getAttribute("value");
  }

  private WebElement waitElement(By locator) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  private void openMainPage() {
    getDriver().get(PAGE);
  }
}
