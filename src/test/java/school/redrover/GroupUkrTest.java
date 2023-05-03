package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

public class GroupUkrTest extends BaseTest {

    @Test
    public void testVerifyJenkinsVersion() {
        WebElement versionJenkins = getDriver().findElement(By.xpath("//div[@class = 'container-fluid']//a[@rel='noopener noreferrer']"));

        Assert.assertEquals(versionJenkins.getText(), "Jenkins 2.387.2");
    }

    @Test
    public void testButtonAddDescription() {

        WebElement buttonAddDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        buttonAddDescription.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));

        Assert.assertTrue(textArea.isDisplayed());

    }

    @Test
    public void testDisplayingElementsCreateNewProjectPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        Actions actions = new Actions(getDriver());

        WebElement createJobButton = getDriver().findElement(By.xpath("//a[@class='content-block__link']/span"));
        createJobButton.click();

        WebElement nameOfItemInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='add-item-name']//input")));
        nameOfItemInput.sendKeys("MaxFirstTest");
        WebElement createNewFreeTaskButton = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']//span"));
        createNewFreeTaskButton.click();
        WebElement okButtonNewJobPage = getDriver().findElement(By.xpath("//div[@class='btn-decorator']/button"));
        okButtonNewJobPage.click();

        WebElement descriptionTextArea = getDriver().findElement(By.xpath("(//div[@class='setting-main']/textarea)[1]"));
        descriptionTextArea.sendKeys("First project");
        WebElement previewTextAreaButton = getDriver().findElement(By.xpath("(//div[@class='textarea-preview-container']/a)[1]"));
        previewTextAreaButton.click();
        WebElement textAreaPreviewInput = getDriver().findElement(By.xpath("//div[@class='textarea-preview']"));
        Assert.assertEquals(textAreaPreviewInput.getText(), "First project");
        WebElement hidePreviewButton = getDriver().findElement(By.xpath("//a[@class='textarea-hide-preview']"));
        Assert.assertTrue(hidePreviewButton.isDisplayed(), "Hide preview button isn't displayed");
        hidePreviewButton.click();
        Assert.assertFalse(textAreaPreviewInput.isDisplayed(), "Hide preview button is displayed");

        List<WebElement> headerToggleMenu = getDriver().findElements(By.xpath("//label[@class='jenkins-toggle-switch__label ']/span"));
        Assert.assertEquals(headerToggleMenu.get(1).getText(), "Enabled");
        headerToggleMenu.get(1).click();

        wait.until(ExpectedConditions.visibilityOf(headerToggleMenu.get(0)));
        Assert.assertEquals(headerToggleMenu.get(0).getText(), "Disabled");

        List<WebElement> checkboxGeneralSettings = getDriver().findElements(By.xpath("//div[@class='jenkins-form-item jenkins-form-item--tight']//label"));
        List<WebElement> namesOfBlockTitle = getDriver().findElements(By.xpath("//div[@class='jenkins-section__title']"));

        Assert.assertEquals(checkboxGeneralSettings.size(), 5);
        checkboxGeneralSettings.get(0).click();
        List<WebElement> checkboxDropDown = findCheckboxDropDownElements();
        Assert.assertTrue(checkboxDropDown.get(0).isDisplayed() && checkboxDropDown.get(1).isDisplayed(), "Dropdown Discard old builds checkbox menu  isn't displayed");
        actions.scrollToElement(checkboxGeneralSettings.get(1)).build().perform();
        checkboxGeneralSettings.get(1).click();
        checkboxDropDown = findCheckboxDropDownElements();
        Assert.assertTrue(checkboxDropDown.get(2).isDisplayed() && checkboxDropDown.get(3).isDisplayed(), "Dropdown GitHub project checkbox menu  isn't displayed");
        actions.scrollToElement(namesOfBlockTitle.get(1)).perform();
        checkboxGeneralSettings.get(2).click();
        checkboxDropDown = findCheckboxDropDownElements();
        actions.scrollToElement(checkboxDropDown.get(3)).perform();
        Assert.assertTrue(checkboxDropDown.get(3).isDisplayed(), "Dropdown This project is parameterized checkbox menu  isn't displayed");
        actions.scrollToElement(namesOfBlockTitle.get(0)).perform();
        checkboxGeneralSettings.get(3).click();
        checkboxDropDown = findCheckboxDropDownElements();
        Assert.assertTrue(checkboxDropDown.get(5).isDisplayed() && checkboxDropDown.get(6).isDisplayed() && checkboxDropDown.get(7).isDisplayed(), "Dropdown Throttle builds checkbox menu  isn't displayed");


        Assert.assertEquals(namesOfBlockTitle.get(0).getText(), "Source Code Management");
        actions.scrollToElement(namesOfBlockTitle.get(1)).perform();

        List<WebElement> useGitRadioButton = getDriver().findElements(By.xpath("//div[@class='radioBlock-container']//label"));
        useGitRadioButton.get(1).click();
        checkboxDropDown = findCheckboxDropDownElements();
        Assert.assertEquals(checkboxDropDown.size(), 14);
        for (int i = 9; i < checkboxDropDown.size(); i++) {
            actions.scrollToElement(checkboxDropDown.get(i)).perform();
            if (i == 12) {
                i++;
            }
            Assert.assertTrue(checkboxDropDown.get(i).isDisplayed(), "Element GIT radio button dropdown menu" + checkboxDropDown.get(i) + "  not showing");
        }

        Assert.assertEquals(namesOfBlockTitle.get(1).getText(), "Build Triggers");
        actions.scrollToElement(namesOfBlockTitle.get(2)).perform();
        List<WebElement> checkboxTriggerBlock = getDriver().findElements(By.xpath("(//div[@class='jenkins-section'])[2]//label"));
        int indexOfDropDownBlock = 14;
        for (int i = 0; i < checkboxTriggerBlock.size() - 1; i++) {
            if (i == 4) {
                i += 4;
            }
            if (i == 2) {
                actions.scrollToElement(namesOfBlockTitle.get(4)).perform();
            }
            if (i == 8) {
                actions.scrollToElement(namesOfBlockTitle.get(5)).perform();
            }
            actions.scrollToElement(checkboxTriggerBlock.get(i)).click(checkboxTriggerBlock.get(i)).perform();
            checkboxDropDown = findCheckboxDropDownElements();
            if (indexOfDropDownBlock != 15) {
                Assert.assertTrue(checkboxDropDown.get(indexOfDropDownBlock).isDisplayed(), checkboxDropDown.get(indexOfDropDownBlock) + "isn't displayed");
            }
            indexOfDropDownBlock++;
        }
        actions.scrollToElement(namesOfBlockTitle.get(5)).perform();
        Assert.assertEquals(namesOfBlockTitle.get(2).getText(), "Build Environment");
        Assert.assertEquals(namesOfBlockTitle.get(4).getText(), "Build Steps");
        Assert.assertEquals(namesOfBlockTitle.get(5).getText(), "Post-build Actions");
        List<WebElement> checkboxEnvironmentBuild = getDriver().findElements(By.xpath("(//div[@class='jenkins-section'])[3]//label"));
        Assert.assertEquals(checkboxEnvironmentBuild.size(), 8);

        for (int i = 0; i < checkboxEnvironmentBuild.size(); i++) {
            if (i == 1) {
                i += 2;
            }
            actions.scrollToElement(checkboxEnvironmentBuild.get(i)).click(checkboxEnvironmentBuild.get(i)).perform();
        }
        checkboxDropDown = findCheckboxDropDownElements();
        for (int i = 16; i < checkboxDropDown.size(); i++) {
            Assert.assertTrue(checkboxDropDown.get(i).isDisplayed(), "Dropdown" + checkboxDropDown.get(i) + "checkbox menu  isn't displayed");
        }

        WebElement addStepsBuild = getDriver().findElement(By.xpath("(//button[@class='hetero-list-add'])[5]"));
        WebElement addStepAfterBuild = getDriver().findElement(By.xpath("(//span[@class='yui-button yui-menu-button'])[7]//button"));
        WebElement footer = getDriver().findElement(By.xpath("//div[@class='page-footer__flex-row']"));
        actions.scrollToElement(footer).click(addStepsBuild).build().perform();


        List<WebElement> addStepsBuildDropDown = getDriver().findElements(By.xpath("//li[@groupindex='0']//a"));
        Assert.assertEquals(addStepsBuildDropDown.size(), 7);
        Assert.assertEquals(addStepsBuildDropDown.get(0).getText(), "Execute Windows batch command");
        Assert.assertEquals(addStepsBuildDropDown.get(1).getText(), "Execute shell");
        Assert.assertEquals(addStepsBuildDropDown.get(2).getText(), "Invoke Ant");
        Assert.assertEquals(addStepsBuildDropDown.get(3).getText(), "Invoke Gradle script");
        Assert.assertEquals(addStepsBuildDropDown.get(4).getText(), "Invoke top-level Maven targets");
        Assert.assertEquals(addStepsBuildDropDown.get(5).getText(), "Run with timeout");
        Assert.assertEquals(addStepsBuildDropDown.get(6).getText(), "Set build status to \"pending\" on GitHub commit");

        actions.moveToElement(footer).click().perform();
        addStepAfterBuild.click();

        List<WebElement> addStepsAfterBuildDropDown = getDriver().findElements(By.xpath("(//ul[@class='first-of-type'])[3]/li"));
        Assert.assertEquals(addStepsAfterBuildDropDown.size(), 11);
        Assert.assertEquals(addStepsAfterBuildDropDown.get(0).getText(), "Aggregate downstream test results");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(1).getText(), "Archive the artifacts");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(2).getText(), "Build other projects");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(3).getText(), "Publish JUnit test result report");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(4).getText(), "Record fingerprints of files to track usage");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(5).getText(), "Git Publisher");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(6).getText(), "E-mail Notification");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(7).getText(), "Editable Email Notification");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(8).getText(), "Set GitHub commit status (universal)");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(9).getText(), "Set build status on GitHub commit [deprecated]");
        Assert.assertEquals(addStepsAfterBuildDropDown.get(10).getText(), "Delete workspace when build is done");

        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submitButton.click();

        try {
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("(//div[@id='breadcrumbBar']//a[@class='model-link'])[1]")))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='MaxFirstTest']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Delete Project']"))).click();
            wait.until(ExpectedConditions.alertIsPresent());

        } catch (UnhandledAlertException e) {
            getDriver().switchTo().alert();
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    private List<WebElement> findCheckboxDropDownElements() {
        return getDriver().findElements(By.xpath("//div[@class='form-container tr']/div"));
    }
}



