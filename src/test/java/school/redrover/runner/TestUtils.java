package school.redrover.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    private static void createProject(BaseTest baseTest, String name) {
        new MainPage(baseTest.getDriver())
                .clickNewItem()
                .enterItemName(name);
    }

    private static void goToMainPage(BaseTest baseTest, Boolean goToMainPage) {
        if (goToMainPage) {
            new JobPage(baseTest.getDriver())
                    .getHeader()
                    .clickLogo();
        }
    }

    public static void createFreestyleProject(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectFreestyleProjectAndOk()
                .clickSaveButton();

        goToMainPage(baseTest, goToHomePage);
    }

    public static void createPipeline(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectPipelineAndOk()
                .clickSaveButton();

        goToMainPage(baseTest, goToHomePage);
    }

    public static void createMultiConfigurationProject(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton();

        goToMainPage(baseTest, goToHomePage);
    }

    public static void createFolder(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectFolderAndOk()
                .clickSaveButton();

        goToMainPage(baseTest, goToHomePage);
    }

    public static void createMultibranchPipeline(BaseTest baseTest, String name, Boolean goToHomePage) {
        createProject(baseTest, name);

        new NewJobPage(baseTest.getDriver())
                .selectMultibranchPipelineAndOk()
                .clickSaveButton();

        goToMainPage(baseTest, goToHomePage);
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

    public static void clickBreadcrumbLinkItem(BaseTest baseTest, String breadcrumbLinkName) {
        List<WebElement> breadcrumbTree = baseTest.getDriver().findElements(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/a"));
        for (WebElement el : breadcrumbTree) {
            if (el.getText().equals(breadcrumbLinkName)) {
                el.click();
                break;
            }
        }
    }

    public static void createFreestyleProjectInsideFolderAndView(BaseTest baseTest, String jobName, String viewName, String folderName) {
        new ViewPage((baseTest.getDriver()))
                .clickDropDownMenuFolder(folderName)
                .selectNewItemInDropDownMenu(viewName, folderName)
                .enterItemName(jobName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton();

       clickBreadcrumbLinkItem(baseTest, viewName);
    }

    public static List<String> getListNames(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText().substring(0, element.getText().indexOf("\n")));
        }
        return texts;
    }
}
