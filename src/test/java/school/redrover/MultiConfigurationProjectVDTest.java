package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectVDTest extends BaseTest {

    Faker faker = new Faker();
    private static final String PROJECT_NAME = "Tricky_Project";

    private static final String NEW_NAME = "Batman";

    private static final By OK_BUTTON = By.xpath("//button[@name='Submit']");

    private static final By DISABLE_BUTTON = By.xpath("//*[@id='disable-project']/button[@name = 'Submit']");

    private static final By ENABLE_BUTTON = By.xpath("//*[@id='enable-project']/button[@name='Submit']");

    private static final By INPUT_NEW_ITEM_FIELD = By.xpath("//input[@name='newName']");

    @Test
    public void testCreateProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='name']"))).sendKeys(PROJECT_NAME);

        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(OK_BUTTON)).click();

        WebElement expectedResult = getDriver().findElement(By.xpath("//h1[@class='matrix-project-headline page-headline']"));
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

        getDriver().findElement(By.xpath("//*[@id='jenkins-name-icon']")).click();
    }

    @Test(dependsOnMethods = {"testCreateProject"})
    public void testAddDescription() {

        getDriver().findElement(By.xpath("//*[@class ='jenkins-table__link model-link inside']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'configure')]"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(PROJECT_NAME);
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
    public void testRenameProject() {

        TestUtils.createMultiConfigurationProject(this, PROJECT_NAME, true);

        String link = getDriver().findElement(By.xpath("//*[@id='job_" + PROJECT_NAME + "']/td[3]/a")).getAttribute("href");
        getDriver().get(link);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/job/" + PROJECT_NAME + "/confirm-rename']"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(INPUT_NEW_ITEM_FIELD)).clear();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(INPUT_NEW_ITEM_FIELD)).sendKeys(NEW_NAME);

        getDriver().findElement(OK_BUTTON).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h1[@class='matrix-project-headline page-headline']"))).getText(), "Project " + NEW_NAME);
    }
}


