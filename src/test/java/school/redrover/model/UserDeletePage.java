package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class UserDeletePage extends BaseMainHeaderPage<UserDeletePage> {

    @FindBy(name = "Submit")
    private WebElement yesButton;

    public UserDeletePage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickOnYesButton() {
        TestUtils.click(this, yesButton);

        return new MainPage(getDriver());
    }
}
