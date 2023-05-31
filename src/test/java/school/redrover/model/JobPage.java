package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class JobPage extends BaseMainHeaderPage<JobPage> {

    public JobPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashBoardButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[text()='Dashboard']")));
        return new  MainPage(getDriver());
    }
}
