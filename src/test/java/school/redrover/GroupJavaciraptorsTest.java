package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupJavaciraptorsTest extends BaseTest {
    @Test
    public void simpleTest() {
       WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(),"Welcome to Jenkins!");
    }

    @Ignore
    @Test
    public void savichevTest() {
        WebElement versionElement = getDriver().findElement(By.xpath("//a[normalize-space()='Jenkins 2.387.2']"));
        Assert.assertEquals(versionElement.getText(), "Jenkins 2.387.2");

        WebElement textElement = getDriver().findElement(By.xpath("//h2[normalize-space()='Set up a distributed build']"));
        Assert.assertEquals(textElement.getText(), "Set up a distributed build");
    }

    @Ignore
    @Test
    public void addDescriptionTest() {
        WebElement addDescription = getDriver().findElement(By.xpath("//*[@id='description-link']"));
        addDescription.click();

        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textArea.sendKeys("It`s my 1st Jenkins!");

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement textDescription = getDriver().findElement(By.xpath("//*[@id='description']/div[1]"));

        Assert.assertEquals(textDescription.getText(), "It`s my 1st Jenkins!");
    }

    @Test
    public void firstBinoederTest() {
        WebElement firstSubtitleElement = getDriver().findElement(
                By.xpath("//section[@class = 'empty-state-section']/h2")
        );

        Assert.assertEquals(firstSubtitleElement.getText(), "Start building your software project");
    }

    @Test
    public void secondBinoederTest() {
        String expectedResult = "This folder is empty";
        WebElement myViewsLink = getDriver().findElement(
               By.xpath("//div[@id = 'tasks']//a[@href = '/me/my-views']")
        );
        myViewsLink.click();

        WebElement header = getDriver().findElement(
                By.xpath("//div[@class = 'empty-state-block']//h2")
        );

        String actualResult = header.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void createNewItemTest() {
        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        WebElement nameItemInput = getDriver().findElement(By.xpath("//input[@name='name']"));
        nameItemInput.sendKeys("Name of test item");

        WebElement typeItem = getDriver().findElement(By.xpath("//span[@class='label' and text()='Pipeline']"));
        typeItem.click();

        WebElement okBtn = getDriver().findElement(By.cssSelector("#ok-button"));
        okBtn.click();

        WebElement submitBtn = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submitBtn.click();

        WebElement title = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));
        Assert.assertEquals(title.getText(), "Pipeline Name of test item");

        WebElement deleteItem = getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']"));
        deleteItem.click();

        getDriver().switchTo().alert().accept();

        WebElement welcomeTitle = getDriver().findElement(By.xpath("//*[text()='Welcome to Jenkins!']"));
        Assert.assertEquals(welcomeTitle.getText(), "Welcome to Jenkins!");
    }
}
