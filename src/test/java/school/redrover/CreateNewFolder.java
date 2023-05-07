package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.testng.Assert.assertEquals;

public class CreateNewFolder extends BaseTest{

    @Test
    public void createNewFolder(){
        String name = "Grouping";

        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(name);

        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]/label/span")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']/ol/li[1]/a")).click();

        WebElement checkFolderName = getDriver().findElement(By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a/span"));

        Assert.assertEquals(checkFolderName.getText(), name);
    }
}
