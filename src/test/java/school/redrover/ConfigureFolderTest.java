package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ConfigureFolderTest extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME_FIELD = By.xpath("//input[@id= 'name'] ");
    private static final By TYPE_FOLDER = By.xpath("//span[@class='label'][text()='Folder']");
    private static final By OK_BUTTON = By.xpath("//*[@id='ok-button']");
    private static final By DISPLAY_NAME = By.xpath("//input[@class= 'jenkins-input validated  ']");
    private static final By FOLDER_HEADER = By.tagName("h1");
    private static final By DESCRIPTION= By.xpath("//textarea[@name= '_.description']");
    private static final By HEALTH_METRICS= By.xpath("//*[@class='jenkins-button advanced-button advancedButton']");
    private static final By ADD_METRICS = By.xpath("//*[@id='yui-gen1-button']");
    private static final By CHILD_ITEM_TYPE = By.xpath("//a[@class='yuimenuitemlabel']");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By CONFIGURE = By.linkText("Configure");
    private static final By RECURSIVE_TYPE = By.xpath("//input[@name='_.recursive']");

    private void createFolder(String name){

        getDriver().findElement(NEW_ITEM).click();

        getWait10().until(ExpectedConditions.
                presenceOfElementLocated(NAME_FIELD)).sendKeys(name);

        getDriver().findElement(TYPE_FOLDER).click();

        getDriver().findElement(OK_BUTTON).click();

        getDriver().findElement(DISPLAY_NAME).sendKeys(name);

        getDriver().findElement(SAVE_BUTTON).click();

        getWait2().until(ExpectedConditions.textToBe(FOLDER_HEADER,name));
    }

    @Test
    public void testConfigureFolder(){
        final String INITIAL_NAME = "NewFolder";
        final String NEW_FOLDER_NAME = "TestFolder0404";
        final String DESCRIPTION_VALUE = "Test Description of the folder";

        createFolder(INITIAL_NAME);

        getDriver().findElement(CONFIGURE).click();

        getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(DISPLAY_NAME));
        getDriver().findElement(DISPLAY_NAME).clear();
        getDriver().findElement(DISPLAY_NAME).sendKeys(NEW_FOLDER_NAME);

        getDriver().findElement(DESCRIPTION).sendKeys(DESCRIPTION_VALUE);

        getDriver().findElement(HEALTH_METRICS).click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated(ADD_METRICS)).click();

        getDriver().findElement(CHILD_ITEM_TYPE).click();

        getDriver().findElement(SAVE_BUTTON).click();

        getWait2().until(ExpectedConditions.textToBe(FOLDER_HEADER,NEW_FOLDER_NAME));

        getDriver().findElement(CONFIGURE).click();

        Assert.assertEquals(getDriver().findElement(DISPLAY_NAME).getAttribute("value"), NEW_FOLDER_NAME);
        Assert.assertEquals(getDriver().findElement(DESCRIPTION).getText(), DESCRIPTION_VALUE);

        getDriver().findElement(HEALTH_METRICS).click();

        Assert.assertTrue(getWait2().until(ExpectedConditions.presenceOfElementLocated(RECURSIVE_TYPE)).isDisplayed());
    }

    public static class CreateMulticonfigurationProjectInFolderTest extends BaseTest {

        @Test
        public void CreateMulticonfigurationProjectInFolderTest(){

            WebElement newItem = getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[1]/span[1]/a[1]/span[1]/*[1]"));
            newItem.click();

            WebElement createName = getDriver().findElement(By.xpath("//input[@id='name']"));
            createName.sendKeys("TC 00.04 New item Create Folder");

            WebElement buttonFolder = getDriver().findElement(By.xpath("//div[contains(text(),'Creates a container that stores nested items in it')]"));
            buttonFolder.click();

            WebElement buttonOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
            buttonOk.click();

            WebElement buttonSave = getDriver().findElement(By.xpath("//button[contains(text(),'Save')]"));
            buttonSave.click();

            WebElement buttonCreateAjob = getDriver().findElement(By.xpath("//span[contains(text(),'Create a job')]"));
            buttonCreateAjob.click();

            WebElement inputName = getDriver().findElement(By.xpath("//input[@id='name']"));
            inputName.sendKeys("Mine Project");

            WebElement selectConfiguration = getDriver().findElement(By.xpath("//div[contains(text(),'Suitable for projects that need a large number of ')]"));
            selectConfiguration.click();

            WebElement buttonOkInto = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
            buttonOkInto.click();

            WebElement buttonSaveInto = getDriver().findElement(By.xpath("//button[contains(text(),'Save')]"));
            buttonSaveInto.click();

            WebElement text = getDriver().findElement(By.xpath("//h1[contains(text(),'Project Mine Project')]"));
            Assert.assertEquals(text.getText(), "Project Mine Project");
        }
    }
}