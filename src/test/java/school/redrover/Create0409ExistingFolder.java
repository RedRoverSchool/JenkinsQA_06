package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Create0409ExistingFolder extends BaseTest {
    @Test
    public void createExistingFolderTest() {
        String name = "ProjectRedRover";

        WebElement item = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        item.click();
        WebElement folder = getDriver().findElement(By.xpath("//span[@class='label'][text()='Folder']"));
        folder.click();
        WebElement input = getDriver().findElement(By.xpath("//*[id='name']"));
        input.sendKeys(name);
        WebElement okButton = getDriver().findElement(By.xpath("//*[id='ok-button']"));
        okButton.click();
        WebElement SaveButton = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"));
        SaveButton.click();

        WebElement item = getDriver().findElement(By.xpath("//a[@href='/job/ProjectRedRover/newJob']"));
        item.click();
        WebElement folder = getDriver().findElement(By.xpath("//span[@class='label'][text()='Folder']"));
        folder.click();
        WebElement input = getDriver().findElement(By.xpath("//*[id='name']"));
        input.sendKeys(name);
        WebElement okButton = getDriver().findElement(By.xpath("//*[id='ok-button']"));
        okButton.click();
        WebElement saveButton = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div"));
        saveButton.click();

        WebElement folderButton = getDriver().findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li[3]/a/button"));
        folderButton.click();
        WebElement actualresult = getDriver().findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li[3]/a" + name + "']/td[3]/a/span"));

        Assert.assertEquals(actualresult.getText(), name);

    }
}


