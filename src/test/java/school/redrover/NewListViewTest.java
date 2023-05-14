package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewListViewTest extends BaseTest {

    String name = "MyView";

    @BeforeMethod

    private void createNewItem()  {

        WebElement task = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        task.click();
        WebElement textBox = getDriver().findElement(By.xpath("//input[@id='name']"));
        textBox.sendKeys("TestPipeline");
        WebElement pipelineButton = getDriver().findElement(By.xpath("//body/div[3]/div/div/div/div/form/div[2]/div[1]/ul/li[2]/label"));
        pipelineButton.click();
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();
        WebElement jenkinsIcon = getDriver().findElement(By.id("jenkins-name-icon"));
        jenkinsIcon.click();
           }
    @Test

    public void testCreateViewItem() throws InterruptedException {

        WebElement myViews = getDriver().findElement(By.xpath("//a[@href='/me/my-views']"));
        myViews.click();
        WebElement plusButton = getDriver().findElement(By.xpath("//a[@title='New View']"));
        plusButton.click();
        WebElement viewNameBox = getDriver().findElement(By.xpath("//input[@id='name']"));
        viewNameBox.sendKeys("MyView");
        getDriver().manage().window().maximize();
        WebElement checkBoxListView = getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']"));
        checkBoxListView.click();
        WebElement createButton = getDriver().findElement(By.id("ok"));
        createButton.click();
        WebElement submitButton = getDriver().findElement(By.name("Submit"));
        submitButton.click();

        WebElement myViewTab = getDriver().findElement(By.xpath("//a[@href='/user/admin/my-views/view/MyView/']"));
        Assert.assertEquals(myViewTab.getText(), name);
    }
}


