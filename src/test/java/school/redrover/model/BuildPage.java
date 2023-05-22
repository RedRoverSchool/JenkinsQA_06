package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class BuildPage extends BasePage {

    public final WebElement BUILD_HEADER = getDriver().findElement(By.xpath("//h1"));


    public BuildPage(WebDriver driver) {
        super(driver);
    }
}
