package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.util.List;

public class ConfigureGlobalSecurityPage extends BasePage {

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getTitles() {
        return getDriver().findElements(By.xpath("(//div[@class='jenkins-section__title'])"));
    }

    private List<WebElement> getCheckBoxes() {
        return getDriver().findElements(By.xpath("//input[@type='checkbox']"));
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

    public boolean checkAllCheckBoxes() throws InterruptedException {
        for (WebElement checkBox : getCheckBoxes()) {
            getWait2().until(ExpectedConditions.elementToBeClickable(checkBox));
            new Actions(getDriver()).moveToElement(checkBox).click().perform();
            Thread.sleep(2000);
            if (!checkBox.isSelected()) {
                return false;
            }
        }

        return true;
    }
    public boolean checkBoxIsSelected(WebElement checkBox) {
        getWait2().until(ExpectedConditions.elementToBeClickable(checkBox));
        new Actions(getDriver()).moveToElement(checkBox).click().perform();
       return checkBox.isSelected();
    }
}
