package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class JobPage extends BasePage {

    private static final By dashBoardButton = By.xpath("//div[@id='breadcrumbBar']//a[text()='Dashboard']");
    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton() {
        click((WebElement) dashBoardButton);
        return new  MainPage(getDriver());
    }
}
