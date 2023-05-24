package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

import java.util.List;

public class ConfigureGlobalSecurityPage extends BasePage {

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> titles = getDriver().findElements(By.xpath("(//div[@class='jenkins-section__title'])"));

    public List<WebElement> getTitles() {
        return titles;
    }

    public boolean titlesAreAsExpected(List<String> expected, List<String> actual){
        for (WebElement title: getTitles()){
            actual.add(title.getText());
        }
        return expected.equals(actual);
    }
}
