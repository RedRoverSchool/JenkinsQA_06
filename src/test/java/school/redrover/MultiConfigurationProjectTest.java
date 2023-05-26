package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
                .getDisableClick().getEnableSwitch();

        Assert.assertEquals(disable.getText(),"Enable");
    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectDisable")
    public void testMultiConfigurationProjectEnable () {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement enable = (WebElement) new MultiConfigurationProjectPage (getDriver())
                .getEnableClick().getDisableElem();

        Assert.assertEquals(enable.getText(),"Disable Project");
    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectEnable")
    public void testMultiConfigurationProjectConfigureDisabled() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement configPage = new MultiConfigurationProjectPage(getDriver())
                .getConfigPage()
                .switchCheckboxDisable()
                .getTextDisable();

        Assert.assertEquals(configPage.getText(),"Disabled");
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();

    }

    @Test (dependsOnMethods = "testMultiConfigurationProjectConfigureDisabled")
    public void testMultiConfigurationProjectConfigureEnable() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement configPage = new MultiConfigurationProjectPage(getDriver())
                .getConfigPage()
                .switchCheckboxEnabled()
                .getTextEnabled();

        Assert.assertEquals(configPage.getText(),"Enabled");
    }
}
