package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
}

