package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewItemTest extends BaseTest {


    @DataProvider(name = "jobType")
    public Object[][] JobTypes() {
        return new Object[][]{
                {TestUtils.JobType.FreestyleProject},
                {TestUtils.JobType.Pipeline},
                {TestUtils.JobType.MultiConfigurationProject},
                {TestUtils.JobType.Folder},
                {TestUtils.JobType.MultibranchPipeline},
                {TestUtils.JobType.OrganizationFolder}};
    }

    @Test(dataProvider = "jobType")
    public void testCreateNewItemWithEmptyName(TestUtils.JobType jobType) {
        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectJobType(jobType)
                .getItemNameRequiredErrorText();

        Assert.assertEquals(errorMessage, "» This field cannot be empty, please enter a valid name");
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
        boolean buttonIsEnabled = new MainPage(getDriver())
                .clickNewItem()
                .okButtonIsEnabled();

        Assert.assertFalse(buttonIsEnabled);
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

    @Test(dataProvider = "wrong-character")
    public void testCreateNewJobProjectWithInvalidName(String wrongCharacter){
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacter);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
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
                .selectJobType(TestUtils.JobType.MultibranchPipeline)
                .clickOkButton(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())))
                .clickSaveButton()
                .getTextFromNameMultibranchProject();

        Assert.assertEquals(project,"MultibranchPipeline_Project");
    }
}
