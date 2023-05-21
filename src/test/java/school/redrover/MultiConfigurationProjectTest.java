package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultiConfigurationProjectPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectTest extends BaseTest {
    @Test
    public void testMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this,"My Multi configuration project",false);

        MultiConfigurationProjectPage multiConfigurationProjectPage = new MultiConfigurationProjectPage(getDriver());

        Assert.assertEquals(multiConfigurationProjectPage.getProjectName().getText(), "Project My Multi configuration project");
    }

    @Test(dependsOnMethods = "testMultiConfigurationProject")
    public void testMultiConfigurationProjectAddDescription () {
        final String text = "text";
        MainPage mainPage = new MainPage(getDriver());
                mainPage.getProjectName()
                        .click();
        MultiConfigurationProjectPage multiConfigurationProjectPage = new MultiConfigurationProjectPage(getDriver());
        multiConfigurationProjectPage.getAddDescription();

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        saveButton.click();

        WebElement inputAdd = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(inputAdd.getText(), text);
    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectAddDescription")
    public void testMultiConfigurationProjectDisable () {
        getDriver().findElement(By.xpath("//span[text() = 'My Multi configuration project']")).click();

        getDriver().findElement(By.xpath("//button[text () = 'Disable Project']")).click();

        Assert.assertEquals((getDriver().findElement(By.xpath("//button[text () = 'Enable']"))).getText(),"Enable");
    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectDisable")
    public void testMultiConfigurationProjectEnable () {
        getWait10().until(ExpectedConditions.elementToBeClickable
                (getDriver().findElement(By.xpath("//span[text() = 'My Multi configuration project']")))).click();

        getDriver().findElement(By.xpath("//*[@id='enable-project']/button")).click();

        WebElement button = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//button[text () = 'Disable Project']"))));

        Assert.assertEquals(button.getText(),"Disable Project");
    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectEnable" )
    public void testMultiConfigurationProjectConfigureDisabled() {
        getDriver().findElement(By.xpath("//span[text() = 'My Multi configuration project']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("Configure")))).click();

        getDriver().findElement(By.xpath("//*[@id='toggle-switch-enable-disable-project']")).click();
        WebElement buttonEnabled = getWait5().until(ExpectedConditions.elementToBeClickable
                (getDriver().findElement(By.xpath("//span[text() = 'Enabled']"))));

        Assert.assertEquals(buttonEnabled.getText(),"Enabled");
    }






}
