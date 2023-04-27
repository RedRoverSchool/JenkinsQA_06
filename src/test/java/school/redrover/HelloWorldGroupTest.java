package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class HelloWorldGroupTest extends BaseTest{
    @Test
    public void testJenkinsVersion() {
        WebElement version = getDriver().findElement(By.xpath("//a[@rel='noopener noreferrer']"));
        Assert.assertEquals(version.getText(),"Jenkins 2.387.2");
    }

    @Test
    public void testCreateFilder() {
        WebElement item = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]//span/a"));
        item.click();

        WebElement itemName = getDriver().findElement(By.xpath("//input[@name='name']"));
        itemName.sendKeys("Name");

        WebElement folder =getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-nested-projects\"]//div[@class='desc']"));
        folder.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]//button[1]"));
        saveButton.click();

        WebElement dashboard = getDriver().findElement(By.xpath("//*[@id=\"breadcrumbs\"]//a"));
        dashboard.click();

        WebElement nameFolder = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(nameFolder.getText(),"Name");
    }

    @Ignore
    @Test
    public void testUserName(){
        WebElement peopleElement = getDriver().findElement(By.xpath("//span[contains(text(), 'People')]/ancestor::a"));
        peopleElement.click();

        WebElement userIDElement = getDriver().findElement(By.xpath("//tr[@id='person-admin']/td/a"));
        userIDElement.click();

        WebElement userName = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(userName.getText(), "admin");
    }
    @Test
    public void testDescriptionEdit(){
        WebElement descr = getDriver().findElement(By.xpath("//*[@id='description-link']"));
        descr.click();

        WebElement descrArea = getDriver().findElement(By.xpath("//div[@id='description']//textarea"));
        descrArea.sendKeys("hello");

        WebElement saveBtn = getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveBtn.click();

        WebElement descrText = getDriver().findElement(By.xpath("//*[@id='description']/div"));
        Assert.assertEquals(descrText.getText(), "helloStart building a software project.");
    }
}
