package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ConfigureGlobalSecurityPage extends BasePage {

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
}
