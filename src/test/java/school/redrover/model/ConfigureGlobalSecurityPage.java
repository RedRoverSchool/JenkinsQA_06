package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import school.redrover.model.base.BaseModel;
import school.redrover.runner.TestUtils;


import java.util.ArrayList;
import java.util.List;

public class ConfigureGlobalSecurityPage extends BaseModel {

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfTitles() {
        List<WebElement> listTitle = new ArrayList<>(getDriver().findElements(By.cssSelector(".jenkins-form-label")));
        return listTitle.size();
    }

    public int getNumberOfHelpButton() {
        List<WebElement> listHelpButton = new ArrayList<>(getDriver().findElements(By.xpath("//a[starts-with(@tooltip,'Help')]")));
        return listHelpButton.size();
    }


    public ConfigureGlobalSecurityPage navigateToHostKeyVerificationStrategyDropdownAndClick() {
        Actions action = new Actions(getDriver());
        WebElement hostKeyVerificationDropdown = getDriver().findElement(By.xpath("//div[@class='jenkins-form-item ']//div[@class='jenkins-select']"));
        action.moveToElement(hostKeyVerificationDropdown).click().perform();

        return this;
    }

    public List<String> getDropDownMenuTexts() {
        List<WebElement> menus = getDriver().findElements(
                By.xpath("//div[@class='jenkins-form-item ']//div[@class='jenkins-select']//option"));

        return TestUtils.getTexts(menus);
    }

    public List<WebElement> getTitles() {
        return getDriver().findElements(By.xpath("(//div[@class='jenkins-section__title'])"));
    }

    private List<WebElement> getAPICheckBoxes() {
        return getDriver().findElements(By.xpath("(//div[@class='jenkins-section'])[8]//input[@type='checkbox']/following-sibling::label"));
    }

    private List<WebElement> getAdvancedSettingsButtons() {
        return getDriver().findElements(By.xpath("//button[@class='jenkins-button advanced-button advancedButton']"));
    }
//
//    private List<WebElement> getDropDownMenus() {
//        return getDriver().findElements(By.xpath("(//select[@class='jenkins-select__input dropdownList'])"));
//    }

    public List<WebElement> getRadioButtons() {
        return getDriver().findElements(By.xpath("//div[@class='jenkins-radio']"));
    }

    public boolean titlesAreAsExpected(List<String> expected, List<String> actual){
        for (WebElement title: getTitles()){
            actual.add(title.getText());
        }
        return expected.equals(actual);
    }

    public boolean checkAPICheckBoxes() {
        for ( int i = 0; i <= 2; i++ ) {
            WebElement checkBox = getWait2().until(ExpectedConditions.elementToBeClickable(getAPICheckBoxes().get(i)));
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
        for ( int i = 0; i <= 5; i++ ){
            WebElement radioButton = getWait5().until(ExpectedConditions.elementToBeClickable(getRadioButtons().get(i)));
            if (!radioButton.isSelected()){
                new Actions(getDriver()).click(radioButton).perform();
            }
            if(!radioButton.isEnabled()) {
                return false;
            }
        }
        return true;
    }
}
