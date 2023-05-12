package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipelineTest extends BaseTest {

    private final String RANDOM_STRING = RandomStringUtils.randomAlphabetic(5);

    private void createPipeLineProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(RANDOM_STRING);
        getDriver().findElement(By.xpath("//span[contains(text(), 'Pipeline')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testDeletePipelineByTheLeftSidebar() {
        createPipeLineProject();
        String projectName = getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
        getDriver().findElement(By.xpath("//li[@href = '/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/']")).click();
        getDriver().findElement(By.xpath("//li[@href = '/view/all/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/job/"+projectName+"/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]")).click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions
                .invisibilityOfElementWithText(By.xpath(
                        "//*[contains(text(),'"+projectName+"')]"), projectName)));
        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                        "//*[contains(text(),'"+projectName+"')]"))));
    }

        @Test
        public void testDeletePipelineByDropDown() {
                createPipeLineProject();
                String projectName = getDriver()
                        .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                        .getText();
                getDriver().findElement(By.name("Submit")).click();
                getDriver().findElement(By.id("jenkins-name-icon")).click();
                getDriver().findElement(By.xpath("//span[contains(text(),'" +projectName+ "')]")).click();
                getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]")).click();
                getDriver().switchTo().alert().accept();

                Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                    "//*[contains(text(),'"+projectName+"')]"))));
        }
}
