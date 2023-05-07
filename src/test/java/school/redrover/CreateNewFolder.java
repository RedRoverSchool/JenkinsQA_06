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
        WebElement newItemLink = getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a"));
        newItemLink.click();

        WebElement enterItemName = getDriver().findElement(By.xpath("//input[@name='name']"));
        enterItemName.sendKeys(name);

        WebElement createFolderButton = getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]/label/span"));
        createFolderButton.click();

        WebElement clickOkButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        clickOkButton.click();

        WebElement clickSaveButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        clickSaveButton.click();

        WebElement clickDashboardButton = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']/ol/li[1]/a"));
        clickDashboardButton.click();

        WebElement checkFolderName = getDriver().findElement(By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a/span"));
        checkFolderName.getText();

        Assert.assertEquals(checkFolderName.getText(), name);
    }
}
