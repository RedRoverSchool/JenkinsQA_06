package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProject3Test extends BaseTest {

    @Test
    public void createDefaultMultiConfigurationProjectTest(){
        final String expectedprojectName = getProjectName();

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'name']"))).sendKeys(expectedprojectName);
        getDriver().findElement(By.cssSelector("li.hudson_matrix_MatrixProject span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'Submit']"))).click();
        getDriver().findElement(By.xpath("//a[@href='/']")).click();
        WebElement project = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#projectstatus a.model-link")));

        Assert.assertEquals(project.getText(), expectedprojectName);
    }

    private String getProjectName() {
        Faker faker = new Faker();
        return faker.funnyName().name();
    }

}