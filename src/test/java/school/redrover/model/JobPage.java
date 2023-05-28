package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseModel;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class JobPage extends BaseModel {

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton(BaseTest baseTest) {
        TestUtils.click(baseTest, getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[text()='Dashboard']")));
        return new  MainPage(getDriver());
    }
}
