package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultiConfigurationProjectPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProject4Test extends BaseTest {
    @Test
    public void testDisableMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, "MyProject", false);

        WebElement enable = new MultiConfigurationProjectPage(getDriver())
                .getDisableClick()
                .getEnableSwitch();

        Assert.assertEquals(enable.getText(),"Enable");
    }

    @Test(dependsOnMethods = {"testDisableMultiConfigurationProject"})
    public void testEnableMultiConfigurationProject() {
        MultiConfigurationProjectPage multiConfigurationProjectPage = new MainPage(getDriver())
                .clickJobWebElement("MyProject");

        WebElement disableProject = new MultiConfigurationProjectPage(getDriver())
                .getEnableClick()
                .getDisableSwitch();

        Assert.assertTrue(disableProject.isDisplayed());
    }

    @Test(dependsOnMethods = {"testEnableMultiConfigurationProject"})
    public void testDisableMultiConfigurationProjectOnConfigurePage() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.getMultiConfigPage();

        WebElement disableButton = new MultiConfigurationProjectPage(getDriver())
                .getConfigPage()
                .switchCheckboxDisable()
                .getTextDisable();

        Assert.assertEquals(disableButton.getText(),"Disabled");
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
    }

    @Test(dependsOnMethods = {"testDisableMultiConfigurationProjectOnConfigurePage"})
    public void testEnableMultiConfigurationProjectOnConfigurePage() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.getMultiConfigPage();

        WebElement enableButton = new MultiConfigurationProjectPage(getDriver())
                .getConfigPage()
                .switchCheckboxEnabled()
                .getTextEnabled();

        Assert.assertEquals(enableButton.getText(), "Enabled");
    }

    @DataProvider(name = "unsafeCharacter")
    public static Object[][] provideUnsafeCharacters() {
        return new Object[][]{{'!'}, {'@'}, {'#'}, {'$'}, {'%'}, {'^'}, {'&'},
                {'*'}, {'['}, {']'}, {'\\'}, {'|'}, {';'}, {':'},
                {'<'}, {'>'}, {'/'}, {'?'}};
    }

    @Test(dataProvider = "unsafeCharacter")
    public void testVerifyAnErrorIfCreatingMultiConfigurationProjectWithUnsafeCharacterInName(char unsafeSymbol) {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(unsafeSymbol + "MyProject");

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));

        Assert.assertEquals(errorMessage.getText(), "» ‘" + unsafeSymbol + "’" + " is an unsafe character");
    }
}
