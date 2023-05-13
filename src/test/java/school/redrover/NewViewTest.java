package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;


public class NewViewTest extends BaseTest {
    private static final String NEW_VIEW_NAME_RANDOM = RandomStringUtils.randomAlphanumeric(5);
    private static final By CREATED_LIST_VIEW = By.xpath("//a[@href='/view/" + NEW_VIEW_NAME_RANDOM + "/']");
    private static final String RANDOM_LIST_VIEW_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");

    private void createNewProjectFromMyViewsPage() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hudson_model_FreeStyleProject"))).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
    }

    private List<String> getListFromWebElements(List<WebElement> elements) {
        List<String> list = new ArrayList<>();
        for (WebElement element : elements) {
            list.add(element.getText());
        }

        return list;
    }

    @Test
    public void testCreateNewView() {
        createNewProjectFromMyViewsPage();
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@name='Submit']"))).click();

        Assert.assertTrue(getDriver().findElement(CREATED_LIST_VIEW).isDisplayed());
    }

    @Test
    public void testCreateNewViewSecond() {
        createNewProjectFromMyViewsPage();

        getDriver().findElement(By.cssSelector("a.addTab")).click();
        getDriver().findElement(By.cssSelector("input#name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.cssSelector("input#hudson\\.model\\.MyView + label")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("div.tab.active")).getText(), NEW_VIEW_NAME_RANDOM);
    }

    @Test
    public void testRenameView() {
        createNewProjectFromMyViewsPage();
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']")));
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
        getDriver().findElement(CREATED_LIST_VIEW).click();
        getDriver().findElement(By.linkText("Edit View")).click();
        getDriver()
                .findElement(By.xpath("//div[@class='setting-main']/input[@name='name']")).clear();
        getDriver()
                .findElement(By.xpath("//div[@class='setting-main']/input[@name='name']")).sendKeys("RenameView");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='/view/RenameView/']")).getText(), "RenameView");
    }

    @Test
    public void testDeleteView() {
        this.createNewProjectFromMyViewsPage();
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(CREATED_LIST_VIEW).click();
        getDriver().findElement(By.linkText("Delete View")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        List<String> listViews = getListFromWebElements(getDriver().findElements(
                By.xpath("//div[@class='tabBar']/div")));

        Assert.assertFalse(listViews.contains(RANDOM_LIST_VIEW_NAME));
    }

}