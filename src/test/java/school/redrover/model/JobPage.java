package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class JobPage extends BasePage {

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton() {

        WebElement dashBoardButton = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[text()='Dashboard']"));
        click(dashBoardButton);
        return new  MainPage(getDriver());
    }
}
