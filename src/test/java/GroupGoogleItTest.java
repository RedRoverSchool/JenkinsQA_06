import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;


public class GroupGoogleItTest extends BaseTest {

    private WebElement findFolderElementByTextLabel(String label) {
        List<WebElement> listWebElements = getDriver().findElements(By.xpath("//span[@class = 'label']"));
        WebElement result = null;
        for (WebElement element : listWebElements){
            if (element.getText().equals(label)){
                result = element;
            }
        }
        return result;
    }

    @Test
    public void testSimple() {
       WebElement welcomeElement =  getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testIfErrorMessageIsDisplayedWhenAnItemNameContainsPoundAsSecondSymbol() throws InterruptedException {
        String expectedResult = "Error";
        String folderName = "a#";
        WebElement newItemIcon = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        Thread.sleep(2000);
        newItemIcon.click();
        Thread.sleep(2000);
        WebElement folder = findFolderElementByTextLabel("Folder");
        folder.click();
        WebElement enterAnItemNameBar = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        enterAnItemNameBar.sendKeys(folderName);
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        okButton.click();
        WebElement errorMessage = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);
    }

    @Test
    public void TestTheSearchBarOnTheDashBoardWithNoMatchingResults() throws InterruptedException {
        String expectedResult = "Nothing seems to match.";
        Thread.sleep(2000);
        WebElement searchBarOnDashBoard = getDriver().findElement(By.xpath("//input[@role = 'searchbox']"));
        searchBarOnDashBoard.click();
        Thread.sleep(3000);
        searchBarOnDashBoard.sendKeys("Java");
        searchBarOnDashBoard.sendKeys(Keys.ENTER);
        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@class = 'error']"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);




    }
}
