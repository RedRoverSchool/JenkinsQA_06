package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject26Test extends BaseTest {
    private static final String NAME_FREESTYLE_PROJECT = "freestyle";
    private static final String DESCRIPTION_PROJECT = "This is a description";

    @Test
    public void testCreate() {

        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys(NAME_FREESTYLE_PROJECT);

        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();

        WebElement freestyleElement = getDriver().findElement(By.xpath("//*[@id='job_" + NAME_FREESTYLE_PROJECT + "']/td[3]/a/span"));
        Assert.assertEquals(freestyleElement.getText(), NAME_FREESTYLE_PROJECT);
    }

    @Test(dependsOnMethods={"testCreate"})
    public void testAddDescription() {
        getDriver().findElement(By.xpath("//*[@id='job_" + NAME_FREESTYLE_PROJECT + "']/td[3]/a/span")).click();

        WebElement h1Element = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));
        Assert.assertEquals(h1Element.getText(), "Project " + NAME_FREESTYLE_PROJECT);

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).sendKeys(DESCRIPTION_PROJECT);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebElement descriptionElement = getDriver().findElement(By.xpath("//*[@id='description']/div[1]"));
        Assert.assertEquals(descriptionElement.getText(), DESCRIPTION_PROJECT);
    }

}
