package school.redrover.old;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class GroupGoogleItKiraKomissarovaTest extends BaseTest {

   private static final String SITE_URL= "https://www.99-bottles-of-beer.net/";

   @Test
   public void testVerifySearchResults() throws InterruptedException {
      String expectedResult = "Kotlin";

      getDriver().get(SITE_URL);
      WebElement searchButton = getDriver().findElement(By.xpath("//a[@href = '/search.html']"));

      searchButton.click();

      WebElement searchBar = getDriver().findElement(By.xpath("//input[@name = 'search']"));

      Thread.sleep(3000);
      searchBar.sendKeys("Kotlin");

      WebElement goButton = getDriver().findElement(By.name("submitsearch"));
      goButton.click();

      WebElement kotlinInTable = getDriver().findElement(By.xpath("//a[@href = '/language-kotlin-2901.html']"));

      Assert.assertEquals(kotlinInTable.getText(),expectedResult);
   }

   @Test
   public void testErrorMessageIfSigningInGuestBook() {
      String expectedResult = "Error: Please enter at least a message, your email address and the security code.";

      getDriver().get(SITE_URL);
      WebElement guestBookLink = getDriver().findElement(By.xpath("//a[@href = '/guestbookv2.html']"));
      guestBookLink.click();
      WebElement signGuestBookLink = getDriver().findElement(By.xpath("//a[@href = './signv2.html']"));
      signGuestBookLink.click();
      WebElement submitButton = getDriver().findElement(By.xpath("//input[@type= 'submit']"));
      submitButton.click();
      WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id= 'main']/p"));
      String textOfErrorMessage = errorMessage.getText();

      Assert.assertEquals(textOfErrorMessage, expectedResult);
   }

   @Test
   public void testURLIsTheSameIfOnSearchPagePressGoWithEmptyField() {
      String expectedResult = "https://www.99-bottles-of-beer.net/search.html";

      getDriver().get(SITE_URL);
      WebElement searchLanguageLink = getDriver().findElement(By.cssSelector("[href = '/search.html']"));
      searchLanguageLink.click();
      WebElement goButton = getDriver().findElement(By.xpath("//input[@type = 'submit']"));
      goButton.click();
      String actualResultTitle = getDriver().getCurrentUrl();

      Assert.assertEquals(actualResultTitle, expectedResult);
   }
}