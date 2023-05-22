package school.redrover.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;

public class TestUtils {

    private static void createProject(BaseTest baseTest, String name) {
       new MainPage(baseTest.getDriver()).clickNewItem().enterItemName(name);
    }

    private static void saveProject(BaseTest baseTest, Boolean goToHomePage) {

        if (goToHomePage) {
            baseTest.getDriver().findElement(By.linkText("Dashboard")).click();
        }
    }

    public static void createFreestyleProject(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);
        new NewJobPage(baseTest.getDriver()).selectFreestyleProjectAndOk();
        saveProject(baseTest, goToHomePage);
    }

    public static void createPipeline(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);
        new NewJobPage(baseTest.getDriver()).selectPipelineAndClickOK();
        saveProject(baseTest, goToHomePage);
    }

    public static void createMultiConfigurationProject(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);
        new NewJobPage(baseTest.getDriver()).selectMultiConfigurationProjectAndOk();
        saveProject(baseTest, goToHomePage);
    }

    public static void createFolder(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);
        new NewJobPage(baseTest.getDriver()).selectFolderAndOk();
        saveProject(baseTest, goToHomePage);
    }

    public static void createMultibranchPipeline(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);
        new NewJobPage(baseTest.getDriver()).selectMultibranchPipelineAndOk();
        saveProject(baseTest, goToHomePage);
    }

    public static void createOrganizationFolder(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);
        new NewJobPage(baseTest.getDriver()).selectOrganizationFolderAndOk();
        saveProject(baseTest, goToHomePage);
    }
}
