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

}
