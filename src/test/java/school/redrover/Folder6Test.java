package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ProjectPage;
import school.redrover.runner.BaseTest;

import static java.sql.DriverManager.getDriver;

public class Folder6Test extends BaseTest {

    @Test
    public void testCreateFolder() {

        MainPage mainpage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("TC 00.04 New item Create Folder")
                .selectFolderAndOk()
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs();

     Assert.assertTrue(new MainPage(getDriver()).projectsTable().getText().contains("TC 00.04 New item Create Folder"));
    }

    @Test
    public void testCreateMulticonfigurationProjectInFolder(){

        ProjectPage mainpage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("TC 00.04 New item Create Folder")
                .selectFolderAndOk()
                .clickSaveButton()
                .clickCreateAJob()
                .enterItemName("Mine Project")
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage();

        Assert.assertTrue(new ProjectPage(getDriver()).projectsHeadline().getText().contains("Mine Project"));
    }
}
