package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JavaJitsuGroupTest extends BaseTest {
    @Test
    public void testCheckingConfiguration() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement dropDownElement = getDriver().findElement(By.xpath("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.click(dropDownElement).perform();

        WebElement configure = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Configure']//parent::a")));
        Actions actions = new Actions(getDriver());
        actions.click(configure).perform();

        Assert.assertTrue(getDriver().findElement(By.xpath("//textarea[@name='_.description']")).isDisplayed());
    }

    @Test
    public void logoCheckingTest() {
        WebElement logoCheck = getDriver().findElement(By.xpath("//img[@id='jenkins-head-icon']"));
        Assert.assertTrue(logoCheck.isDisplayed());

    }

    @Test
    public void newItem() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        newItem.click();
        WebElement nameInput = getDriver().findElement(By.xpath("//input[@id=\"name\"]"));

        nameInput.sendKeys("JavaTest");
        WebElement freProject = getDriver().findElement(By.xpath("//span[text() =\"Freestyle project\"]"));
        freProject.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        WebElement configElement = getDriver().findElement(By.xpath("//div[@class = 'jenkins-app-bar__content']/h1"));
        Assert.assertEquals(configElement.getText(), "Configure");
    }

    @Test
    public void testManagePlugins() {
        WebElement manageJ = getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[4]/span[1]/a[1]"));
        manageJ.click();

        WebElement managePlugins = getDriver().findElement(By.xpath("//dt[contains(text(),'Manage Plugins')]"));
        managePlugins.click();

        WebElement text = getDriver().findElement(By.xpath("//h1[contains(text(),'Plugins')]"));
        Assert.assertEquals(text.getText(), "Plugins");
    }

    @Test
    public void testAddNew1Item() throws InterruptedException {

        WebElement newItemBtn = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemBtn.click();
        WebElement searchField = getDriver().findElement(By.xpath("//input[@id='name']"));
        searchField.sendKeys("Mari5");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']//label"));
        freestyleProject.click();
        WebElement okBtn = getDriver().findElement(By.id("ok-button"));
        okBtn.click();
        Thread.sleep(2000);
        WebElement myDropDown = getDriver().findElement(By.cssSelector("a[class='model-link'] span[class='hidden-xs hidden-sm']"));
        myDropDown.click();

        getDriver().findElement(By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[1]"));
        getDriver().findElement(By.xpath("(//span[contains(text(),'Configure')])[1]"));

        WebElement myViews = getDriver().findElement(By.xpath("//div[5]//span[1]//a[1]"));
        Thread.sleep(2000);
        Assert.assertEquals(myViews.getText(), "My Views");


    }
}
