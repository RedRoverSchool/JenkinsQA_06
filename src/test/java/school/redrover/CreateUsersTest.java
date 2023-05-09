package school.redrover;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.runner.BaseTest;

import java.util.List;

public class CreateUsersTest extends BaseTest {

  @Test
  public void testCreateUserCheckIDUserPage (){

    WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
    manageJenkins.click();

    WebElement manageUsers = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//dt[text() = 'Manage Users']")));
    manageUsers.click();

    WebElement createUser = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href = 'addUser']")));
    createUser.click();

    List<WebElement> valueInputs = getDriver().findElements(By.xpath("//*[@class = 'jenkins-input']"));
    for (int i = 0; i <valueInputs.size(); i++) {
      if (i == 0) {
        valueInputs.get(i).sendKeys("Test");
      } else {
        valueInputs.get(i).sendKeys("test@test.com");
      }
    }
    getDriver().findElement(By.name("Submit")).click();

    WebElement result = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='people']/tbody/tr[2]/td[2]/a")));
    Assert.assertTrue(result.isDisplayed());
    Assert.assertEquals(result.getText(), "Test");

    getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__button jenkins-!-destructive-color']")).click();
    getDriver().findElement(By.name("Submit"));
  }
}
