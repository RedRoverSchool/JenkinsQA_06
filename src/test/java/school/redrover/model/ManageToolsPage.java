package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;

public class ManageToolsPage  extends BaseConfigPage<ManageToolsPage, ManageJenkinsPage> {
    @FindBy(xpath = "//*[@class='jenkins-button advanced-button advancedButton']")
    private WebElement mavenInstallationsButton;

    @FindBy(xpath = "//*[text()='Add Maven']")
    private WebElement mavenAddButton;

    @FindBy(xpath = "//*[@name='_.name']")
    private WebElement nameField;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(tagName = "footer")
    private WebElement footer;

    @FindBy(xpath = "//button[@title ='Delete Maven']")
    private WebElement deleteMavenButton;

    public ManageToolsPage(ManageJenkinsPage manageJenkinsPage) {
        super(manageJenkinsPage);
    }

    public ManageToolsPage clickAddMaven() {
        int deltaY = footer.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(mavenAddButton)).click();
        return this;
    }

    public ManageToolsPage clickMavenInstallationsButton() {
        int deltaY = footer.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(mavenInstallationsButton)).click();
        return this;
    }

    public ManageToolsPage addMavenName(String nameMaven) {
        nameField.sendKeys(nameMaven);
        return this;
    }

    public ManageToolsPage clickDeleteMaven() {
        int deltaY = footer.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(deleteMavenButton)).click();
        getWait2().until(ExpectedConditions.invisibilityOf(deleteMavenButton));
        return this;
    }



}
