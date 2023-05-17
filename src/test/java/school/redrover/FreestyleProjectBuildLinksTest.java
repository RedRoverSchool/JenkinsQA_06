package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjectBuildLinksTest extends BaseTest {

    private void createFreestyleProject() {
        String nameOfProject = "anyName";

        WebElement newItemLink = getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a"));
        newItemLink.click();

        WebElement enterItemName = getDriver().findElement(By.xpath("//input[@id='name']"));
        enterItemName.sendKeys(nameOfProject);

        WebElement freestyleProjectBtn = getDriver()
                .findElement(By.xpath("//*[contains(@class,'FreeStyleProject')]/label/span"));
        freestyleProjectBtn.click();

        WebElement okBtn = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okBtn.click();

        WebElement submitBtn = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        submitBtn.click();
    }

    @Test
    public void testBuildLinks() {
        createFreestyleProject();

        WebElement buildNowBtn = getDriver().findElement(By.xpath("//*[@class='task '][4]/span/a"));
        buildNowBtn.click();

        WebElement dashBoardBtn = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashBoardBtn.click();

        WebElement greenCheckmark = getDriver().findElement(By.xpath("//*[@class='svg-icon ']"));

        Assert.assertTrue(greenCheckmark.isDisplayed());

        WebElement projectNameBtn = getDriver()
                .findElement(By.xpath("//*[@class='jenkins-table__link model-link inside']"));
        projectNameBtn.click();

        WebElement permaLinks = getDriver()
                .findElement(By.xpath("//*[@class='permalink-link model-link inside tl-tr']"));
        Assert.assertTrue(permaLinks.isDisplayed());
    }
}
