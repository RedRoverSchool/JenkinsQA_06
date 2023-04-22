package school.redrover;

import school.redrover.runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NataChelovechikTest extends BaseTest {

    @Test
    public void testVerifyOrthodonticsPresent() throws InterruptedException {

        getDriver().get("https://vinir.by");
        getDriver().findElement(By.xpath("//nav//a[@href='https://vinir.by/uslugi/']")).click();

        Thread.sleep(3000);

        getDriver().findElement(By.xpath("//nav//a[@href='https://vinir.by/brekety/']")).click();

        Thread.sleep(2000);

        WebElement orthodonticsElement = getDriver().findElement(By.xpath("//div[@class='col-lg-5']//a[@href='https://vinir.by/brekety/']"));

        Assert.assertEquals(orthodonticsElement.getText(), "Ортодонтия");
    }
}