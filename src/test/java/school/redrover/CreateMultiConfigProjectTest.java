package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigProjectTest extends BaseTest {
    @Test
    public void testJMultiConfigurationProject(){
        final String projectMultiConfigName = "New item";

        WebElement buttonNewItem = getDriver().findElement(By.xpath("//a [@href = '/view/all/newJob']"));
        buttonNewItem.click();

        WebElement InputArea = getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div [@class = 'add-item-name']// input[@class= 'jenkins-input']")
        ));
        InputArea.sendKeys(projectMultiConfigName);

        WebElement buttonMultiConfigProject = getDriver().findElement(
                By.xpath("//label//span[text() ='Multi-configuration project' ]"));
        buttonMultiConfigProject.click();

        WebElement buttonEnterItemOK =getDriver().findElement(
                By.xpath("//button [@class = 'jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width']")
        );
        buttonEnterItemOK.click();

        WebElement buttonGeneralSave =getDriver().findElement(
                By.xpath("//button [@class = 'jenkins-button jenkins-button--primary ']")
        );
        buttonGeneralSave.click();

        WebElement buttonJenkinsLogo = getDriver().findElement(
                By.xpath("//a [@id = 'jenkins-home-link']")
        );
        buttonJenkinsLogo.click();

        WebElement NameofMultiConfigProject = getDriver().findElement(
                By.xpath("//a [@ class = 'jenkins-table__link model-link inside']")
        );
        Assert.assertEquals(NameofMultiConfigProject.getText(),projectMultiConfigName);
    }
}
