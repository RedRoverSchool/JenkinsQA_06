package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.NewJobPage;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;
import school.redrover.model.base.BaseProjectPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewItemTest extends BaseTest {

    @DataProvider(name = "jobType")
    public Object[][] JobTypes(){
        return new Object[][]{
                {TestUtils.JobType.FreestyleProject},
                {TestUtils.JobType.Pipeline},
                {TestUtils.JobType.MultiConfigurationProject},
                {TestUtils.JobType.Folder},
                {TestUtils.JobType.MultibranchPipeline},
                {TestUtils.JobType.OrganizationFolder}};
    }

    @Test(dataProvider = "jobType")
    public void testCreateNewItemWithEmptyName(TestUtils.JobType jobType){
        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectJobType(jobType)
                .getItemNameRequiredErrorText();

        Assert.assertEquals(errorMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testNewItemHeader(){
        String titleNewItem = new MainPage(getDriver())
                .clickNewItem()
                .getTitle();

        Assert.assertEquals(titleNewItem, "Enter an item name");
    }

    @Test
    public void testVerifyNewItemsList(){
        List<String> listOfNewItemsExpect = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        List<String> listOfNewItems = new MainPage(getDriver())
                .clickNewItem()
                .getListOfNewItems();

        for (int i = 0; i < listOfNewItemsExpect.size(); i++) {
            Assert.assertEquals(listOfNewItems.get(i), listOfNewItemsExpect.get(i));
        }
    }

    @Test
    public void testVerifyButtonIsDisabled(){
        boolean buttonIsEnabled = new MainPage(getDriver())
                .clickNewItem()
                .okButtonIsEnabled();

        Assert.assertFalse(buttonIsEnabled);
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters(){
        return new Object[][]
                {{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreateNewJobProjectWithInvalidName(String wrongCharacter){
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacter);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }


    public void createJob(TestUtils.JobType jobType, String projectName){
        BaseConfigPage<?, ?> page = null;
        switch (jobType) {
            case FreestyleProject -> page = new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver()));
            case Pipeline -> page = new PipelineConfigPage(new PipelinePage(getDriver()));
            case MultiConfigurationProject ->
                    page = new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver()));
            case MultibranchPipeline ->
                    page = new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
            case Folder -> page = new FolderConfigPage(new FolderPage(getDriver()));
            case OrganizationFolder -> page = new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver()));
        }

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(projectName)
                .selectJobType(jobType)
                .clickOkButton(page)
                .clickSaveButton()
                .getHeader()
                .clickLogo();
    }

    @Test(dataProvider = "jobType")
    public void testCreateNewItemWithSameNamed(TestUtils.JobType jobType){
        String projectName = "project";
        createJob(jobType, projectName);
        String validationMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(projectName)
                .selectJobType(jobType)
                .getItemInvalidMessage();

        Assert.assertEquals(validationMessage, String.format("» A job already exists with the name ‘%s’", projectName));
    }
}
