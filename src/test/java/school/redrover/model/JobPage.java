package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseModel;

public class JobPage extends BaseModel {

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton() {
        click(getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[text()='Dashboard']")));
        return new  MainPage(getDriver());
    }
}
