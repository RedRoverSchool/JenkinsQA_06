package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.ViewPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MyViewsTest extends BaseTest {

    @Test
    public void testCreateAJobInThePageMyViews() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("First Project");
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();

        List<WebElement> table = getDriver().findElements(By.xpath("//tr[@class =' job-status-nobuilt']/td"));
        for (WebElement td : table) {

            Assert.assertTrue(td.getText().contains("First Project"));
        }
    }

    @Ignore
    @Test
    public void testEditDescription() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.id("description-link")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='description']"))).sendKeys("Test");
        getDriver()
                .findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();
        getDriver().findElement(By.id("description-link")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='description']"))).clear();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Test2");
        getDriver()
                .findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        Assert.assertEquals(getDriver().
                findElement(By.xpath("//div[@id='description']/div[1]")).getText(), "Test2");
    }

    @Test
    public void testCreateMyView() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.xpath("//span[@class='trailing-icon']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//input[@id='name']"))))
                .sendKeys("My project");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span")).click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        WebElement buttonSave = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        buttonSave.click();

        getDriver().findElement(By.xpath("//div[3]/a[1]/span")).click();
        getDriver().findElement(By.xpath("//a[@href='/user/admin/my-views']")).click();
        getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']/div/div[1]/div[2]/a")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Java");
        getDriver().findElement(By.xpath("//label[@for='hudson.model.MyView']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok']")).click();

        WebElement myViewsPage = getDriver().findElement(By.xpath("//div[@id=\"breadcrumbBar\"]//li[5]"));
        Assert.assertEquals(myViewsPage.getText(), "My Views");

        WebElement myViewName = getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']/div/div[1]/div[2]"));
        Assert.assertEquals(myViewName.getText(), "Java");
    }

    private static final String NAME_FOLDER = "TestPipeline";

    @Test
    public void testCreateViewItem() {

        TestUtils.createPipeline(this, NAME_FOLDER, true);
        WebElement myViews = getDriver().findElement(By.xpath("//a[@href='/me/my-views']"));
        myViews.click();
        WebElement plusButton = getDriver().findElement(By.xpath("//a[@title='New View']"));
        plusButton.click();
        WebElement viewNameBox = getDriver().findElement(By.xpath("//input[@id='name']"));
        viewNameBox.sendKeys("MyView");
        WebElement checkBoxListView = getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']"));
        new Actions(getDriver())
                .scrollToElement(checkBoxListView)
                .perform();
        checkBoxListView.click();
        WebElement createButton = getDriver().findElement(By.id("ok"));
        createButton.click();
        WebElement submitButton = getDriver().findElement(By.name("Submit"));
        submitButton.click();
        WebElement myViewTab = getDriver().findElement(By.xpath("//a[@href='/user/admin/my-views/view/MyView/']"));
        Assert.assertEquals(myViewTab.getText(), "MyView");
    }

    private static final String DESCRIPTION_EDIT = "//textarea[@name='description']";
    private static final String DESCRIPTION_FIELD = "//div[@id='description']//div[not(a[@id='description-link'])]";

    private void createNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("Item1");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
    }

    private void createNewView() {
        getDriver().findElement(By.xpath("//a[@class='addTab']")).click();
        getDriver().findElement(By.id("name")).sendKeys("View1");
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
    }

    @Test
    public void editDescriptionTest() {
        createNewItem();
        createNewView();

        getDriver().findElement(By.linkText("View1")).click();
        getDriver().findElement(By.linkText("Edit View")).click();
        getDriver().findElement(By.xpath(DESCRIPTION_EDIT)).clear();
        getDriver().findElement(By.xpath(DESCRIPTION_EDIT)).sendKeys("New description");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(DESCRIPTION_FIELD)).getText(), "New description");

        getDriver().findElement(By.linkText("Edit View")).click();
        getDriver().findElement(By.xpath(DESCRIPTION_EDIT)).clear();
        getDriver().findElement(By.xpath(DESCRIPTION_EDIT)).sendKeys("Old description");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(DESCRIPTION_FIELD)).getText(), "Old description");
    }
    @DataProvider(name = "description")
    public Object [][] provideDescription() {
        return new Object[][]
                {{"Description first"},{"Description second"}};
    }
    @Test(dataProvider = "description")
    public void testAddDescription(String desc) {
        ViewPage viewPage = new ViewPage(getDriver());
        viewPage.clickAddDescription().
                inputDescText(desc).
                saveDescription();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[normalize-space()='"+desc+"']")).isDisplayed());
    }
}
