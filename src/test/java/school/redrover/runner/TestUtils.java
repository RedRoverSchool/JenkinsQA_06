package school.redrover.runner;

import org.openqa.selenium.WebElement;
import school.redrover.model.JobPage;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    private static void createProject(BaseTest baseTest, String name) {
        new MainPage(baseTest.getDriver())
                .clickNewItem()
                .enterItemName(name);
    }

    private static void goToHomePage(BaseTest baseTest, Boolean goToHomePage) {
        if (goToHomePage) {
            new JobPage(baseTest.getDriver())
                    .clickDashBoardButton();
        }
    }

    public static void createFreestyleProject(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectFreestyleProjectAndOk()
                .clickSave();

        goToHomePage(baseTest, goToHomePage);
    }

    public static void createPipeline(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectPipelineAndOk()
                .clickSaveButton();

        goToHomePage(baseTest, goToHomePage);
    }

    public static void createMultiConfigurationProject(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage();

        goToHomePage(baseTest, goToHomePage);
    }

    public static void createFolder(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectFolderAndOk()
                .clickSaveButton();

        goToHomePage(baseTest, goToHomePage);
    }

    public static void createMultibranchPipeline(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectMultibranchPipelineAndOk()
                .saveButton();

        goToHomePage(baseTest, goToHomePage);
    }

    public static void createOrganizationFolder(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectOrganizationFolderAndOk()
                .clickSaveButton();

        goToHomePage(baseTest, goToHomePage);
    }

    public static List<String> getTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }
}
