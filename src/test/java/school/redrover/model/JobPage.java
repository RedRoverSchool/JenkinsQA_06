package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class JobPage extends BasePage {

    private WebElement getDashBoardButton() {
        return getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[text()='Dashboard']"));
    }
    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton() {
        click(getDashBoardButton());
        return new  MainPage(getDriver());
    }
}
