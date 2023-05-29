package school.redrover.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.model.base.BasePage;

import java.time.Duration;
import java.util.List;

public class ConfigureGlobalSecurityPage extends BasePage {

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getTitles() {
        return getDriver().findElements(By.xpath("(//div[@class='jenkins-section__title'])"));
    }

    private List<WebElement> getCheckBoxes() {
        return getDriver().findElements(By.xpath("(//div[@class='jenkins-section'])[8]//input[@type='checkbox']/following-sibling::label"));
    }

    private List<WebElement> getAdvancedSettingsButtons() {
        return getDriver().findElements(By.xpath("//button[@class='jenkins-button advanced-button advancedButton']"));
    }

    private List<WebElement> getDropDownMenus() {
        return getDriver().findElements(By.xpath("(//select[@class='jenkins-select__input dropdownList'])"));
    }

    public List<WebElement> getRadioButtons() {
        return getDriver().findElements(By.xpath("//label[@class='jenkins-radio__label']"));
    }

    public boolean titlesAreAsExpected(List<String> expected, List<String> actual){
        for (WebElement title: getTitles()){
            actual.add(title.getText());
        }
        return expected.equals(actual);
    }

    public void checkAllCheckBoxes() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        for (int i = 0; i<=2; i++) {
            WebElement checkBox = getCheckBoxes().get(i);
            js.executeScript("arguments[0].scrollIntoView();", checkBox);
               new Actions(getDriver()).click(checkBox).perform();
               Thread.sleep(2000);
//            if (!checkBox.isSelected()) {
//                return false; b
//            }
        }
//
//        return true;
    }
    public boolean checkBoxIsSelected(WebElement checkBox) {
        getWait2().until(ExpectedConditions.elementToBeClickable(checkBox));
        new Actions(getDriver()).moveToElement(checkBox).click().perform();
       return checkBox.isSelected();
    }
}
