package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MyViewsTest extends BaseTest {

    @Test
    public void testCreateAJobInThePageMyViews() {
        final String newViewNameRandom = RandomStringUtils.randomAlphanumeric(5);

        boolean isJobPresent = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(newViewNameRandom)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .verifyJobIsPresent(newViewNameRandom);

        Assert.assertTrue(isJobPresent);
    }

    @Test
    public void testAddDescriptionFromMyViewsPage() {
        final String newViewDescriptionRandom = RandomStringUtils.randomAlphanumeric(7);

        String description = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickOnDescription()
                .clearTextFromDescription()
                .enterDescription(newViewDescriptionRandom)
                .clickSaveButtonDescription()
                .getTextFromDescription();

        Assert.assertEquals(description, newViewDescriptionRandom);
    }

    @Test
    public void testEditDescription() {
        final String newViewDescriptionRandom = RandomStringUtils.randomAlphanumeric(7);

        final String newViewNewDescriptionRandom = RandomStringUtils.randomAlphanumeric(7);

        String description = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickOnDescription()
                .enterDescription(newViewDescriptionRandom)
                .clickSaveButtonDescription()
                .clickOnDescription()
                .clearTextFromDescription()
                .enterNewDescription(newViewNewDescriptionRandom)
                .clickSaveButtonDescription()
                .getTextFromDescription();

        Assert.assertEquals(description, newViewNewDescriptionRandom);
    }

    @Test
    public void testCreateMyView() {
        String viewName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickCreateAJobArrow()
                .enterItemName("My project")
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickUserName()
                .clickMyViewsDropDownMenuUser()
                .clickPlusSign()
                .setNewViewName("Java")
                .selectMyView()
                .clickCreateMyViewButton()
                .getActiveViewName();

        Assert.assertEquals(viewName, "Java");
    }

    @Test
    public void testCreateViewItem() {

        TestUtils.createJob(this, "NAME_FOLDER", TestUtils.JobType.Pipeline, true);
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
    public static Object[][] provideDescription() {
        return new Object[][]
                {{"Description first"}, {"Description second"}};
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
