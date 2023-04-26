package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class TopMenuTest extends BaseTest {

    @Test
    public void testCheckPeopleButton() {
        WebElement buttonPeople = getDriver().findElement(By.linkText("People"));
        boolean actualResult = buttonPeople.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testUserButton() {
        WebElement buttonAdmin = getDriver().findElement(By.xpath("//header/div/a[@class = 'model-link']"));
        buttonAdmin.click();

        WebElement userIDDescription = getDriver().findElement(By.xpath("//div[@id='main-panel']//div[contains(text(), " +
                "'Jenkins User ID:')]"));
        String userID = userIDDescription.getText().split(": ")[1];

        Assert.assertEquals(userID, "admin");
    }

    @Test
    public void testSetOfElementsPeople() {
        WebElement buttonPeople = getDriver().findElement(By.linkText("People"));
        buttonPeople.click();

        WebElement fourElements = getDriver().findElement(By.xpath("//table[@id = \"people\"]"));
        boolean actualResult = fourElements.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testTopMenuUser(){
        WebElement topMenuUser = getDriver().findElement(By.xpath("//span[@class='hidden-xs hidden-sm'][text()='admin']"));

        String actualResult1 = topMenuUser.getText();

        Assert.assertEquals(actualResult1, "admin");
    }

    @Test
    public void testCreateNewItem() {
        WebElement newItem = getDriver().findElement(By.linkText("New Item"));
        newItem.click();

        WebElement enterItemNameField = getDriver().findElement(By.xpath("//input[@id='name']"));
        enterItemNameField.sendKeys("TestFirst");

        WebElement folderType = getDriver().findElement(By.xpath("//span[text()='Folder']"));
        folderType.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        WebElement displayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        displayName.sendKeys("FirstFolder");

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='_.description']"));
        description.sendKeys("TestOne");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        WebElement folderArea = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(folderArea.getText(), "FirstFolder");
    }
}