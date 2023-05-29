package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MyViewsPage;
import school.redrover.model.ViewPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MyViewsTest extends BaseTest {

    private static final String NEW_VIEW_NAME_RANDOM = "NEW_VIEW_NAME_RANDOM";

    private static final String NEW_VIEW_DESCRIPTION_RANDOM = "NEW_VIEW_DESCRIPTION_RANDOM";

    private static final String NAME_FOLDER = "TestPipeline";

    private static final String NEW_VIEW_NEW_DESCRIPTION_RANDOM = "NEW_VIEW_NEW_DESCRIPTION_RANDOM";



    @Test
    public void testCreateAJobInThePageMyViews() {
        new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickCreateAJob()
                .enterAnItemName(NEW_VIEW_NAME_RANDOM)
                .clickFreestyleProject()
                .clickOkButton()
                .clickSaveButton()
                .clickOnDashboardPage()
                .clickOnNewJob();

        List<WebElement> table = getDriver().findElements(By.xpath("//tr[@class =' job-status-nobuilt']/td"));
        for (WebElement td : table) {

            Assert.assertTrue(td.getText().contains(NEW_VIEW_NAME_RANDOM));
        }
    }

    @Test
    public void testAddDescriptionFromMyViewsPage() {

        MyViewsPage myViewsPage = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickOnDescription()
                .enterDescription(NEW_VIEW_DESCRIPTION_RANDOM)
                .clickSaveButtonDescription();

        Assert.assertEquals(myViewsPage.getTextFromDescription(),NEW_VIEW_DESCRIPTION_RANDOM);

    }



    @Test
    public void testEditDescription() {
        MyViewsPage myViewsPage = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickOnDescription()
                .enterDescription(NEW_VIEW_DESCRIPTION_RANDOM)
                .clickSaveButtonDescription()
                .clickOnDescription()
                .clearTextFromDescription()
                .enterNewDescription(NEW_VIEW_NEW_DESCRIPTION_RANDOM)
                .clickSaveButtonDescription();

        Assert.assertEquals(myViewsPage.getTextFromDescription(), NEW_VIEW_NEW_DESCRIPTION_RANDOM);
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

    @DataProvider(name = "description")
    public static Object [][] provideDescription() {
        return new Object[][]
                {{"Description first"},{"Description second"}};
    }
    @Test(dataProvider = "description")
    public void testAddDescription(String desc) {
        ViewPage viewPage = new ViewPage(getDriver());
        viewPage.clickAddDescription().
                inputDescText(desc).
                saveDescription();

        Assert.assertEquals(viewPage.getDescriptionText(), desc);
    }

}
