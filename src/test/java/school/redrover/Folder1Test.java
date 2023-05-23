package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder1Test extends BaseTest {
   private static final String NAME = RandomStringUtils.randomAlphanumeric(10);

    @Test
    public void testCreateNewFolder() {
        TestUtils.createFolder(this, NAME,false);

        Assert.assertEquals(NAME, getDriver().findElement(By.cssSelector("div[id='main-panel'] h1")).getText());
    }

    @Test
    public void testCreateFolderWithDescription() {
        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys(NAME);

        WebElement folder = getDriver().findElement(By.xpath("//span[@class='label'][text()='Folder']"));
        folder.click();

        WebElement button = getDriver().findElement(By.cssSelector("#ok-button"));
        button.click();

        WebElement displayName = getDriver().findElement(By.name("_.displayNameOrNull"));
        displayName.sendKeys(NAME);

        WebElement submit = getDriver().findElement(By.name("Submit"));
        submit.click();

        Assert.assertEquals(NAME, getDriver().findElement(By.cssSelector("div[id='main-panel'] h1")).getText());
    }

    @Test
    public void testRenameFolder() {
        TestUtils.createFolder(this, NAME,false);

        WebElement rename = getDriver().findElement(By.xpath("//a[@href='/job/" + NAME + "/confirm-rename']"));
        rename.click();

        WebElement renameText = getDriver().findElement(By.name("newName"));
        renameText.clear();
        renameText.sendKeys("Rename");

        WebElement submit = getDriver().findElement(By.name("Submit"));
        submit.click();

        Assert.assertEquals("Rename", getDriver().findElement(By.cssSelector("div[id='main-panel'] h1")).getText());
    }

    @Test
    public void testMoveFolderToFolder(){
        final String folderFirst = RandomStringUtils.randomAlphanumeric(10);
        final String folderSecond = RandomStringUtils.randomAlphanumeric(10);

        TestUtils.createFolder(this, folderFirst, true);
        TestUtils.createFolder(this, folderSecond, true);

        WebElement chevron = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'job/" + folderSecond + "/')]/button[@class='jenkins-menu-dropdown-chevron']")));
        chevron.sendKeys(Keys.RETURN);

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='first-of-type']/li[6]"))).click();

        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(By.name("destination")))).selectByIndex(1);
        getDriver().findElement(By.name("Submit")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'job/" + folderSecond + "/')]"))).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + folderSecond + "/')]"))).isDisplayed());
    }

    @DataProvider(name = "invalid character")
    public Object[][] invalidCharactersData() {
        return new Object[][]{{"!"}, {"#"}, {"$"}, {"%"}, {"&"}, {"*"}, {"/"}, {":"},
                {";"}, {"<"}, {">"}, {"?"}, {"@"}, {"["}, {"]"}, {"|"}, {"\\"}, {"^"}};
    }

    @Test(dataProvider = "invalid character")
    public void testFolderCreationWithInvalidCharacters(String invalidCharacter) {
        String errorMessage = "» ‘" + invalidCharacter + "’ is an unsafe character";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Folder']"))).click();

        WebElement inputLine = getDriver().findElement(By.id("name"));
        inputLine.clear();
        inputLine.sendKeys(invalidCharacter);

        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.id("itemname-invalid"), errorMessage));
        Assert.assertEquals(errorMessage, getDriver().findElement(By.id("itemname-invalid")).getText());
    }
}

