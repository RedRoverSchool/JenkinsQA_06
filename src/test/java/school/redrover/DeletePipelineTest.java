package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipelineTest extends BaseTest {

    private final String RANDOM_STRING = RandomStringUtils.randomAlphabetic(5);

    private void createPipeLineProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(RANDOM_STRING);
        getDriver().findElement(By.xpath("//span[contains(text(), 'Multi-configuration project')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testDeletePipelineByTheLeftSidebar() {
        createPipeLineProject();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Multi-configuration project')]"))
                .click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getDriver()
                .findElement(By.xpath("//section[@class='empty-state-section']"))
                .isDisplayed());
    }
}
