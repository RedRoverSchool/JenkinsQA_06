package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderDeleteTest extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");

    private static final By FOLDER = By.xpath("//*[@id='j-add-item-type-nested-projects']//div[@class='desc']");

    private static final By ENTER_ITEM_NAME = By.xpath("//input[@id='name']");

    private static final By OK_BUTTON = By.xpath("//button[@id='ok-button']");

    private static final By SUBMIT_BUTTON = By.xpath("//button[@name='Submit']");

    private static final By BREADCRUMBS = By.xpath("//*[@id='breadcrumbs']//a");

    private static final By DELETE_FOLDER = By.xpath("//span[@class='task-link-text']");

    private static final By SUBMIT_DELETE_BUTTON = By.xpath("//button[@name='Submit']");

    private static final By Title = By.xpath("//h1[contains(text(),'Добро пожаловать в Jenkins!')]");
    private void newFolder(String name) {
        getWait2().until(ExpectedConditions.elementToBeClickable(NEW_ITEM)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(ENTER_ITEM_NAME)).sendKeys(name);
        getWait2().until(ExpectedConditions.elementToBeClickable(FOLDER)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(OK_BUTTON)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(BREADCRUMBS)).click();
    }

    @Test
    public void testNewFolder() {
        String name = "FolderTest";
        newFolder(name);

        WebElement title = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a//span[1]")));

        Assert.assertEquals(title.getText(), name);
    }
}