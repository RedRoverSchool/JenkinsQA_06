package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TestMainPage extends BaseTest {
    @Ignore
    @Test
    public void testMainPage() {

        WebElement startElement = getDriver().findElement(By.xpath("//div/section[1]/h2[@class = 'h4']"));
        Assert.assertEquals(startElement.getText(), "Start building your software project");

        WebElement setElement = getDriver().findElement(By.xpath("//div/section[2]/h2[@class = 'h4']"));
        Assert.assertEquals(setElement.getText(), "Set up a distributed build");
    }

    @Test
    public void testInputNewDescription() {
        WebElement fieldDescription = getDriver().findElement(By.xpath("//*[@id=\"description-link\"]"));
        fieldDescription.click();
        WebElement textarea = new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea")));
        textarea.clear();
        textarea.sendKeys("RRSDevgeni Jenkins_06");
        WebElement buttonSave = getDriver().findElement(By.xpath("//*[@id='description']//button"));
        buttonSave.click();
        WebElement newDescription = new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#description > div:nth-child(1)")));
        Assert.assertEquals(newDescription.getText(), "RRSDevgeni Jenkins_06");
    }

    @Test
    public void testCreateJob() throws InterruptedException {

        getDriver().findElement(By.xpath("(//section[@class='empty-state-section'] )[1]//li")).click();
        Thread.sleep(1000);
        Assert.assertEquals(getDriver().findElement(By.xpath("//label[@class='h3']")).getText(), "Enter an item name");

        List<WebElement> getLabelList = getDriver().findElements(By.xpath("//span[@class='label']"));
        List<String> expectedLabelList = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project",
                "Folder", "Multibranch Pipeline", "Organization Folder");
        for (int i = 0; i < getLabelList.size(); i++) {
            Assert.assertEquals(getLabelList.get(i).getText(), expectedLabelList.get(i));
        }
    }

    @Test
    public void testAdminList() throws InterruptedException {

        getDriver().findElement(By.className("model-link")).click();
        Thread.sleep(3000);

        List<WebElement> getList = getDriver().findElements(By.className("task-link-text"));
        List<String> expectedList = Arrays.asList("People","Status","Builds","Configure","My Views","Credentials");
        for (int i = 0; i < getList.size(); i++) {
            Assert.assertEquals(getList.get(i).getText(),expectedList.get(i));
        }

    }

    @Test
    public void testLogOutIconTextVerification() {
        String logOutIconText = getDriver().findElement(By.xpath("//a[@href='/logout']/span")).getText();
        Assert.assertEquals(logOutIconText, "log out");
    }
    @Test
    public void testVerifyLogOutBtnColorChange() {
        MainPage mainPage = new MainPage(getDriver());
        String bfrColor = mainPage.getLogOutBtnColor();
        String afrColor = mainPage.mouseOverLogOutBtn().
                          getLogOutBtnColor();

        Assert.assertNotEquals(afrColor,bfrColor);
    }
}
