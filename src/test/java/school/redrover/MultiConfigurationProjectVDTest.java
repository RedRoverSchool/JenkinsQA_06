package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.time.Duration;

public class MultiConfigurationProjectVDTest extends BaseTest {

    private static final String PROJECT_NAME = "Tricky_Project";

    private static final By OK_BUTTON = By.xpath("//*[@name='Submit']");

    private static final By DISABLE_BUTTON = By.xpath("//*[@id='disable-project']/button[@name = 'Submit']");

    private static final By ENABLE_BUTTON = By.xpath("//*[@id='enable-project']/button[@name='Submit']");

    @Test
    public void testCreateProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='name']"))).sendKeys(PROJECT_NAME);

        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(OK_BUTTON)).click();

        WebElement expectedResult = getDriver().findElement(By.xpath("//*[@class='matrix-project-headline page-headline']"));
        Assert.assertEquals(expectedResult.getText(), "Project " + PROJECT_NAME);
    }

    @DataProvider(name = "wrong character")
    public Object[][] wrongCharacters() {
        return new Object[][]{
                {"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}
        };
    }

    @Test(dataProvider = "wrong character")
    public void testCreateProjectWithWrongName(String wrongCharacter) {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='name']"))).sendKeys(wrongCharacter);

        String errorName = getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='itemname-invalid']"))).getText();

        Assert.assertEquals(errorName, "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(getDriver().findElement(By.xpath("//button[@id='ok-button']")).isEnabled());

    }

    @Test(dependsOnMethods = {"testCreateProject"})
    public void testAddDescription() {

        getDriver().findElement(By.xpath("//*[@class ='jenkins-table__link model-link inside']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'configure')]"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='description']"))).sendKeys(PROJECT_NAME);
        getDriver().findElement(OK_BUTTON).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='description']/div[1]"))).getText(), PROJECT_NAME);
    }

    @Test
    public void testDisableAndEnableProject() {

        TestUtils.createMultiConfigurationProject(this, PROJECT_NAME, true);

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class ='jenkins-table__link model-link inside']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(DISABLE_BUTTON)).click();
        Assert.assertTrue(getWait2().until(ExpectedConditions.visibilityOfElementLocated(ENABLE_BUTTON)).isDisplayed());

        getWait2().until(ExpectedConditions.elementToBeClickable(ENABLE_BUTTON)).click();
        Assert.assertTrue(getWait2().until(ExpectedConditions.visibilityOfElementLocated(DISABLE_BUTTON)).isDisplayed());
    }

    @Test
    public void testBuildProject() {

        TestUtils.createMultiConfigurationProject(this, PROJECT_NAME, true);

        if (getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[local-name()='svg' and @title = 'Not built']"))).isDisplayed()) {

            new Actions(getDriver()).moveToElement(getWait2().until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//a[@class='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']"))))
                    .click()
                    .perform();

            new Actions(getDriver()).moveToElement(getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                            (By.xpath("//a[@href = '#' and contains(@class, 'yuimenuitemlabel')]")))))
                    .click()
                    .perform();
        } else {
            System.out.println("The project " + PROJECT_NAME + " has already built");
        }

        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + PROJECT_NAME + "/1/console']"))).click();

        Assert.assertTrue(getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@class='console-output']"))).getText().contains("Finished: SUCCESS"), "The build has failed");

    }
}


