package school.redrover.runner;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.jobs.*;
import school.redrover.model.jobsconfig.*;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public enum JobType {
        FreestyleProject(By.xpath("//span[contains(text(),'Freestyle project')]")),

        Pipeline(By.xpath("//span[contains(text(),'Pipeline')]")),

        MultiConfigurationProject(By.xpath("//span[contains(text(),'Multi-configuration project')]")),

        Folder(By.xpath("//span[contains(text(),'Folder')]")),

        MultibranchPipeline(By.xpath("//span[contains(text(),'Multibranch Pipeline')]")),

        OrganizationFolder(By.xpath("//span[contains(text(),'Organization Folder')]"));

        private final By locator;

        JobType(By locator) {
            this.locator = locator;
        }

        public By getLocator() {
            return locator;
        }
    }

    public static void createJob(BaseTest baseTest, String name, JobType jobType, Boolean goToMainPage) {
        final WebDriver driver = baseTest.getDriver();
        BaseConfigPage<?,?> configPage = null;

        switch (jobType) {
            case FreestyleProject ->
                    configPage = new FreestyleProjectConfigPage(new FreestyleProjectPage(driver));
            case Pipeline ->
                    configPage = new PipelineConfigPage(new PipelinePage(driver));
            case MultiConfigurationProject ->
                    configPage = new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(driver));
            case  Folder ->
                    configPage = new FolderConfigPage(new FolderPage(driver));
            case MultibranchPipeline ->
                    configPage = new MultibranchPipelineConfigPage(new MultibranchPipelinePage(driver));
            case OrganizationFolder ->
                    configPage = new OrganizationFolderConfigPage(new OrganizationFolderPage(driver));
            default -> {
            }
        }

        new MainPage(driver)
                .clickNewItem()
                .enterItemName(name)
                .selectJobType(jobType)
                .clickOkButton(configPage)
                .clickSaveButton();

        if (goToMainPage) {
            new MainPage(driver)
                    .getHeader()
                    .clickLogo();
        }
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
                .selectJobType(JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(baseTest.getDriver())))
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

    public static String getRandomStr(int length) {
        return RandomStringUtils.random(length,
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    public static void createUserAndReturnToMainPage(BaseTest baseTest, String username, String password, String fullName, String email) {
        new MainPage(baseTest.getDriver())
                .clickManageJenkinsPage()
                .clickManageUsers()
                .clickCreateUser()
                .enterUsername(username)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .enterFullName(fullName)
                .enterEmail(email)
                .clickCreateUserButton()
                .getHeader()
                .clickLogo();
    }
}
