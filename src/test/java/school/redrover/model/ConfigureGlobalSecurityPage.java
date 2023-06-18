package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigureGlobalSecurityPage extends BaseMainHeaderPage<ConfigureGlobalSecurityPage> {

    @FindBy(css = ".jenkins-section__title")
    private List<WebElement> listSectionTitles;

    @FindBy(css = ".jenkins-form-label")
    private List<WebElement> listFormLabels;

    @FindBy(xpath = "//a[starts-with(@tooltip,'Help')]")
    private List<WebElement> listHelpButtons;

    @FindBy(xpath = "//div[@class='jenkins-form-item ']//div[@class='jenkins-select']//option")
    private List<WebElement> menus;

    @FindBy(xpath = "//div[@class='jenkins-radio']")
    private List<WebElement> radioButtons;

    @FindBy(xpath = "(//div[@class='jenkins-section'])[8]//input[@type='checkbox']/following-sibling::label")
    private List<WebElement> apiTokenCheckboxes;

    @FindBy(xpath = "//div[@class='jenkins-form-item ']//div[@class='jenkins-select']")
    private WebElement hostKeyVerificationDropdown;

    @FindBy(xpath = "//button[@name = 'Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@id='notification-bar']/span")
    private WebElement savedNotificationText;

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfTitles() {

        return listFormLabels.size();
    }

    public int getNumberOfHelpButtons() {

        return listHelpButtons.size();
    }

    public ConfigureGlobalSecurityPage navigateToHostKeyVerificationStrategyDropdownAndClick() {
        Actions action = new Actions(getDriver());
        action.moveToElement(hostKeyVerificationDropdown).click().perform();

        return this;
    }

    public List<String> getDropDownMenuTexts() {

        return TestUtils.getTexts(menus);
    }

    private List<WebElement> getAPITokenCheckboxes() {
        return apiTokenCheckboxes;
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    public boolean checkAPITokenCheckboxes() {
        for (int i = 0; i <= 2; i++) {
            WebElement checkBox = getWait2().until(ExpectedConditions.elementToBeClickable(getAPITokenCheckboxes().get(i)));
            if (!checkBox.isSelected()) {
                new Actions(getDriver()).click(checkBox).perform();
            }
            if (!checkBox.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRadioButtons() {
        for (int i = 0; i <= 5; i++) {
            WebElement radioButton = getWait5().until(ExpectedConditions.elementToBeClickable(getRadioButtons().get(i)));
            if (!radioButton.isSelected()) {
                new Actions(getDriver()).click(radioButton).perform();
            }
            if (!radioButton.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public ConfigureGlobalSecurityPage clickApplyButton() {
        applyButton.click();

        return new ConfigureGlobalSecurityPage(getDriver());
    }

    public String getSavedNotificationText() {

        return getWait2().until(ExpectedConditions.visibilityOf(savedNotificationText)).getText();
    }

    public List<String> getSectionTitleList() {
        List<String> sectionTitleList = new ArrayList<>();
        for (WebElement element : listSectionTitles) {
            sectionTitleList.add(element.getText());
        }

        return sectionTitleList;
    }
}
