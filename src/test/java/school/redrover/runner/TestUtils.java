package school.redrover.runner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.JobPage;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.model.base.BaseModel;

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
                .clickSaveButton();

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
                .clickSaveButton();

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

    public static void click(BaseModel baseModel, WebElement element) {
        waitElementToBeVisible(baseModel, element);
        waitElementToBeClickable(baseModel, element).click();
    }

    protected static void clear(BaseModel baseModel, WebElement element) {
        waitElementToBeClickable(baseModel, element).clear();
    }

    protected static void input(BaseModel baseModel, String text, WebElement element) {
        click(baseModel, element);
        element.sendKeys(text);
    }

    public static void sendTextToInput(BaseModel baseModel, WebElement element, String text) {
        click(baseModel, element);
        clear(baseModel, element);
        input(baseModel, text, element);
    }

    private static WebElement waitElementToBeClickable(BaseModel baseModel, WebElement element) {

        return baseModel.getWait5().until(ExpectedConditions.elementToBeClickable(element));
    }

    private static void waitElementToBeVisible(BaseModel baseModel, WebElement element) {
        baseModel.getWait5().until(ExpectedConditions.visibilityOf(element));
    }

    public static String getText(BaseModel baseModel, WebElement element) {
        if (!element.getText().isEmpty()) {
            waitElementToBeVisible(baseModel, element);
        }
        return element.getText();
    }

    public static void scrollToElementByJavaScript(BaseModel baseModel, WebElement element) {
        JavascriptExecutor jsc = (JavascriptExecutor) baseModel.getDriver();
        jsc.executeScript("arguments[0].scrollIntoView();", waitElementToBeClickable(baseModel, element));
    }

    public static void clickByJavaScript(BaseModel baseModel, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) baseModel.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
}
