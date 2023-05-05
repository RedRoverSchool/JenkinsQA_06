package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder2Test extends BaseTest {

    @Test
    public void testFolderCreation() {
        final String FOLDER_NAME = "My_folder";

        WebElement newItem = getDriver().findElement(By.xpath(
                "//a[@href='/view/all/newJob']"));
        newItem.click();

        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("name"))));
        WebElement nameField = getDriver().findElement(By.id("name"));
        nameField.sendKeys(FOLDER_NAME);
        WebElement folderType = getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder"));
        folderType.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        getWait2().until(ExpectedConditions.textToBe(By.tagName("h1"), FOLDER_NAME));

        WebElement dashboardMenu = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardMenu.click();

        WebElement folderInTheList = getDriver().findElement(By.xpath
                ("//a[@class='jenkins-table__link model-link inside']/span"));
        Assert.assertEquals(folderInTheList.getText(), FOLDER_NAME);

        folderInTheList.click();

        WebElement header = getDriver().findElement(By.tagName("h1"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains(FOLDER_NAME));
        Assert.assertEquals(header.getText(),FOLDER_NAME);
    }
}
