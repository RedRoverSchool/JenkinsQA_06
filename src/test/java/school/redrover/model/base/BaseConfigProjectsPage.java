package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.CreateItemErrorPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public abstract class BaseConfigProjectsPage<Self extends BaseConfigPage<?, ?>, ProjectPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, ProjectPage> {

    public BaseConfigProjectsPage(ProjectPage projectPage) {
        super(projectPage);
    }

    public Self addExecuteShellBuildStep(String command) {
        WebElement buildStep = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add build step')]")));

        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(By.xpath("//button[contains(text(), 'Add post-build action')]"))).click().perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(buildStep)).click();

        WebElement executeShell = getDriver().findElement(By.xpath("//a[contains(text(), 'Execute shell')]"));
        executeShell.click();

        WebElement codeMirror = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("CodeMirror")));
        actions.scrollToElement(getDriver().findElement(By.xpath("//button[contains(text(), 'Add post-build action')]"))).click().perform();
        WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-lines")).get(0);
        codeLine.click();
        WebElement commandField = codeMirror.findElement(By.cssSelector("textarea"));
        commandField.sendKeys(command);

        return (Self) this;
    }

    public Self clickOldBuildCheckBox() {
        TestUtils.clickByJavaScript(this, getDriver()
                .findElement(By.xpath("//span[@class='jenkins-checkbox']//input[@id='cb4']")));

        return (Self) this;
    }

    public Self enterDaysToKeepBuilds(int number) {
        WebElement daysToKeepBuilds = getDriver()
                .findElement(By.xpath("//input[@name='_.daysToKeepStr']"));
        WebElement nameFieldDaysToKeepBuilds = getDriver().findElement(By.xpath("//div[text()='Days to keep builds']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", nameFieldDaysToKeepBuilds);
        TestUtils.sendTextToInput(this, daysToKeepBuilds, String.valueOf(number));

        return (Self) this;
    }

    public Self enterMaxNumOfBuildsToKeep(int number) {
        WebElement maxNumOfBuildsToKeepNumber = getDriver()
                .findElement(By.xpath("//input[@name='_.numToKeepStr']"));
        TestUtils.sendTextToInput(this, maxNumOfBuildsToKeepNumber, String.valueOf(number));

        return (Self) this;
    }

    public Self switchCheckboxDisable() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//span[text() = 'Enabled']")))).click();
        return (Self) this;
    }

    public Self switchCheckboxEnabled() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//label[@for='enable-disable-project']")))).click();
        return (Self) this;
    }

    public String getTextDisable() {

        return getWait5().until(ExpectedConditions.elementToBeClickable
                (getDriver().findElement(By.xpath("//span[text() = 'Disabled']")))).getText();
    }

    public String getTextEnabled() {

        return getWait5().until(ExpectedConditions.elementToBeClickable
                (getDriver().findElement(By.xpath("//span[text() = 'Enabled']")))).getText();
    }

    public String getDaysToKeepBuilds(String attribute) {
        WebElement daysToKeepBuilds = getDriver()
                .findElement(By.xpath("//input[@name='_.daysToKeepStr']"));

        return daysToKeepBuilds.getAttribute(attribute);
    }

    public String getMaxNumOfBuildsToKeep(String attribute) {
        WebElement maxNumOfBuildsToKeepNumber = getDriver()
                .findElement(By.xpath("//input[@name='_.numToKeepStr']"));

        return maxNumOfBuildsToKeepNumber.getAttribute(attribute);
    }

    public Self clickGitHubProjectCheckbox() {
        getDriver().findElement(By.xpath("//label[text()='GitHub project']")).click();
        return (Self) this;
    }

    public Self inputTextTheInputAreaProjectUrlInGitHubProject(String text) {
        getDriver().findElement(By.cssSelector("[name='_.projectUrlStr']")).sendKeys(text);
        return (Self) this;
    }

    public CreateItemErrorPage getErrorPage() {
        return new CreateItemErrorPage(getDriver());
    }

    public Self checkProjectIsParametrized() {
        WebElement projectIsParametrized = getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//label[text()='This project is parameterized']")));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", projectIsParametrized);
        return (Self) this;
    }

    public Self selectParameterInDropDownByType(String type) {
        getDriver().findElement(By.xpath(String.format("//li/a[text()='%s']", type))).click();
        return (Self) this;
    }

    public Self openAddParameterDropDown() {
        WebElement addParameter = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='hetero-list-add']")));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement projectIsParametrized = getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//label[text()='This project is parameterized']")));
        js.executeScript("arguments[0].scrollIntoView();", projectIsParametrized);
        addParameter.click();
        return (Self) this;
    }

    public Self inputParameterName(String name) {
        getDriver().findElement(By.xpath("//input[@name='parameter.name']")).sendKeys(name);
        return (Self) this;
    }

    public Self inputParameterChoices(List<String> parameterChoices) {
        for (String element : parameterChoices) {
            getDriver().findElement(By.xpath("//textarea[@name='parameter.choices']")).sendKeys(element + "\n");
        }
        return (Self) this;
    }

    public Self inputParameterDesc(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='parameter.description']")).sendKeys(description);
        return (Self) this;
    }

    public Self selectCheckboxSetByDefault() {
        WebElement checkboxSetByDefault = getDriver().findElement(By.xpath("//label[normalize-space(text())='Set by Default']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", checkboxSetByDefault);
        return (Self) this;
    }

    public Self openBuildStepOptionsDropdown() {
        WebElement addBuildStepButton = getDriver().findElement(By.xpath("//button[text()='Add build step']"));
        TestUtils.scrollToElementByJavaScript(this, addBuildStepButton);
        getWait5().until(ExpectedConditions.elementToBeClickable(addBuildStepButton)).click();
        return (Self) this;
    }

    public List<String> getOptionsInBuildStepDropdown() {
        return TestUtils.getTexts(getDriver().findElements(By.xpath("//button[text()='Add build step']/../../..//a"))) ;
    }
}
