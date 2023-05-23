package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class JobPage extends BasePage {

    @FindBy(xpath = "//div[@id='breadcrumbBar']//a[text()='Dashboard']")
    private WebElement dashBoardButton;
    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton() {
        click(dashBoardButton);
        return new  MainPage(getDriver());
    }
}
