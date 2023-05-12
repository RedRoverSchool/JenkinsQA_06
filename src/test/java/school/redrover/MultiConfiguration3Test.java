package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiConfiguration3Test extends BaseTest {
    private static final String NAME_OF_PROJECT = "New project";
    private static final String DESCRIPTION = "Description";
    private static final String ERROR_MESSAGE_EQUAL_NAME = "A job already exists with the name ‘New project’";
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final By NEW_ITEM_BUTTON = By.xpath("//*[@id='tasks']//span/a");
    private static final By INPUT_FIELD = By.name("name");
    private static final By DISABLE_BUTTON_CONFIG_PAGE = By.xpath("//*[@id='disable-project']/button");
    private static final List<String> SPECIAL_SYMBOLS = new ArrayList<> (Arrays.asList("!","@","#","$","%","^","&","*","[","]","?"));


    private void createBaseMultiConfigurationProject() {
        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getDriver().findElement(INPUT_FIELD).sendKeys(NAME_OF_PROJECT);
        getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']")).click();
        getDriver().findElement(By.xpath("//div[@class ='btn-decorator']")).click();
        }

    @Test
    public void testCreateMultiConfigurationProjectTest() {
        createBaseMultiConfigurationProject();

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(DASHBOARD_BUTTON).click();

        WebElement nameMultiCofigurationProject = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(nameMultiCofigurationProject.getText(),NAME_OF_PROJECT);
    }

    @Test
    public void testCreateMultiConfigurationProjectWithDescriptionTest() {
        createBaseMultiConfigurationProject();

        getDriver().findElement(By.name("description")).sendKeys(DESCRIPTION);
        getDriver().findElement(SAVE_BUTTON).click();

        WebElement nameDescription = getDriver().findElement(By.xpath("//div[@id ='description']//div"));

        Assert.assertEquals(nameDescription.getText(),DESCRIPTION);
    }

    @Test
    public void testCreateMultiConfigurationProjectWithSpaceInsteadName() {
        final String expectedResult = "Error";

        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getDriver().findElement(INPUT_FIELD).sendKeys(" ");
        getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']")).click();
        getDriver().findElement(By.xpath("//div[@class ='btn-decorator']")).click();

        WebElement errorMessage  = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);
    }

    @Test
    public void testCreateMultiConfigurationProjectWithSpecialSymbols()  {
        final String expectedResult = "is an unsafe character";

        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();

        for (String symbol:SPECIAL_SYMBOLS) {
            getDriver().findElement(INPUT_FIELD).sendKeys(symbol);

            WebElement errorMessage = getDriver().findElement(By.id("itemname-invalid"));

            Assert.assertEquals((errorMessage.getText()).substring(6, 28), expectedResult);

            getDriver().findElement(By.name("name")).clear();
        }
    }

    @Test
    public void testCreateMultiConfigurationProjectWithEqualName() {
        createBaseMultiConfigurationProject();

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(DASHBOARD_BUTTON).click();

        createBaseMultiConfigurationProject();

        WebElement errorMessage  = getDriver().findElement(By.xpath("//*[@id='main-panel']/p"));

        Assert.assertEquals(errorMessage.getText(),ERROR_MESSAGE_EQUAL_NAME);
    }

    @Test
    public void testDisableMultiConfigurationProjectFromConfigurationPage() {
        final String expectedResult = "This project is currently disabled";

        createBaseMultiConfigurationProject();

        getDriver().findElement(SAVE_BUTTON).click();

        getDriver().findElement(DISABLE_BUTTON_CONFIG_PAGE).click();

        WebElement disableMessage = getDriver().findElement(By.xpath("//*[@id='enable-project']"));

        Assert.assertEquals(disableMessage.getText().substring(0,34),expectedResult);
    }

    @Test
    public void testCheckDisableIconOnDashboard() {
        createBaseMultiConfigurationProject();

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(DISABLE_BUTTON_CONFIG_PAGE).click();
        getDriver().findElement(DASHBOARD_BUTTON).click();

        WebElement iconDisabled = getDriver().findElement(By.xpath("//*[@tooltip='Disabled']"));

        Assert.assertTrue(iconDisabled.isDisplayed());
    }

    @Test
    public void testRenameMultiConfigurationProject() {
        final String NEW_PROJECT_NAME="New project renamed";

        createBaseMultiConfigurationProject();

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[7]/span/a")).click();
        getDriver().findElement(By.xpath("//*[@checkdependson='newName']")).sendKeys(NEW_PROJECT_NAME);
        getDriver().findElement(By.xpath("//*[@formnovalidate='formNoValidate']")).click();
        getDriver().findElement(DASHBOARD_BUTTON).click();

        WebElement newNameMultiCofigurationProject = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(newNameMultiCofigurationProject.getText(),NAME_OF_PROJECT+NEW_PROJECT_NAME);
    }
}
