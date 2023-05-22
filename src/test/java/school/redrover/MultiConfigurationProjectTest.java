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

        WebElement newNameJob = new MultiConfigurationProjectPage(getDriver())
                .getMultiProjectName();

        Assert.assertEquals(newNameJob.getText(), "Project My Multi configuration project");
    }

    @Test(dependsOnMethods = "testMultiConfigurationProject")
    public void testMultiConfigurationProjectAddDescription () {
        final String text = "text";
        MainPage mainPage = new MainPage(getDriver());
                mainPage.getProjectName()
                        .click();
        WebElement addDescriptionText = (WebElement) new MultiConfigurationProjectPage(getDriver())
                .getAddDescription(text)
                .getSaveButton()
                .getInputAdd();
        Assert.assertEquals(addDescriptionText.getText(), text);
    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectAddDescription")
    public void testMultiConfigurationProjectDisable () {

        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getProjectName()
                .click();
        WebElement disable = (WebElement) new MultiConfigurationProjectPage(getDriver())
                .getDisable().Enable();

        Assert.assertEquals(disable.getText(),"Enable");
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
