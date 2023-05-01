package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class GroupOlesyaTest extends BaseTest {
    private WebDriverWait wait;

    protected WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait;
    }

    @Test
    public void testInputHelpMsg() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();

        String expectedResultMsg = getDriver()
                .findElement(By.xpath("//div[@class = 'input-help']"))
                .getText();

        Assert.assertEquals(expectedResultMsg, "» Required field");
    }

    @Test
    public void createProjectTest() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.xpath("//span[text() ='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[text()='Project Freestyle']")).getText(), "Project Freestyle");
    }

    @Test
    public void adminPageTest() {
        List<String> expectedMenus = Arrays.asList(
                "People", "Status", "Builds", "Configure", "My Views", "Credentials");
        getDriver().findElement(By.xpath("//a[@class='model-link']")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins")));
        List<WebElement> listMenu = getDriver().findElements(By.className("task-link"));

        for (int i = 0; i < listMenu.size(); i++) {
            Assert.assertEquals(listMenu.get(i).getText(), expectedMenus.get(i));
        }
    }


    @Test
    public void descriptionAreaTest() throws InterruptedException {
        String descriptionXpath = "//a[@id='description-link']";
        String textAreaXPath = "//textarea[@name='description']";
        String saveButtonXPath = "//button[@name='Submit']";
        String descriptionHeader = "//*[@id=\"description\"]/div[1]";

        WebElement addDescription = getDriver().findElement(By.xpath(descriptionXpath));
        addDescription.click();
        WebElement textArea = getDriver().findElement(By.xpath(textAreaXPath));
        getWait().until(ExpectedConditions.visibilityOf(textArea));
        textArea.clear();
        textArea.sendKeys("Testing the description");
        WebElement saveButton = getDriver().findElement(By.xpath(saveButtonXPath));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath(descriptionHeader))
                .getText(), "Testing the description");

        addDescription = getDriver().findElement(By.xpath(descriptionXpath));
        addDescription.click();
        textArea = getDriver().findElement(By.xpath(textAreaXPath));
        textArea.clear();
        saveButton = getDriver().findElement(By.xpath(saveButtonXPath));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath(descriptionHeader))
                .getText(), "");

    }
}
