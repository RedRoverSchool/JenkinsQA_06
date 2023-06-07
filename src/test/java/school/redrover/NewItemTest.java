package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewItemTest extends BaseTest {

    @Test
    public void testCreateNewItemWithNullName() {

        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectMultiConfigurationProject()
                .getItemNameRequiredErrorText();

        Assert.assertTrue(errorMessage.contains("» This field cannot be empty, please enter a valid name"));
    }

    @Test
    public void testNewItemHeader() {
        String titleNewItem = new MainPage(getDriver())
                .clickNewItem()
                .getTitle();

        Assert.assertEquals(titleNewItem, "Enter an item name");
    }

    @Test
    public void testVerifyNewItemsList() {
        List<String> listOfNewItemsExpect = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        List<String> listOfNewItems = new MainPage(getDriver())
                .clickNewItem()
                .getListOfNewItems();

        for (int i = 0; i < listOfNewItemsExpect.size(); i++) {
            Assert.assertEquals(listOfNewItems.get(i), listOfNewItemsExpect.get(i));
        }
    }

    @Test
    public void testVerifyButtonIsDisabled() {
        WebElement button = new MainPage(getDriver())
                .clickNewItem()
                .getOkButton();

        Assert.assertFalse(button.isEnabled());
    }

    @Test
    public void testErrorWhenCreateNewItemWithSpecialCharacterName() {
        String expectedErrorMessage = "» ‘@’ is an unsafe character";

        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("@")
                .getItemInvalidMessage();

        Assert.assertEquals(errorMessage, expectedErrorMessage);
    }

    @Test
    public void testErrorRequiredCreateFreestyleProjectWithEmptyName() {
        String actualErrorMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectFreestyleProject()
                .getItemNameRequiredMessage();

        Assert.assertEquals(actualErrorMessage, "» This field cannot be empty, please enter a valid name");
    }

    public void createProject(String nameOfProject, String typeOfProject){
        getDriver().findElement(By.className("task-icon-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name"))).sendKeys(nameOfProject);

        List <WebElement> listItemOptions = getDriver().findElements(By.id("j-add-item-type-standalone-projects"));
        for(WebElement element:listItemOptions){
            if (element.getText().contains(typeOfProject)){
                element.click();
            }
        }
    }

    @Test
    public void testCreatePipelineProject(){
        String expectedResult = "New pipeline project";
        String typeOfProject = "Pipeline";
        createProject(expectedResult, typeOfProject);
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//*[text()='Dashboard']")).click();

        List <WebElement> listOfCreateProjects = getDriver().findElements(By.xpath("//table[@id='projectstatus']//a[@class='jenkins-table__link model-link inside']"));
        List <String> nameOfCreateProjects = new ArrayList<>();
        for(WebElement element : listOfCreateProjects){
            nameOfCreateProjects.add(element.getText());
        }

        Assert.assertEquals(nameOfCreateProjects.get(0), expectedResult);
        Assert.assertEquals(nameOfCreateProjects.size(), 1);
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]
                {{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreatePipelineProjectWithInvalidName2(String wrongCharacter){
        createProject(wrongCharacter, "Pipeline");

        String validationMessage = getDriver().findElement(By.id("itemname-invalid")).getText();
        Assert.assertEquals(validationMessage, "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());

        getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]")).click();
    }

    @Test
    public void testCreatePipelineProjectWithInvalidName(){
        String[] invalidChars = new String[] {"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "/", "|", "?", "<", ">"};
        String typeOfProject = "Pipeline";
        for (String invalidChar : invalidChars) {
            createProject(invalidChar, typeOfProject);
            String validationMessage = getDriver().findElement(By.id("itemname-invalid")).getText();
            Assert.assertEquals(validationMessage, "» ‘" + invalidChar + "’ is an unsafe character");
            Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
            getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]")).click();
        }
    }

    @Test
    public void testCreatePipelineProjectSameNamed(){
        String expectedResult = "New Pipeline project";
        String typeOfProject = "Pipeline";
        createProject(expectedResult, typeOfProject);
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]")).click();

        createProject(expectedResult, typeOfProject);

        String validationMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(validationMessage, String.format("» A job already exists with the name ‘%s’", expectedResult));
    }

    @Test
    public void testCreateMultibranchPipeline(){
        String project = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MultibranchPipeline_Project")
                .selectMultibranchPipelineAndOk()
                .clickSaveButton()
                .getTextFromNameMultibranchProject();

        Assert.assertEquals(project,"MultibranchPipeline_Project");
    }
}
