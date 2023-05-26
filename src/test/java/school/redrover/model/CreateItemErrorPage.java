package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BasePage;

public class CreateItemErrorPage extends BasePage {

    public CreateItemErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();
    }
}
