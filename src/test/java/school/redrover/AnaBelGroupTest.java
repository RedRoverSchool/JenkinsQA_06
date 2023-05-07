package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class AnaBelGroupTest extends BaseTest {

    @Test
    public void testItem() {

        WebElement button = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        button.click();

        WebElement textBox = getDriver().findElement(By.id("name"));
        textBox.sendKeys("Item");

        WebElement lableButton = getDriver().findElement(By.xpath("//span[contains(text(),'Multi-configuration project')]"));
        lableButton.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okButton.click();

        WebElement validateButton = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate'][@name='Submit']"));
        validateButton.click();

        WebElement general = getDriver().findElement(By.xpath("//h1[@class='matrix-project-headline page-headline']"));
        Assert.assertEquals(general.getText(), "Project Item");
    }

    @Test
    public void testBuildHistory() {
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href ='/view/all/builds']"));

        assertEquals(buildHistory.getText(), "Build History");
    }

    @Test
    public void testAddDescription() {
        WebElement button = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        button.click();

        WebElement textField = getDriver().findElement(By.cssSelector(".jenkins-input"));
        textField.clear();
        textField.sendKeys("testDesctiprion1");

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement verify = getDriver().findElement(By.cssSelector("#description>div"));

        assertEquals(verify.getText(), "testDesctiprion1");
    }
    
    @Test
    public void testIndicator(){
        WebElement indicator = getDriver().findElement(By.xpath("//*[@id='visible-sec-am-button']/span"));
        Assert.assertTrue(indicator.isDisplayed());
    }
    
    @Test
    public void testWelcomeJenkins() {
        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1"));
        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }

    @Test
    public  void testRequieredField() throws InterruptedException {
        WebElement button = getDriver().findElement(By.xpath("//a[@href='newJob']"));
        button.click();

        Thread.sleep(1000);

        WebElement buttonProject = getDriver().findElement(By.cssSelector(".label"));
        buttonProject.click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));

        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
    }


    @Ignore
    @Test
    public void testSetUpAgent() {
        getDriver().findElement(By.xpath(("(//a[@class='content-block__link'])[2]"))).click();

        Assert.assertEquals(getDriver().findElement
                (By.xpath("//div[@class='jenkins-app-bar__content']/h1")).getText(),"New node");
    }

    @Test
    public void testCreateAJob() {
        WebElement createAJobButton = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a/span[1]"));
        createAJobButton.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@id='name']")).isDisplayed());
    }

}



